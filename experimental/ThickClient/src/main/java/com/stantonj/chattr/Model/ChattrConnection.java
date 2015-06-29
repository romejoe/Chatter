package com.stantonj.chattr.Model;

import java.net.URI;
import java.util.Map;

/**
 * Created by Joey on 6/20/15.
 */
public interface ChattrConnection {
    void Connect(URI path, Map<String, Object> authenticationParameters);


    ChattrState getState();
    void addEventHandler(StateChangeHandler handler);
    void removeEventHandler(StateChangeHandler handler);
    void close();
}
