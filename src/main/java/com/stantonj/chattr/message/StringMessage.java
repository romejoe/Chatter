package com.stantonj.chattr.message;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by jstanton on 5/3/15.
 */
public class StringMessage implements Message {
    @Setter
    @Getter
    private String Message;
}
