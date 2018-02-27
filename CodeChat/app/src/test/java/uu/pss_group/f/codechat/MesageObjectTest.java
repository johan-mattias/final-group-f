package uu.pss_group.f.codechat;

import org.junit.Before;
import org.junit.Test;

import uu.pss_group.f.codechat.demo.Message;

import static org.junit.Assert.*;

public class MesageObjectTest {
    Message msg;

    @Before
    public void setup(){
        msg = new Message();
    }

   @Test
   public void assertMessage(){
       msg.setMessage("message");
       assertMessage(msg);
   }

    @Test
   public void assertAuthor(){
       msg.setAuthor("author");
       assertMessageText(msg);
   }

   @Test
   public void testFactoryMethod(){
       msg = Message.create("message","author");
       assertMessageText(msg);
       assertMessage(msg);
   }

    private void assertMessage(Message m) {
        assertEquals("message",m.get("message"));
        assertEquals("message",m.getMessage());
    }

    private void assertMessageText(Message m) {
        assertEquals("author",m.get("author"));
        assertEquals("author",m.getAuthor());
    }

}
