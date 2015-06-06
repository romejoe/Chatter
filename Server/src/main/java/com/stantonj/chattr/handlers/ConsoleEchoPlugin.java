package com.stantonj.chattr.handlers;

import com.google.common.eventbus.Subscribe;
import com.stantonj.chattr.event.MessageModificationEvent;
import com.stantonj.chattr.message.Message;
import com.stantonj.chattr.message.StringMessage;

/**
 * Created by jstanton on 5/3/15.
 */
public class ConsoleEchoPlugin implements MessageHandler {

    @Subscribe
    public void PrintMessage(MessageModificationEvent e) {
        assert e != null;

        StringBuilder builder = new StringBuilder();
        Message msg = e.getMessage();

        builder.append("[").append(e.getTimeStamp()).append("]");

        if (StringMessage.class.isAssignableFrom(msg.getClass())){
            builder.append(" ").append(((StringMessage)msg).getMessage());
        }

        System.out.println(builder);
    }
}
