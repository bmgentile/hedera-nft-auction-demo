/*
 * This file is generated by jOOQ.
 */
package com.hedera.demo.auction.node.app.domain;


import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import org.jooq.Record;

import java.io.Serializable;

import static com.hedera.demo.auction.node.app.db.Tables.AUCTIONS;
public class Auction implements VertxPojo, Serializable {

    private static final long serialVersionUID = -495816041;

    private Integer id = 0;
    private String lastconsensustimestamp = "";
    private Long winningbid = 0L;
    private String winningaccount = "";
    private String winningtimestamp = "";
    private String tokenid = "";
    private String auctionaccountid = "";
    private String endtimestamp = "";
    private Long reserve = 0L;
    private String status = "";
    private boolean winnercanbid = false;
    private String winningtxid = "";
    private String winningtxhash = "";
    private String tokenimage = "";
    private long minimumbid = 0L;

    public Auction() {}
    
    public Auction(Record record) {
        this.id = record.get(AUCTIONS.ID);
        this.lastconsensustimestamp = record.get(AUCTIONS.LASTCONSENSUSTIMESTAMP);
        this.winningbid = record.get(AUCTIONS.WINNINGBID);
        this.winningaccount = record.get(AUCTIONS.WINNINGACCOUNT);
        this.winningtimestamp = record.get(AUCTIONS.WINNINGTIMESTAMP);
        this.tokenid = record.get(AUCTIONS.TOKENID);
        this.auctionaccountid = record.get(AUCTIONS.AUCTIONACCOUNTID);
        this.endtimestamp = record.get(AUCTIONS.ENDTIMESTAMP);
        this.reserve = record.get(AUCTIONS.RESERVE);
        this.status = record.get(AUCTIONS.STATUS);
        this.winnercanbid = record.get(AUCTIONS.WINNERCANBID);
        this.winningtxid = record.get(AUCTIONS.WINNINGTXID);
        this.winningtxhash = record.get(AUCTIONS.WINNINGTXHASH);
        this.tokenimage = record.get(AUCTIONS.TOKENIMAGE);
        this.minimumbid = record.get(AUCTIONS.MINIMUMBID);
    }

    public Auction (Row row) {
        this.id = row.getInteger(AUCTIONS.ID.getName());
        this.auctionaccountid = row.getString(AUCTIONS.AUCTIONACCOUNTID.getName());
        this.endtimestamp = row.getString(AUCTIONS.ENDTIMESTAMP.getName());
        this.winningbid = row.getLong(AUCTIONS.WINNINGBID.getName());
        this.winningaccount = row.getString(AUCTIONS.WINNINGACCOUNT.getName());
        this.winningtimestamp = row.getString(AUCTIONS.WINNINGTIMESTAMP.getName());
        this.tokenid = row.getString(AUCTIONS.TOKENID.getName());
        this.reserve = row.getLong(AUCTIONS.RESERVE.getName());
        this.status = row.getString(AUCTIONS.STATUS.getName());
        this.winningtxid = row.getString(AUCTIONS.WINNINGTXID.getName());
        this.winningtxhash = row.getString(AUCTIONS.WINNINGTXHASH.getName());
        this.tokenimage = row.getString(AUCTIONS.TOKENIMAGE.getName());
        this.minimumbid = row.getLong(AUCTIONS.MINIMUMBID.getName());
    }

    public Auction(
        Integer id,
        String lastconsensustimestamp,
        Long winningbid,
        String winningaccount,
        String winningtimestamp,
        String tokenid,
        String auctionaccountid,
        String endtimestamp,
        Long reserve,
        String status,
        boolean winnercanbid,
        String winningtxid,
        String winningtxhash,
        String tokenimage,
        long minimumbid
    ) {
        this.id = id;
        this.lastconsensustimestamp = lastconsensustimestamp;
        this.winningbid = winningbid;
        this.winningaccount = winningaccount;
        this.winningtimestamp = winningtimestamp;
        this.tokenid = tokenid;
        this.auctionaccountid = auctionaccountid;
        this.endtimestamp = endtimestamp;
        this.reserve = reserve;
        this.status = status;
        this.winnercanbid = winnercanbid;
        this.winningtxid = winningtxid;
        this.winningtxhash = winningtxhash;
        this.tokenimage = tokenimage;
        this.minimumbid = minimumbid;
    }

    public Auction(io.vertx.core.json.JsonObject json) {
        this();
        fromJson(json);
    }

    public static String closed() {
        return "CLOSED";
    }
    public static String pending() {
        return "PENDING";
    }
    public static String active() {
        return "ACTIVE";
    }

    public boolean isPending() {
        return this.status.equals(Auction.pending());
    }
    public boolean isActive() {
        return this.status.equals(Auction.active());
    }
    public boolean isClosed() {
        return this.status.equals(Auction.closed());
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastconsensustimestamp() {
        return this.lastconsensustimestamp;
    }

    public void setLastconsensustimestamp(String lastconsensustimestamp) {
        this.lastconsensustimestamp = lastconsensustimestamp;
    }

    public Long getWinningbid() {
        return this.winningbid;
    }

    public void setWinningbid(Long winningbid) {
        this.winningbid = winningbid;
    }

    public String getWinningaccount() {
        return this.winningaccount;
    }

    public void setWinningaccount(String winningaccount) {
        this.winningaccount = winningaccount;
    }

    public String getWinningtimestamp() {
        return this.winningtimestamp;
    }

    public void setWinningtimestamp(String winningtimestamp) {
        this.winningtimestamp = winningtimestamp;
    }

    public String getTokenid() {
        return this.tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getAuctionaccountid() {
        return this.auctionaccountid;
    }

    public void setAuctionaccountid(String auctionaccountid) {
        this.auctionaccountid = auctionaccountid;
    }

    public String getEndtimestamp() {
        return this.endtimestamp;
    }

    public void setEndtimestamp(String endtimestamp) {
        this.endtimestamp = endtimestamp;
    }

    public Long getReserve() {
        return this.reserve;
    }

    public void setReserve(Long reserve) {
        this.reserve = reserve;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getWinnerCanBid() {
        return this.winnercanbid;
    }

    public void setWinnercanbid(boolean winnercanbid) {
        this.winnercanbid = winnercanbid;
    }

    public String getWinningtxid() {
        return this.winningtxid;
    }

    public void setWinningtxid(String winningtxid) {
        this.winningtxid = winningtxid;
    }

    public String getWinningtxhash() {
        return this.winningtxhash;
    }

    public void setWinningtxhash(String winningtxhash) {
        this.winningtxhash = winningtxhash;
    }

    public String getTokenimage() {
        return this.tokenimage;
    }

    public void setTokenimage(String tokenimage) {
        this.tokenimage = tokenimage;
    }

    public Long getMinimumbid() {
        return this.minimumbid;
    }

    public void setMinimumbid(Long minimumbid) {
        this.minimumbid = minimumbid;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Auctions (");

        sb.append(id);
        sb.append(", ").append(lastconsensustimestamp);
        sb.append(", ").append(winningbid);
        sb.append(", ").append(winningaccount);
        sb.append(", ").append(winningtimestamp);
        sb.append(", ").append(tokenid);
        sb.append(", ").append(auctionaccountid);
        sb.append(", ").append(endtimestamp);
        sb.append(", ").append(reserve);
        sb.append(", ").append(status);
        sb.append(", ").append(winnercanbid);
        sb.append(", ").append(winningtxid);
        sb.append(", ").append(winningtxhash);
        sb.append(", ").append(tokenimage);
        sb.append(", ").append(minimumbid);

        sb.append(")");
        return sb.toString();
    }

    @Override
    public Auction fromJson(io.vertx.core.json.JsonObject json) {
        this.setId(json.getInteger("id"));
        this.setLastconsensustimestamp(json.getString("lastconsensustimestamp"));
        this.setWinningbid(json.getLong("winningbid"));
        this.setWinningaccount(json.getString("winningaccount"));
        this.setWinningtimestamp(json.getString("winningtimestamp"));
        this.setTokenid(json.getString("tokenid"));
        this.setAuctionaccountid(json.getString("auctionaccountid"));
        this.setEndtimestamp(json.getLong("endtimestamp").toString());
        this.setReserve(json.getLong("reserve"));
        this.setStatus(json.getString("status"));
        this.setWinnercanbid(json.getBoolean("winnercanbid"));
        this.setWinningtxid(json.getString("winningtxid"));
        this.setWinningtxhash(json.getString("winningtxhash"));
        this.setTokenimage(json.getString("tokenimage"));
        this.setMinimumbid(json.getLong("minimumbid"));
        return this;
    }


    @Override
    public JsonObject toJson() {
        JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("id",getId());
        json.put("lastconsensustimestamp",getLastconsensustimestamp());
        json.put("winningbid",getWinningbid());
        json.put("winningaccount",getWinningaccount());
        json.put("winningtimestamp",getWinningtimestamp());
        json.put("tokenid",getTokenid());
        json.put("auctionaccountid",getAuctionaccountid());
        json.put("endtimestamp",getEndtimestamp());
        json.put("reserve", getReserve());
        json.put("status",getStatus());
        json.put("winnercanbid",getWinnerCanBid());
        json.put("winningtxid", getWinningtxid());
        json.put("winningtxhash", getWinningtxhash());
        json.put("tokenimage", getTokenimage());
        json.put("minimumbid", getMinimumbid());

        return json;
    }
}
