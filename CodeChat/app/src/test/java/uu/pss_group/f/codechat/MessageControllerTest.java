package uu.pss_group.f.codechat;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import uu.pss_group.f.codechat.controllers.MessageController;
import uu.pss_group.f.codechat.database.ConversationDatabaseFetcher;
import uu.pss_group.f.codechat.demo.Conversation;
import uu.pss_group.f.codechat.demo.Message;
import uu.pss_group.f.codechat.view.MessageViewer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rasmus on 2018-02-27.
 */

public class MessageControllerTest {

    @Test
    public void testViewIsUpdated(){
        MessageController c = new MessageController();
        MessageViewer mockViewer = mock(MessageViewer.class);

        c.setView(mockViewer);

        List<Message> messages = new ArrayList<>();
        c.updateView(messages);

        verify(mockViewer).update(messages);
    }

    @Test
    public void fetchData(){
        MessageController c = new MessageController();
        ConversationDatabaseFetcher mockFetcher = mock(ConversationDatabaseFetcher.class);
        Conversation conv = mock(Conversation.class);

        when(conv.getId()).thenReturn("id");
        c.setFetcher(mockFetcher);
        c.fetchData(conv);

        verify(conv).getId();
        verify(mockFetcher).fetch(conv.getId());
    }

    @Test
    public void testFetcherIsUsed(){
        MessageController c = new MessageController();
        ConversationDatabaseFetcher mockFetcher = mock(ConversationDatabaseFetcher.class);

        c.setFetcher(mockFetcher);
        verify(mockFetcher).registerMessageController(c);

        Conversation conv = mock(Conversation.class);
        when(conv.getId()).thenReturn("fakeID");
        c.createMessage(conv,"author","msg");

        verify(conv).getId();
        verify(mockFetcher).postMessage(conv.getId(),"author","msg");
    }
}
