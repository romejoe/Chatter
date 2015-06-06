package com.stantonj.chattr.Security;

import javax.security.sasl.AuthenticationException;

/**
 * Created by Joey on 5/31/15.
 */
public interface Authenticator {
    String getUserName() throws AuthenticationException;
    String getPassword() throws AuthenticationException;
}
