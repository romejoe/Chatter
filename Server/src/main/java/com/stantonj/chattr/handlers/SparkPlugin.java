package com.stantonj.chattr.handlers;

import com.stantonj.chattr.message.StringMessage;

import static com.stantonj.chattr.channel.ChannelRegistry.GetChannel;
import static spark.Spark.post;

/**
 * Created by jstanton on 5/3/15.
 */
public class SparkPlugin {
    public SparkPlugin() {
        post("/Spark/:ChannelName", (req, res) -> {
            StringMessage msg = new StringMessage(req.body(), null);


            GetChannel(req.params(":ChannelName")).PostMessage(msg);

            return "";
        });
    }


}
