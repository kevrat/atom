/*
 * This file is generated by jOOQ.
 */
package db.gen.tables;


import db.gen.Atom;
import db.gen.Indexes;
import db.gen.Keys;
import db.gen.tables.records.UserRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = 1045666888;

    /**
     * The reference instance of <code>atom.user</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>atom.user.id</code>.
     */
    public final TableField<UserRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>atom.user.password</code>.
     */
    public final TableField<UserRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>atom.user.nickname</code>.
     */
    public final TableField<UserRecord, String> NICKNAME = createField("nickname", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>atom.user.lastLogin</code>.
     */
    public final TableField<UserRecord, Date> LASTLOGIN = createField("lastLogin", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * Create a <code>atom.user</code> table reference
     */
    public User() {
        this(DSL.name("user"), null);
    }

    /**
     * Create an aliased <code>atom.user</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>atom.user</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> User(Table<O> child, ForeignKey<O, UserRecord> key) {
        super(child, key, USER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Atom.ATOM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.USER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserRecord, Integer> getIdentity() {
        return Keys.IDENTITY_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.KEY_USER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserRecord>> getKeys() {
        return Arrays.<UniqueKey<UserRecord>>asList(Keys.KEY_USER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }
}
