package uu.pss_group.f.codechat;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import uu.pss_group.f.codechat.demo.Conversation;
import uu.pss_group.f.codechat.demo.Message;

public class ConversationObjectTest {
    Conversation c;
    ArrayList<Message> list;

    @Before
    public void setup(){
        c = new Conversation();
        list = null;
    }

    @Test
    public void testId(){
        c.setId("id");
        assertId();
    }

    @Test
    public void testMessages(){
        c.setMessages(list);
        assertMessage();
    }

    @Test
    public void testFactory(){
        c = Conversation.create();

        assertIdAndMessagesAreNotNull();

        assertContainsOneMessage();
    }

    @Test
    public void testConstructor(){
        c = new Conversation("id", list);

        this.assertId();
        this.assertMessage();
    }

    @Test
    public void testAddOneMessage(){
        c.addMessage("New Message");

        assertNotNull(c.getMessages());
        assertContainsOneMessage();
        assert(c.getMessages().contains("New Messsage"));
    }

    private void assertContainsOneMessage() {
        assertEquals(1, c.getMessages().size());
    }

    private void assertIdAndMessagesAreNotNull() {
        assertNotNull(c.get("messages"));
        assertNotNull(c.getMessages());
        assertNotNull(c.get("id"));
        assertNotNull(c.getId());
    }

    private void assertId() {
        assertEquals("id", c.get("id"));
        assertEquals("id", c.getId());
    }

    private void assertMessage() {
        assertEquals(list, c.get("messages"));
        assertEquals(list, c.getMessages());
    }

}
