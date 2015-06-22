package com.stantonj.chattr.endpoint;

import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stantonj.chattr.channel.Channel;
import com.stantonj.chattr.channel.ChannelRegistry;
import com.stantonj.chattr.event.MessageCreationEvent;
import com.stantonj.chattr.message.StringMessage;
import com.stantonj.chattr.user.User;
import com.stantonj.chattr.user.UserFactory;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.*;

@Log4j2
public class ChattrWebSocketListener implements WebSocketListener {

    User user;
    private RemoteEndpoint Endpoint;
    Set<Channel> listeningChannels = new HashSet<>();
    Map<Channel, MessageSender> channels = new HashMap<>();

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

        //Map authenticationRequest = new HashMap<>();
        //authenticationRequest.put("Username", Username);
        //authenticationRequest.put("Password", Password);


        Endpoint = session.getRemote();
        session.getUpgradeResponse().setSuccess(true);

    }

    @Override
    public void onWebSocketError(Throwable throwable) {

    }

    private void sendAuthenticationResults(boolean results) throws IOException {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("EventType", "AuthenticationResponse");
        toSend.put("Results", results);
        this.Endpoint.sendString(new Gson().toJson(toSend));
    }


    private void preAuthenticationHandle(Map<String, Object> data){
        String username = (String) data.get("UserName");
        String authentication = (String) data.get("Authentication");
        if(username == null)
            return;

        try {
            user = UserFactory.GetInstance().AuthenticateUser(username, authentication);
            sendAuthenticationResults(user != null);
        } catch (AuthenticationException e) {
            log.error("Authentication failed", e);
        } catch (IOException e) {
            log.error("Send authentication results failed", e);
        }

    }


    @Override
    public void onWebSocketText(String s) {

        Gson gson = new Gson();
        Map<String,Object> map = gson.fromJson(s, new TypeToken<Map<String, Object>>(){}.getType());
        Channel ch = null;

        if(user == null){
            preAuthenticationHandle(map);
            return;
        }


        if(map.containsKey("ChannelId"))
            ch = ChannelRegistry.GetChannel(map.get("ChannelId").toString());



        switch(map.get("EventType").toString()){

            case "ListenToChannel": {
                assert ch != null;
                MessageSender ms = new MessageSender(Endpoint, user);

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
                StringMessage msg = new StringMessage(map.get("Message").toString(), user);

                ch.PostMessage(msg);
                break;
            }
            case "CreateChannel": {
                //TODO:channel access control list
                Channel newChannel = new Channel(map.get("ChannelId").toString());

                ChannelRegistry.RegisterChannel(newChannel);
                MessageSender ms = new MessageSender(Endpoint, user);

                newChannel.RegisterMessageHandler(ms);
                channels.put(newChannel, ms);

                StringMessage msg = new StringMessage("Well, hi there!!", user);

                newChannel.PostMessage(msg);

                break;
            }
            default:
                throw new NotImplementedException();
        }

    }
}
