package com.stantonj.chattr.endpoint;

import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stantonj.chattr.channel.Channel;
import com.stantonj.chattr.channel.ChannelRegistry;
import com.stantonj.chattr.event.MessageCreationEvent;
import com.stantonj.chattr.eventbus.EventBus;
import com.stantonj.chattr.handlers.MessageHandler;
import com.stantonj.chattr.message.StringMessage;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.*;

/**
 * Created by Joey on 6/2/15.
 */

public class ChattrWebSocketListener implements WebSocketListener {

    Object identity;
    private RemoteEndpoint Endpoint;
    Set<Channel> listeningChannels = new HashSet<>();
    Map<Channel, MessageSender> channels = new HashMap<>();

    private class MessageSender{
        private RemoteEndpoint Endpoint;
        private Object Me;
        private String ChannelId;

        public MessageSender(RemoteEndpoint endpoint, Object me){
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


    @Override
    public void onWebSocketBinary(byte[] bytes, int i, int i1) {

    }

    @Override
    public void onWebSocketClose(int i, String s) {

    }

    @Override
    public void onWebSocketConnect(Session session) {
        //String Username = session.getUpgradeRequest().getHeader("User");
        //String Password = session.getUpgradeRequest().getHeader("Pass");
        //assert Username != null && Password != null;

        String Username = String.valueOf(System.currentTimeMillis());
        //Map authenticationRequest = new HashMap<>();
        //authenticationRequest.put("Username", Username);
        //authenticationRequest.put("Password", Password);

        //TODO!:do authentication checks

        identity = Username;
        Endpoint = session.getRemote();
        session.getUpgradeResponse().setSuccess(true);

    }

    @Override
    public void onWebSocketError(Throwable throwable) {

    }



    @Override
    public void onWebSocketText(String s) {

        Gson gson = new Gson();
        Map<String,Object> map = gson.fromJson(s, new TypeToken<Map<String, Object>>(){}.getType());
        Channel ch = null;
        if(map.containsKey("ChannelId"))
            ch = ChannelRegistry.GetChannel(map.get("ChannelId").toString());


        switch(map.get("EventType").toString()){
            case "ListenToChannel": {
                assert ch != null;
                MessageSender ms = new MessageSender(Endpoint, identity);

                ch.RegisterMessageHandler(ms);
                channels.put(ch, ms);

                break;
            }
            case "UnListenToChannel": {
                assert ch != null;
                ch.UnregisterMessageHandler(channels.get(ch));
                channels.remove(ch);
                break;
            }
            case "WriteMessage": {
                assert ch != null;
                StringMessage msg = new StringMessage(map.get("Message").toString(), identity);

                ch.PostMessage(msg);
                break;
            }
            case "CreateChannel": {
                Channel newChannel = new Channel(map.get("ChannelId").toString());

                ChannelRegistry.RegisterChannel(newChannel);
                MessageSender ms = new MessageSender(Endpoint, identity);

                newChannel.RegisterMessageHandler(ms);
                channels.put(newChannel, ms);

                StringMessage msg = new StringMessage("Well, hi there!!", identity);

                newChannel.PostMessage(msg);

                break;
            }
            default:
                throw new NotImplementedException();
        }

    }
}
