package com.stantonj.chatter.eventbus;

/**
 * Created by jstanton on 5/2/15.
 */
public abstract class EventBus {

    public abstract void Publish(Object event);
    public abstract void Subscribe(Object subscriber);
    public abstract void UnSubscribe(Object subscriber);

}
