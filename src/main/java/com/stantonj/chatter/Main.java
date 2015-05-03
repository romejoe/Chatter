package com.stantonj.chatter;

import com.stantonj.chatter.channel.ConsoleEchoPlugin;
import com.stantonj.chatter.channel.SparkPlugin;
import com.stantonj.chatter.eventbus.GuavaEventBus;

/**
 * Created by jstanton on 5/2/15.
 */
public class Main {

    public static void main(String[] args){
        String channelID="testy";
        GuavaEventBus.init();
        SparkPlugin sparky = new SparkPlugin();
        ConsoleEchoPlugin echo = new ConsoleEchoPlugin();

        sparky.JoinChannel(channelID);
        echo.JoinChannel(channelID);

    }
}
