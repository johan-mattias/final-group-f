package uu.pss_group.f.codechat.view;

import java.util.List;

import uu.pss_group.f.codechat.controllers.MessageController;
import uu.pss_group.f.codechat.demo.Message;

/**
 * Created by rasmus on 2018-02-24.
 */

public interface MessageViewer {

    void setup();
    void register(MessageController messageController);
    void update(List<Message> messages);
}
