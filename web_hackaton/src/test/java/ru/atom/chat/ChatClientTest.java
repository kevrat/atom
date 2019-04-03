package ru.atom.chat;

import okhttp3.Response;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ChatApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class ChatClientTest {
    private static final Logger log = LoggerFactory.getLogger(ChatClientTest.class);

    private static String MY_NAME_IN_CHAT = "I_AM_STUPID";
    private static String MY_MESSAGE_TO_CHAT = "KILL_ME_SOMEONE";

    @Test
    public void login() throws IOException {
        Response response = ChatClient.login(MY_NAME_IN_CHAT);
        log.info("[" + response + "]");
        String body = response.body().string();
        log.info(body);
        Assert.assertTrue(response.code() == 200 || body.equals("Already logged in:("));
    }

    @Test
    public void viewChat() throws IOException {
        Response response = ChatClient.viewChat();
        log.info("[" + response + "]");
        log.info(response.body().string());
        Assert.assertEquals(200, response.code());
    }

    @Test
    public void viewOnline() throws IOException {
        Response loginResponse = ChatClient.login(MY_NAME_IN_CHAT);
        Response viewResponse = ChatClient.viewOnline();
        System.out.println("[" + viewResponse + "]");
        String responseBody = viewResponse.body().string();
        Assert.assertTrue(viewResponse.code() == 200 && responseBody.equals(MY_NAME_IN_CHAT));
    }

    @Test
    public void say() throws IOException {
        Response loginResponse = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + loginResponse + "]");
        Response sayResponse = ChatClient.say(MY_NAME_IN_CHAT, MY_MESSAGE_TO_CHAT);
        System.out.println("[" + sayResponse + "]");
        System.out.println(sayResponse.body().string());
        Assert.assertEquals(200, sayResponse.code());
    }

    @Test
    public void logout() throws IOException {
        Response loginResponse = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + loginResponse + "]");
        Response logoutResponse = ChatClient.logout(MY_NAME_IN_CHAT);
        System.out.println("[" + logoutResponse + "]");
        System.out.println(logoutResponse.body().string());
        Assert.assertEquals(200, logoutResponse.code());
    }

    @Test
    public void clear() throws IOException {
        Response response = ChatClient.clear();
        System.out.println(response.code());
        Assert.assertTrue(response.code() == 204);
    }

}
