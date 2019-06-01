/*
 * This file is generated by jOOQ.
 */
package db.gen.tables.pojos;


import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;


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
public class User implements Serializable {

    private static final long serialVersionUID = -1119138921;

    private final Integer id;
    private final String  password;
    private final String  nickname;
    private final Date    lastlogin;

    public User(User value) {
        this.id = value.id;
        this.password = value.password;
        this.nickname = value.nickname;
        this.lastlogin = value.lastlogin;
    }

    public User(
        Integer id,
        String  password,
        String  nickname,
        Date    lastlogin
    ) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.lastlogin = lastlogin;
    }

    public Integer getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public Date getLastlogin() {
        return this.lastlogin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(password);
        sb.append(", ").append(nickname);
        sb.append(", ").append(lastlogin);

        sb.append(")");
        return sb.toString();
    }
}
