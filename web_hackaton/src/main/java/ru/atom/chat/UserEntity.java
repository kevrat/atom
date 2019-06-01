package ru.atom.chat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

public class UserEntity {
    private int id;
    private String nickname;
    private String password;
    private Timestamp lastLogin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Basic
//    @Column(name = "lastLogin", nullable = false)
//    public Timestamp getLastLogin() {
//        return lastLogin;
//    }
//
//    public void setLastLogin(Timestamp lastLogin) {
//        this.lastLogin = lastLogin;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id
            && Objects.equals(nickname, that.nickname)
            && Objects.equals(password, that.password)
            && Objects.equals(lastLogin, that.lastLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, password, lastLogin);
    }
}
