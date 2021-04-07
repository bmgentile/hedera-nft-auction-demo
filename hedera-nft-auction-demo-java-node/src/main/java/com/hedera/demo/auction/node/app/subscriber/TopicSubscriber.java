package com.hedera.demo.auction.node.app.subscriber;

import com.google.errorprone.annotations.Var;
import com.google.protobuf.ByteString;
import com.hedera.demo.auction.node.app.HederaClient;
import com.hedera.demo.auction.node.app.readinesswatcher.AuctionReadinessWatcher;
import com.hedera.demo.auction.node.app.domain.Auction;
import com.hedera.demo.auction.node.app.repository.AuctionsRepository;
import com.hedera.demo.auction.node.app.repository.BidsRepository;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.FileContentsQuery;
import com.hedera.hashgraph.sdk.FileId;
import com.hedera.hashgraph.sdk.Hbar;
import com.hedera.hashgraph.sdk.Status;
import com.hedera.hashgraph.sdk.TokenAssociateTransaction;
import com.hedera.hashgraph.sdk.TokenId;
import com.hedera.hashgraph.sdk.TokenInfo;
import com.hedera.hashgraph.sdk.TokenInfoQuery;
import com.hedera.hashgraph.sdk.TopicId;
import com.hedera.hashgraph.sdk.TopicMessage;
import com.hedera.hashgraph.sdk.TopicMessageQuery;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.hedera.hashgraph.sdk.TransactionResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import lombok.extern.log4j.Log4j2;
import org.jooq.tools.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TopicSubscriber implements Runnable{
    private final AuctionsRepository auctionsRepository;
    private final BidsRepository bidsRepository;
    private final TopicId topicId;
    private static Instant startTime = Instant.EPOCH;
    private final WebClient webClient;
    private final String refundKey;
    private final int mirrorQueryFrequency;
    private final HederaClient hederaClient;

    public TopicSubscriber(HederaClient hederaClient, AuctionsRepository auctionsRepository, BidsRepository bidsRepository, WebClient webClient, TopicId topicId, String refundKey, int mirrorQueryFrequency) {
        this.auctionsRepository = auctionsRepository;
        this.bidsRepository = bidsRepository;
        this.topicId = topicId;
        this.webClient = webClient;
        this.refundKey = refundKey;
        this.mirrorQueryFrequency = mirrorQueryFrequency;
        this.hederaClient = hederaClient;
    }

    @Override
    public void run() {
        try {
            startSubscription();
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error(e);
        }
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
    }

    private void startSubscription() {
        try {
            Client client = hederaClient.client();
            log.info("Subscribing to topic " + topicId.toString());
            new TopicMessageQuery()
                    .setTopicId(topicId)
                    .setStartTime(startTime)
                    .subscribe(client, topicMessage -> {
                        startTime = topicMessage.consensusTimestamp;
                        handle(client, topicMessage);
                    });
        } catch (RuntimeException e) {
            log.error("Mirror subscription error " + e.getMessage());
            log.info("Attempting re-subscription after 5s");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
                log.error(interruptedException);
            }
            startSubscription();
        }
    }

    private void handle(Client client, TopicMessage topicMessage) {
        try {
            String auctionData = new String(topicMessage.contents, StandardCharsets.UTF_8);
            JsonObject auctionJson = new JsonObject(auctionData);
            Auction newAuction = new Auction().fromJson(auctionJson);
            @Var String endTimeStamp = newAuction.getEndtimestamp();
            if (StringUtils.isEmpty(endTimeStamp)) {
                // no end timestamp, use consensus timestamp + 2 days
                Instant consensusTime = topicMessage.consensusTimestamp;
                endTimeStamp = String.valueOf(consensusTime.plus(2, ChronoUnit.DAYS).getEpochSecond());
            }
            newAuction.setEndtimestamp(endTimeStamp.concat(".000000000")); // add nanoseconds
            newAuction.setWinningbid(0L);

            // get token info
            TokenInfo tokenInfo = new TokenInfoQuery()
                    .setTokenId(TokenId.fromString(newAuction.getTokenid()))
                    .execute(client);

            // if token symbol starts with HEDERA://
            // load file from hedera
            if (tokenInfo.symbol.startsWith("HEDERA://")) {
                String fileId = tokenInfo.symbol.replace("HEDERA://", "");
                ByteString contentsQuery = new FileContentsQuery()
                        .setFileId(FileId.fromString(fileId))
                        .execute(client);
                String contents = contentsQuery.toString(StandardCharsets.UTF_8);
                // set token image data
                newAuction.setTokenimage(contents);
            }

            Auction auction = auctionsRepository.add(newAuction);

            if (auction.getId() != null) {
                log.info("Auction for token " + newAuction.getTokenid() + " added");

                // If refund key, associate with the token using a scheduled transaction
                if ( ! StringUtils.isEmpty(refundKey)) {
                    //TODO: Scheduled transaction - sign with refundKey
                    client.setMaxTransactionFee(Hbar.from(100));
                    TokenAssociateTransaction tokenAssociateTransaction = new TokenAssociateTransaction();
                    List<TokenId> tokenIds = new ArrayList<>();
                    tokenIds.add(TokenId.fromString(auction.getTokenid()));
                    tokenAssociateTransaction.setTokenIds(tokenIds);
                    tokenAssociateTransaction.setTransactionMemo("Associate");
                    tokenAssociateTransaction.setAccountId(AccountId.fromString(auction.getAuctionaccountid()));

                    TransactionResponse response = tokenAssociateTransaction.execute(client);

                    TransactionReceipt receipt = response.getReceipt(client);
                    if (receipt.status != Status.SUCCESS) {
                        log.error("Token association failed " + receipt.status);
                        throw new Exception("Token association failed " + receipt.status);
                    } else {
                        log.info("Scheduled transaction to associate token " + auction.getTokenid() + " with auction account " + auction.getAuctionaccountid());
                    }
                }

                // Start a thread to watch this new auction for readiness
                Thread t = new Thread(new AuctionReadinessWatcher(hederaClient, webClient, auctionsRepository, bidsRepository, newAuction, refundKey, mirrorQueryFrequency));
                t.start();
            }
        } catch (Exception e) {
            log.error(e);
        }
    }
}
