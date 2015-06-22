package com.stantonj.chattr.user;


import javax.security.sasl.AuthenticationException;
import java.util.HashSet;

import java.util.Set;

/**
 * Created by Joey on 6/1/15.
 */
public class UserFactory implements UserDirectory{


    private UserFactory(){

    }

    private static UserFactory _factory;



    private static Set<UserDirectory> directories = new HashSet<>();

    static public UserFactory GetInstance(){
        if(_factory == null)
            _factory = new UserFactory();
        return _factory;
    }

    static public void RegisterUserDirectory(UserDirectory directory){
        directories.add(directory);
    }

    @Override
    public User AuthenticateUser(String username, Object authentication) throws AuthenticationException {
        return _AuthenticateUser(username, authentication);
    }

    private User _AuthenticateUser(String username, Object auth) throws AuthenticationException{
        AuthenticationException ex = new AuthenticationException("All registered user directories failed to authenticate the user");
        for(UserDirectory dir: directories){
            try{
                return dir.AuthenticateUser(username, auth);
            }
            catch(AuthenticationException e){
                ex.addSuppressed(e);

            }
        }

        throw ex;
    }
}
