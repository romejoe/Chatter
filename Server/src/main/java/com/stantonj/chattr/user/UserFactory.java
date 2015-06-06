package com.stantonj.chattr.user;


import javax.security.sasl.AuthenticationException;
import java.util.HashSet;

import java.util.Set;

/**
 * Created by Joey on 6/1/15.
 */
public class UserFactory implements UserDirectory{


    private UserFactory _factory = new UserFactory();

    private static Set<UserDirectory> directories = new HashSet<>();

    static public void RegisterUserDirectory(UserDirectory directory){
        directories.add(directory);
    }

    @Override
    public Long AuthenticateUser(Object obj) throws AuthenticationException {
        return _AuthenticateUser(obj);
    }

    private Long _AuthenticateUser(Object obj) throws AuthenticationException{
        AuthenticationException ex = new AuthenticationException("All registered user directories failed to authenticate the user");
        for(UserDirectory dir: directories){
            try{
                return dir.AuthenticateUser(obj);
            }
            catch(AuthenticationException e){
                ex.addSuppressed(e);

            }
        }

        throw ex;
    }
}
