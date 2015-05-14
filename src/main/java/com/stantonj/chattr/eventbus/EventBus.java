package com.stantonj.chattr.eventbus;

public abstract class EventBus {

    public abstract void Publish(Object event);

    public abstract void Subscribe(Object subscriber);

    public abstract void UnSubscribe(Object subscriber);

}
