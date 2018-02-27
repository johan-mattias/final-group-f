package uu.pss_group.f.codechat;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import uu.pss_group.f.codechat.controllers.ConversationController;
import uu.pss_group.f.codechat.database.ConversationDatabaseFetcher;
import uu.pss_group.f.codechat.view.ConversationViewer;

import static org.mockito.Mockito.*;

public class TestConversationController {
    ConversationController c;
    ConversationViewer mockView;
    ConversationDatabaseFetcher mockFetcher;

    @Before
    public void setup(){
        c = new ConversationController();
        mockView = mock(ConversationViewer.class);
        mockFetcher = mock(ConversationDatabaseFetcher.class);

        c.setView(mockView);
        c.setDatabase(mockFetcher);
    }

    @Test
    public void testSetupsAreCalled(){
        verify(mockView).setup();
        verify(mockFetcher).setup();
    }


    @Test
    public void testComponentsAreRegistered(){
        verify(mockView).register(c);
        verify(mockFetcher).registerConversationController(c);
    }

    @Test
    public void testFetchData(){
        List conversations = mock(List.class);

        c.fetchData("fakeConvId");

        verify(mockFetcher).fetch("fakeConvId");
    }

    @Test
    public void testSendData(){
        List conversations = mock(List.class);

        c.createConversation("senderID","recieverIdD");

        verify(mockFetcher).createConversation("senderID","recieverIdD");
    }

    @Test
    public void controllerUpdatingView(){
        List conversations = mock(List.class);

        c.updateView(conversations);
        verify(mockView).update(conversations);
    }

}