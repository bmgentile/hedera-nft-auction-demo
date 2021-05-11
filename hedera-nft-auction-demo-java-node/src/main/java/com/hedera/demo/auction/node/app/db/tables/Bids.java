/*
 * This file is generated by jOOQ.
 */
package com.hedera.demo.auction.node.app.db.tables;


import com.hedera.demo.auction.node.app.db.Keys;
import com.hedera.demo.auction.node.app.db.Public;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Bids extends TableImpl<Record> {

    private static final long serialVersionUID = 309494280;

    /**
     * The reference instance of <code>public.bids</code>
     */
    public static final Bids BIDS = new Bids();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>public.bids.timestamp</code>.
     */
    public final TableField<Record, String> TIMESTAMP = createField(DSL.name("timestamp"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.bids.auctionid</code>.
     */
    public final TableField<Record, Integer> AUCTIONID = createField(DSL.name("auctionid"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.bids.bidderaccountid</code>.
     */
    public final TableField<Record, String> BIDDERACCOUNTID = createField(DSL.name("bidderaccountid"), org.jooq.impl.SQLDataType.CLOB.defaultValue(org.jooq.impl.DSL.field("''::text", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>public.bids.bidamount</code>.
     */
    public final TableField<Record, Long> BIDAMOUNT = createField(DSL.name("bidamount"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.bids.status</code>.
     */
    public final TableField<Record, String> STATUS = createField(DSL.name("status"), org.jooq.impl.SQLDataType.CLOB.defaultValue(org.jooq.impl.DSL.field("''::text", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>public.bids.refundtxid</code>.
     */
    public final TableField<Record, String> REFUNDTXID = createField(DSL.name("refundtxid"), org.jooq.impl.SQLDataType.CLOB.defaultValue(org.jooq.impl.DSL.field("''::text", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>public.bids.refundtxhash</code>.
     */
    public final TableField<Record, String> REFUNDTXHASH = createField(DSL.name("refundtxhash"), org.jooq.impl.SQLDataType.CLOB.defaultValue(org.jooq.impl.DSL.field("''::text", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>public.bids.transactionid</code>.
     */
    public final TableField<Record, String> TRANSACTIONID = createField(DSL.name("transactionid"), org.jooq.impl.SQLDataType.CLOB.defaultValue(org.jooq.impl.DSL.field("''::text", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>public.bids.transactionhash</code>.
     */
    public final TableField<Record, String> TRANSACTIONHASH = createField(DSL.name("transactionhash"), org.jooq.impl.SQLDataType.CLOB.defaultValue(org.jooq.impl.DSL.field("''::text", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>public.bids.timestampforrefund</code>.
     */
    public final TableField<Record, String> TIMESTAMPFORREFUND = createField(DSL.name("timestampforrefund"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.bids.refundstatus</code>.
     */
    public final TableField<Record, String> REFUNDSTATUS = createField(DSL.name("refundstatus"), org.jooq.impl.SQLDataType.CLOB.defaultValue(org.jooq.impl.DSL.field("''::text", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * Create a <code>public.bids</code> table reference
     */
    public Bids() {
        this(DSL.name("bids"), null);
    }

    /**
     * Create an aliased <code>public.bids</code> table reference
     */
    public Bids(String alias) {
        this(DSL.name(alias), BIDS);
    }

    /**
     * Create an aliased <code>public.bids</code> table reference
     */
    public Bids(Name alias) {
        this(alias, BIDS);
    }

    private Bids(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Bids(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Bids(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, BIDS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.BIDS_PKEY;
    }

    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(Keys.BIDS_PKEY);
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.<ForeignKey<Record, ?>>asList(Keys.BIDS__BIDS_AUCTIONID_FKEY);
    }

    public Auctions auctions() {
        return new Auctions(this, Keys.BIDS__BIDS_AUCTIONID_FKEY);
    }

    @Override
    public Bids as(String alias) {
        return new Bids(DSL.name(alias), this);
    }

    @Override
    public Bids as(Name alias) {
        return new Bids(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Bids rename(String name) {
        return new Bids(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Bids rename(Name name) {
        return new Bids(name, null);
    }
}
