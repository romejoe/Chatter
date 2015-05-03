package com.stantonj.chatter.eventbus;

/**
 * Created by jstanton on 5/2/15.
 */
public class GuavaEventBus extends EventBus {

    static{
        init();
    }

    public static void init() {
        EventBusRegistry.RegisterEventBusType(GuavaEventBus.class);
    }

    private com.google.common.eventbus.EventBus delegate = new com.google.common.eventbus.EventBus();

    @Override
    public void Publish(Object event) {
        delegate.post(event);
    }

    @Override
    public void Subscribe(Object subscriber) {
        delegate.register(subscriber);
    }

    @Override
    public void UnSubscribe(Object subscriber) {
        delegate.unregister(subscriber);
    }

}
