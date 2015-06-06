package com.stantonj.chattr.channel;

import com.stantonj.chattr.event.MessageCreationEvent;
import com.stantonj.chattr.event.MessageModificationEvent;
import com.stantonj.chattr.event.UpdateMessageEvent;
import com.stantonj.chattr.eventbus.EventBus;
import com.stantonj.chattr.eventbus.EventBusRegistry;
import com.stantonj.chattr.handlers.MessageHandler;
import com.stantonj.chattr.message.Message;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Joey on 5/13/15.
 */
public class Channel {

    private String ChannelID;
    private EventBus eventBus;
    private Set<Object> MessageHandlers;


    public Channel(String ChannelID){
        assert ChannelID != null && !ChannelID.isEmpty();

        this.ChannelID = ChannelID;
        eventBus = EventBusRegistry.GetOrCreateBus(ChannelID);
        MessageHandlers = new HashSet<>();


        ChannelRegistry.RegisterChannel(this);
        assert (this.ChannelID != null && !this.ChannelID.isEmpty()) && this.eventBus != null;
    }

    public String GetChannelID(){
        return ChannelID;
    }

    public void PostMessage(Message newMessage){
        assert newMessage != null;
        MessageModificationEvent e;
        e = new MessageCreationEvent(newMessage);
        eventBus.Publish(e);
    }

    public void UpdateMessage(Message newMessage){
        assert newMessage != null;
        UpdateMessageEvent e;
        e = new UpdateMessageEvent(newMessage);
        eventBus.Publish(e);
    }

    public void RegisterMessageHandler(Object handler){
        assert handler != null && !MessageHandlers.contains(handler);

        eventBus.Subscribe(handler);
        MessageHandlers.add(handler);

        assert MessageHandlers.contains(handler);
    }

    public void UnregisterMessageHandler(Object handler){
        assert handler != null && MessageHandlers.contains(handler);

        eventBus.UnSubscribe(handler);
        MessageHandlers.remove(handler);

        assert !MessageHandlers.contains(handler);
    }
}
