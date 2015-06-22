package com.stantonj.chattr.Model;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Joey on 6/20/15.
 */

@WebSocket(maxTextMessageSize = 64*1024)
public class WebSocketConnection implements ChattrConnection {

    private ChattrState state = ChattrState.Closed;

    private Session session;

    Collection<StateChangeHandler> handlers = new HashSet<>();

    private void changeState(ChattrState newState){
        if(state == newState)
            return;

        state = newState;

    }


    @OnWebSocketConnect
    public void onConnect(Session session){
        this.session = session;
    }

    @Override
    public void Connect(URI path, Map<String, Object> authenticationParameters) {
        WebSocketClient client = new WebSocketClient();
        try {
            client.start();
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(this, path, request);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ChattrState getState() {
        return null;
    }

    @Override
    public void addEventHandler(StateChangeHandler handler) {

    }

    @Override
    public void removeEventHandler(StateChangeHandler handler) {

    }

    @Override
    public void close() {

    }


}
