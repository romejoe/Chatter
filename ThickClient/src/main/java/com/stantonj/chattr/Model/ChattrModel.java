package com.stantonj.chattr.Model;

import java.beans.EventHandler;
import java.net.Authenticator;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Joey on 5/31/15.
 */
public class ChattrModel {
    ChattrSession session;

    Collection<StateChangeListener> stateChangeListeners = new HashSet<>();

    public ChattrModel(URL ChatterUrl, Authenticator authenticator){


    }

    public void RegisterStateListener(StateChangeListener listener){
        stateChangeListeners.add(listener);
    }

    public void UnregisterStateListener(StateChangeListener listener){
        stateChangeListeners.remove(listener);
    }




}
