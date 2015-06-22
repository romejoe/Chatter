package com.stantonj.chattr.endpoint;

import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;
import com.stantonj.chattr.event.MessageCreationEvent;
import com.stantonj.chattr.user.User;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joey on 6/20/15.
 */
public class MessageSender {
    private RemoteEndpoint Endpoint;
    private User Me;
    private String ChannelId;

    public MessageSender(RemoteEndpoint endpoint, User me){
        Endpoint = endpoint;
        Me = me;
    }

    @Subscribe
    public void MessageCreated(MessageCreationEvent event) throws IOException {
//            if(event.getMessage().getAuthor() == Me)
        //return;

        Map<String, Object> toSend = new HashMap<>();

        //toSend.put("Author", event.getMessage().getAuthor());
        toSend.put("Message", event.getMessage());
        toSend.put("Timestamp", event.getTimeStamp());
        toSend.put("ChannelId", ChannelId);
        Endpoint.sendString(new Gson().toJson(toSend));
    }
}
