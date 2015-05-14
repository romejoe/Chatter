package com.stantonj.chattr.handlers;

import com.stantonj.chattr.eventbus.EventBus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Joey on 5/13/15.
 */
public class MessageHandler {
    @Setter
    @Getter(AccessLevel.PROTECTED)
    private EventBus handlerBus;

}
