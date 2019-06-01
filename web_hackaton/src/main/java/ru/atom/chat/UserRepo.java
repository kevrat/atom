package ru.atom.chat;


import db.gen.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static db.gen.Tables.USER;
import db.gen.tables.User;

import java.util.List;

@Repository
@Transactional
public class UserRepo {
    User user = User.USER;

    @Autowired
    private DSLContext dsl;

    @Transactional
    public void createUser(UserEntity user){
        dsl.insertInto(USER)
            .columns(USER.NICKNAME, USER.PASSWORD)
            .values(
                user.getNickname(),
                user.getPassword())
            .execute();
    }

    @Transactional(readOnly = true)
    public db.gen.tables.pojos.User getUser(String nickname){
        return dsl.selectFrom(user)
            .where(user.NICKNAME.eq(nickname))
            .fetchOneInto(db.gen.tables.pojos.User.class);
    }
    @Transactional(readOnly = true)
    public List<db.gen.tables.pojos.User> getUsers(){
        return dsl.selectFrom(user).fetchInto(db.gen.tables.pojos.User.class);
    }

    @Transactional
    public boolean deleteUserByNickname(String nickname)
    {
        return dsl.deleteFrom(user)
            .where(user.NICKNAME.eq(nickname))
            .execute() > 0;
    }
}
