/*
 * This file is generated by jOOQ.
 */
package db.gen;


import db.gen.tables.Message;
import db.gen.tables.User;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in atom
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>atom.message</code>.
     */
    public static final Message MESSAGE = db.gen.tables.Message.MESSAGE;

    /**
     * The table <code>atom.user</code>.
     */
    public static final User USER = db.gen.tables.User.USER;
}
