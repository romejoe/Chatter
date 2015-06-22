package com.stantonj.chattr.user;

import javax.naming.OperationNotSupportedException;
import javax.security.sasl.AuthenticationException;

/**
 * Created by Joey on 6/1/15.
 */
public interface UserDirectory {

    User AuthenticateUser(String username, Object authentication) throws AuthenticationException;

    default User RegisterUser(String username, Object authentication) throws OperationNotSupportedException{
        throw new OperationNotSupportedException();
    }

}
