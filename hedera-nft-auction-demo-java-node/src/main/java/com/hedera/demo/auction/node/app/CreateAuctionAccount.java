package com.hedera.demo.auction.node.app;

import com.google.errorprone.annotations.Var;
import com.hedera.hashgraph.sdk.*;
import io.vertx.core.json.JsonObject;
import lombok.extern.log4j.Log4j2;
import org.jooq.tools.StringUtils;

@Log4j2
public class CreateAuctionAccount extends AbstractCreate {

    public CreateAuctionAccount() throws Exception {
        super();
    }

    /**
     * Creates an auction account with an optional set of keys
     * @param initialBalance the initial balance for the new account
     * @param keys an array of public keys, if none supplied, the operator's public key is used
     * @throws Exception in the event of an exception
     */
    public AccountId create(long initialBalance, String keys) throws Exception {

        Client client = hederaClient.client();
        AccountCreateTransaction accountCreateTransaction = new AccountCreateTransaction();

        @Var KeyList keyList = new KeyList();
        if (StringUtils.isEmpty(keys)) {
            log.info("No public key provided, defaulting to operator public key");
            keyList.add(client.getOperatorPublicKey());
        } else {
            JsonObject jsonObject = new JsonObject(keys);
            AuctionKey auctionKey = jsonObject.mapTo(AuctionKey.class);
            keyList = auctionKey.toKeyList();
        }

        try {
            accountCreateTransaction.setKey(keyList);
            accountCreateTransaction.setInitialBalance(Hbar.from(initialBalance));
            TransactionResponse response = accountCreateTransaction.execute(client);

            TransactionReceipt receipt = response.getReceipt(client);
            if (receipt.status != Status.SUCCESS) {
                log.error("Account creation failed " + receipt.status);
                throw new Exception("Account creation failed " + receipt.status);
            } else {
                log.info("Account created " + receipt.accountId.toString());
            }
            return receipt.accountId;
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            log.error("Invalid number of arguments supplied - initial balance, keys");
            return;
        } else {
            @Var String keys = "";
            if (args.length == 2) {
                keys = args[1];
            }
            log.info("Creating account");
            CreateAuctionAccount createAuctionAccount = new CreateAuctionAccount();
            createAuctionAccount.create(Long.parseLong(args[0]), keys);
        }
    }
}
