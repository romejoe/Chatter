package com.stantonj.chatter.channel;

import com.stantonj.chatter.message.Message;
import com.stantonj.chatter.message.StringMessage;

/**
 * Created by jstanton on 5/3/15.
 */
public class ConsoleEchoPlugin extends ChannelPlugin {

    @Override
    public void ReceiveMessage(Message msg) {

        if (!StringMessage.class.isAssignableFrom(msg.getClass()))
            return;
        System.out.println(((StringMessage) msg).getMessage());

    }
}
