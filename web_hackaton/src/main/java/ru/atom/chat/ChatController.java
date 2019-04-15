package ru.atom.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.hibernate.Session;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Controller
@RequestMapping("chat")
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private Queue<String> messages = new ConcurrentLinkedQueue<>();
    private Map<String, String> usersOnline = new ConcurrentHashMap<>();

    /**
     * curl -X POST -i localhost:8080/chat/login -d "name=I_AM_STUPID"
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(
        path = "login",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("name") String name) {
        if (name.length() < 1) {
            return ResponseEntity.badRequest().body("Too short name, sorry :(");
        }
        if (name.length() > 20) {
            return ResponseEntity.badRequest().body("Too long name, sorry :(");
        }
        if (usersOnline.containsKey(name)) {
            return ResponseEntity.badRequest().body("Already logged in:(");
        }
        usersOnline.put(name, name);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setBody("[" + name + "] logged in");
        session.save(messageEntity);
        session.getTransaction().commit();

        session.close();
        messages.add("[" + name + "] logged in");
        return ResponseEntity.ok().build();
    }

    /**
     * curl -i localhost:8080/chat/chat
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(
        path = "chat",
        method = RequestMethod.GET,
        produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> chat() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<MessageEntity> messages = session.createCriteria(MessageEntity.class).list();
        session.close();
        return new ResponseEntity<>(messages.stream()
            .map(object -> object.getBody())
            .collect(Collectors.joining("\n")),
            HttpStatus.OK);
    }

    /**
     * curl -i localhost:8080/chat/online
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(
        path = "online",
        method = RequestMethod.GET,
        produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity online() {
        String responseBody = String.join("\n", usersOnline.keySet().stream().sorted().collect(Collectors.toList()));
        return ResponseEntity.ok(responseBody);
    }

    /**
     * curl -X POST -i localhost:8080/chat/logout -d "name=I_AM_STUPID"
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(
        path = "logout",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> logout(@RequestParam("name") String name) {
        if (!usersOnline.containsKey(name)) {
            return ResponseEntity.badRequest().body("User '" + name + "' was not logged in.");
        }
        usersOnline.remove(name);
        messages.add("[" + name + "] logged out");
        return ResponseEntity.ok().build();
    }

    /**
     * curl -X POST -i localhost:8080/chat/say -d "name=I_AM_STUPID&msg=Hello everyone in this chat"
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(
        path = "say",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> say(@RequestParam("name") String name, @RequestParam("msg") String msg) {
        if (!usersOnline.containsKey(name)) {
            return ResponseEntity.badRequest().body("User '" + name + "' was not logged in.");
        }
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setBody("[" + name + "]: " + msg);
        session.save(messageEntity);
        session.getTransaction().commit();

        session.close();
        messages.add("[" + name + "]: " + msg);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(
        path = "clear",
        method = RequestMethod.DELETE,
        produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity clear() {
        messages.clear();
        return ResponseEntity.noContent().build();
    }

}
