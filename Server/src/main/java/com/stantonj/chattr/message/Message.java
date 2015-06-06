package com.stantonj.chattr.message;

import java.io.Serializable;

/**
 * Created by jstanton on 5/2/15.
 */
public interface Message extends Serializable {
    Object getAuthor();
}
