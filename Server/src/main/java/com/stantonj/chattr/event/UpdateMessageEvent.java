package com.stantonj.chattr.event;

import com.stantonj.chattr.message.Message;

/**
 * Created by Joey on 5/13/15.
 */
public class UpdateMessageEvent extends MessageModificationEvent {
    public UpdateMessageEvent(Message newMessage) {
        super(newMessage);
    }
}
