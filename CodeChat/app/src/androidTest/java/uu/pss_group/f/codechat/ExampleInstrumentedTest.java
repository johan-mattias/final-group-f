package uu.pss_group.f.codechat;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.google.firebase.auth.FirebaseAuth;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import uu.pss_group.f.codechat.controllers.MessageController;
import uu.pss_group.f.codechat.database.FirebaseConversationFetcher;

import uu.pss_group.f.codechat.demo.Conversation;
import uu.pss_group.f.codechat.demo.Message;
import uu.pss_group.f.codechat.view.MainActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@MediumTest
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends InstrumentationTestCase{

    private static final String FIREBASE = "https://codechat-group-f.firebaseio.com/";
    MainActivity activity;

    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    public void waitAbit(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Before
    public void setup(){
        activity = rule.getActivity();
        FirebaseApp.initializeApp(activity.getApplicationContext());
        FirebaseAuth.getInstance().signInAnonymously();
    }

    @Test
    public void testRetrieveConversationWithMessages(){
        // Setup
        ConversationControllerMock mock = new ConversationControllerMock();
        FirebaseConversationFetcher fetcher = new FirebaseConversationFetcher();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        fetcher.registerConversationController(mock);

        DatabaseReference ref = database.getReference();

        // Creates an hardcoded conversation and inserts it into the database.
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> conversation = new HashMap<>();

        ArrayList<Message> msg = new ArrayList<>();
        msg.add(Message.create("aa","bb"));
        msg.add(Message.create("aa","bb"));

        conversation.put("id", "123");
        conversation.put("messages", msg);
        data.put("conversations/123", conversation);

        // Inserts and wait
        ref.updateChildren(data);
        waitAbit();

        // Fetch stored data and wait
        fetcher.fetch("123");
        waitAbit();

        // Retrieve convesation from mock-controller.
        List<Conversation> convs = mock.getConvs();
        assertNotNull(convs);
        assertEquals(1, convs.size());

        Conversation conv = convs.get(0);
        assertNotNull(conv);

        // Asserts ID
        assertEquals("123", conv.getId());

        // Asserts Messages
        ArrayList actual = conv.getMessages();
        assertEquals(msg.toString(), actual.toString());
    }

    @Test
    public void testCreateConversation(){
        FirebaseConversationFetcher fetcher = new FirebaseConversationFetcher();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        Conversation c = Conversation.create();
        c.setId("staticId");

        fetcher.createConversation(c, "unusedParameter");
        waitAbit();

        DatabaseReference ref = database.getReference("conversations/staticId");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Asserts that the object can be used as a 'Conversation' object.
                Conversation c = dataSnapshot.getValue(Conversation.class);
                assertNotNull(c);
                assertEquals("staticId",c.getId());

                // Asserts that the object can be used as a 'Map' object.
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                assertNotNull(map);
                assertEquals("staticId", map.get("id"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                fail("Request was cancelled...");
            }

        });

    }

    @Test
    public void testCreateMessage(){
        // When a message is posted in a conversation the following have to be verified.
        // 1. The message is inserted in the right conversation.
        // 2. The author is also registered as a member of said conversation

        FirebaseConversationFetcher fetcher = new FirebaseConversationFetcher();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        String convId = "fakeConvv";

        fetcher.postMessage(convId, "TheMessage","TheAuthor");
        waitAbit();

        // Asserts that the message is created.

        DatabaseReference ref = database.getReference("conversations/fakeConvv/");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Asserts that all messages inside the conversation contains the correct message and author.
                // Even though they are the same for all messages.
                for(DataSnapshot snap : dataSnapshot.getChildren()){

                    // Asserts that the object can be used in a 'non-map' format.
                    Message m = snap.getValue(Message.class);
                    assertNotNull(m);
                    assertEquals("TheMessage", m.getMessage());
                    assertEquals("TheAuthor", m.getAuthor());

                    // Assert that the object works in a 'map' format.
                    Map<String, Object> map = (Map<String, Object>) snap.getValue();
                    assertNotNull(map);
                    assertEquals("TheMessage", map.get("message"));
                    assertEquals("TheAuthor", map.get("author"));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                fail("Request was cancelled...");
            }

        });

        // Asserts that the author is registered as a member in the 'members' group.
        ref = database.getReference("members/fakeConvv/");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Asserts that all messages inside the conversation contains the correct message and author.
                // Even though they are the same for all messages.
                Map<String, Boolean> map = (Map<String, Boolean>) dataSnapshot.getValue();
                assertTrue(String.valueOf(true), map.get("TheAuthor"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                fail("Request was cancelled...");
            }

        });

    }

    @Test
    public void testRetrieveMessages(){
// Setup
        MessageControllerMock mock = new MessageControllerMock();

        FirebaseConversationFetcher fetcher = new FirebaseConversationFetcher();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference();
        fetcher.registerMessageController(mock);

        // Creates an hardcoded conversation and inserts it into the database.
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> conversation = new HashMap<>();

        ArrayList<Message> msg = new ArrayList<>();
        msg.add(Message.create("message1","author1"));
        msg.add(Message.create("message2","author2"));

        conversation.put("id", "newmessages");
        conversation.put("messages", msg);
        data.put("conversations/newmessages", conversation);

        // Inserts and wait
        ref.updateChildren(data);
        waitAbit();

        // Fetch stored messages in a conversation and wait
        fetcher.fetchMessages("newmessages");

        waitAbit();

        // Retrieve convesation from mock-controller.
        assertMessageIsCorrect(mock.getMsgs());
    }

    private void assertMessageIsCorrect(List<Message> messages) {
        assertEquals(2, messages.size());

        String message1 = messages.get(0).getMessage();
        String message2 = messages.get(1).getMessage();

        String author1 = messages.get(0).getAuthor();
        String author2 = messages.get(1).getAuthor();

        assertEquals("message1", message1);
        assertEquals("message2", message2);
        assertEquals("author1", author1 );
        assertEquals("author2", author2 );
    }

}
