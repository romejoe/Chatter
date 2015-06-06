package com.stantonj.chattr.Model;

import com.stantonj.chattr.Security.Authenticator;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Joey on 5/31/15.
 */
public class ChattrSessionFactory {
    static Set<ChattrSessionHandler> handlers = new HashSet<>();
    static boolean RegisterSessionHandler(ChattrSessionHandler handler){
        return handlers.add(handler);
    }

    static Collection<ChattrSessionHandler> getHandlersForUrl(URL url){
        Collection<ChattrSessionHandler> ret = new ArrayList<>();

        for(ChattrSessionHandler handler : handlers){
            if(handler.SupportUri(url))
                ret.add(handler);
        }

        return ret;
    }

    public static ChattrSession getSessionForURL(URL url, Authenticator authenticator) throws IOException {
        Collection<ChattrSessionHandler> handlers = ChattrSessionFactory.getHandlersForUrl(url);
        ChattrSession newSession;
        for(ChattrSessionHandler handler : handlers){
            newSession = handler.openSession(url, authenticator);
            if(newSession != null)
                return newSession;
        }
        return null;
    }

}
