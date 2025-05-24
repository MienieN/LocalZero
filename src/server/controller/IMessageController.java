package server.controller;

import shared.CommunityMessage;
import shared.IMessage;

public interface IMessageController {
    void sendMessage(CommunityMessage message);
}
