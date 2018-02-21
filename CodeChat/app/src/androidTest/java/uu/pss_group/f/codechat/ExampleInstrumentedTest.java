package uu.pss_group.f.codechat;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import uu.pss_group.f.codechat.database.FirebaseConversationFetcher;
import uu.pss_group.f.codechat.domain.Conversation;
import uu.pss_group.f.codechat.controllers.ConversationController;
import uu.pss_group.f.codechat.view.ConversationViewer;
import uu.pss_group.f.codechat.view.MainActivity;

import static org.junit.Assert.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.*;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@MediumTest
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends InstrumentationTestCase{

    private static final String FIREBASE = "https://codechat-group-f.firebaseio.com/";

    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCreateConversation() throws Exception {
        MainActivity activity = rule.getActivity();
        FirebaseApp.initializeApp(activity.getApplicationContext());
        FirebaseAuth.getInstance().signInAnonymously();

        FirebaseConversationFetcher fetcher = new FirebaseConversationFetcher();

        fetcher.createConversation("fakeSenderID","fakeRecieverId");

    }


    @Test
    public void testRetrieveConversation(){

//        System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getTargetContext().getCacheDir().getPath());

        MainActivity activity = rule.getActivity();
        FirebaseApp.initializeApp(activity.getApplicationContext());
        FirebaseAuth.getInstance().signInAnonymously();

        FirebaseConversationFetcher fetcher = new FirebaseConversationFetcher();
        fetcher.fetch("test/conversations/fakeSenderID");

        ConversationControllerMock controllerMock = new ConversationControllerMock();
        fetcher.register(controllerMock);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(controllerMock.countConversations() == 1);
        //assertTrue(true);


    }

}
