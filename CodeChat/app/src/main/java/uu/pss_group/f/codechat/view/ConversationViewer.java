package uu.pss_group.f.codechat.view;

import java.util.List;

import uu.pss_group.f.codechat.controllers.ConversationController;

/**
 * Created by rasmus on 2018-02-21.
 */

public interface ConversationViewer {
    public void setup();

    public void update(List conversations);

    public void register(ConversationController c);
}
