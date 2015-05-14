package com.stantonj.chattr;

import com.stantonj.chattr.channel.Channel;
import com.stantonj.chattr.eventbus.GuavaEventBus;
import com.stantonj.chattr.handlers.ConsoleEchoPlugin;
import com.stantonj.chattr.handlers.SparkPlugin;

/**
 * Created by jstanton on 5/2/15.
 */
public class Main {

    public static void main(String[] args) {
        GuavaEventBus.init();
        String channelID = "testy";

        Channel channel = new Channel(channelID);

        SparkPlugin sparky = new SparkPlugin();
        ConsoleEchoPlugin echo = new ConsoleEchoPlugin();

        channel.RegisterMessageHandler(echo);

    }
}
