package ru.atom.chat;


import db.gen.tables.Message;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static db.gen.Tables.MESSAGE;

@Repository
@Transactional
public class MessageRepo {
    Message message = Message.MESSAGE;

    @Autowired
    private DSLContext dsl;

    @Transactional
    public void createMessage(MessageEntity message){
        dsl.insertInto(MESSAGE)
            .columns(MESSAGE.BODY)
            .values(message.getBody())
            .execute();
    }

    @Transactional(readOnly = true)
    public db.gen.tables.pojos.Message getMessage(Integer id){
        return dsl.selectFrom(message)
            .where(message.ID.eq(id))
            .fetchOneInto(db.gen.tables.pojos.Message.class);
    }
    @Transactional(readOnly = true)
    public List<db.gen.tables.pojos.Message> getMessages(){
        return dsl.selectFrom(message).fetchInto(db.gen.tables.pojos.Message.class);
    }
}
