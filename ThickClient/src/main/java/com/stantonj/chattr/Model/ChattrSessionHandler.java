package com.stantonj.chattr.Model;

import com.stantonj.chattr.Security.Authenticator;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

import java.net.URL;

/**
 * Created by Joey on 5/31/15.
 */
public interface ChattrSessionHandler {
    boolean SupportUri(URL url);
    ChattrSession openSession(URL url, Authenticator authenticator) throws AuthenticationException, IOException;
}
