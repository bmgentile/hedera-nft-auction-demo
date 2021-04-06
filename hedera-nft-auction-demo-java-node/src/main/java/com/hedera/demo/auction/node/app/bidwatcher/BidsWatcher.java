package com.hedera.demo.auction.node.app.bidwatcher;

import com.hedera.demo.auction.node.app.HederaClient;
import com.hedera.demo.auction.node.app.repository.AuctionsRepository;
import com.hedera.demo.auction.node.app.repository.BidsRepository;
import io.vertx.ext.web.client.WebClient;
import lombok.SneakyThrows;

public class BidsWatcher implements Runnable {

    private final int auctionId;
    private final WebClient webClient;
    private final BidsRepository bidsRepository;
    private final AuctionsRepository auctionsRepository;
    private final String refundKey;
    private final int mirrorQueryFrequency;
    private final String mirrorProvider = HederaClient.getMirrorProvider();

    public BidsWatcher(WebClient webClient, AuctionsRepository auctionsRepository, BidsRepository bidsRepository, int auctionId, String refundKey, int mirrorQueryFrequency) {
        this.webClient = webClient;
        this.bidsRepository = bidsRepository;
        this.auctionsRepository = auctionsRepository;
        this.auctionId = auctionId;
        this.refundKey = refundKey;
        this.mirrorQueryFrequency = mirrorQueryFrequency;
    }

    @SneakyThrows
    @Override
    public void run() {

        BidsWatcherInterface bidsWatcher;
        switch (mirrorProvider) {
            case "HEDERA":
                bidsWatcher = new HederaBidsWatcher(webClient, auctionsRepository, bidsRepository, auctionId, refundKey, mirrorQueryFrequency);
                break;
            case "DRAGONGLASS":
                bidsWatcher = new DragonglassBidsWatcher(webClient, auctionsRepository, bidsRepository, auctionId, refundKey, mirrorQueryFrequency);
                break;
            default:
                bidsWatcher = new KabutoBidsWatcher(webClient, auctionsRepository, bidsRepository, auctionId, refundKey, mirrorQueryFrequency);
                break;
        }
        bidsWatcher.watch();
    }
}
