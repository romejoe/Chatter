package com.stantonj.chattr.event;

import com.stantonj.chattr.message.Message;

import java.util.Date;

/**
 * Created by Joey on 5/13/15.
 */
public class MessageModificationEvent {

    private Message message;
    private Date timeStamp;

    public Message getMessage(){
        return message;
    }

    public Date getTimeStamp(){
        return timeStamp;
    }

    public MessageModificationEvent(Message message) {
        assert message != null;
        timeStamp = new Date();
        this.message = message;
    }
}
