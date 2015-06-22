package com.stantonj.chattr.user;

import javax.naming.OperationNotSupportedException;
import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joey on 6/20/15.
 */
public class HashMapDirectory implements UserDirectory{

    Map<String, Object> passwords = new HashMap<>();
    Map<String, User> users = new HashMap<>();

    @Override
    public User AuthenticateUser(String username, Object authentication) throws AuthenticationException {
        if(passwords.containsKey(username) && passwords.get(username).equals(authentication))
            return users.get(username);
        return null;
    }

    @Override
    public User RegisterUser(String username, Object authentication) throws OperationNotSupportedException {
        users.put(username, new User(username));
        passwords.put(username, authentication);
        return users.get(username);
    }
}
