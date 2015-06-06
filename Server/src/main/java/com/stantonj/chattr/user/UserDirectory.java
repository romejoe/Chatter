package com.stantonj.chattr.user;

import javax.security.sasl.AuthenticationException;

/**
 * Created by Joey on 6/1/15.
 */
public interface UserDirectory {

    Long AuthenticateUser(Object obj) throws AuthenticationException;

}
