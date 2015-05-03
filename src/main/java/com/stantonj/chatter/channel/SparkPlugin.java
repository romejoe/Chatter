package com.stantonj.chatter.channel;

import com.stantonj.chatter.message.StringMessage;

import static spark.Spark.*;

/**
 * Created by jstanton on 5/3/15.
 */
public class SparkPlugin extends ChannelPlugin {
    public SparkPlugin() {

        post("/Spark/:ChannelName", (req, res)->{
            StringMessage msg = new StringMessage();
            msg.setMessage(req.body());
            PostMessage(req.params(":ChannelName"), msg);
            return "";
        });
    }


}
