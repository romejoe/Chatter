package com.stantonj.chattr.channel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joey on 5/13/15.
 */
public class ChannelRegistry {

    private static Map<String, Channel> RegisteredChannels = new HashMap<>();

    public static Channel GetChannel(String id){
        assert id != null && RegisteredChannels.containsKey(id);
        return RegisteredChannels.get(id);
    }

    public static void RegisterChannel(Channel ch){
        assert ch != null && !RegisteredChannels.containsKey(ch.GetChannelID());
        RegisteredChannels.put(ch.GetChannelID(), ch);

        assert RegisteredChannels.containsKey(ch.GetChannelID());
    }

    public static void UnregisterChannel(Channel ch){
        assert ch != null && RegisteredChannels.containsKey(ch.GetChannelID());
        RegisteredChannels.remove(ch.GetChannelID());
        assert !RegisteredChannels.containsKey(ch.GetChannelID());
    }

    public static void UnregisterChannel(String channelID){
        assert channelID != null && RegisteredChannels.containsKey(channelID);
        RegisteredChannels.remove(channelID);
        assert !RegisteredChannels.containsKey(channelID);
    }
}
