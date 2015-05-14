package com.stantonj.chattr.event;

import com.stantonj.chattr.message.Message;

/**
 * Created by Joey on 5/13/15.
 */
public class MessageCreationEvent extends MessageModificationEvent {
    public MessageCreationEvent(Message newMessage) {
        super(newMessage);
    }
}
