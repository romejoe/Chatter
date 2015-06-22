package com.stantonj.chattr;

import com.stantonj.chattr.channel.Channel;
import com.stantonj.chattr.endpoint.ChattrWebSocketListener;
import com.stantonj.chattr.eventbus.GuavaEventBus;
import com.stantonj.chattr.handlers.ConsoleEchoPlugin;
import com.stantonj.chattr.handlers.SparkPlugin;

import javax.naming.OperationNotSupportedException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stantonj.chattr.user.HashMapDirectory;
import com.stantonj.chattr.user.User;
import com.stantonj.chattr.user.UserDirectory;
import com.stantonj.chattr.user.UserFactory;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import java.io.IOException;

/**
 * Created by jstanton on 5/2/15.
 */
@Log4j2
public class Main {






    //@SuppressWarnings("serial")
    @WebServlet(name = "MyEcho WebSocket Servlet", urlPatterns = { "/echo" })
    public static class MyEchoServlet extends WebSocketServlet {

        @Override
        public void configure(WebSocketServletFactory factory) {
            factory.getPolicy().setIdleTimeout(10000);
            factory.register(ChattrWebSocketListener.class);
        }
    }

    public static void SetupUserDirectories() throws OperationNotSupportedException {
        UserDirectory directory = new HashMapDirectory();
        directory.RegisterUser("test", "test");
        directory.RegisterUser("test2", "test");
        UserFactory.RegisterUserDirectory(directory);
    }


    public static void main(String[] args) throws Exception {

        GuavaEventBus.init();
        String channelID = "testy";

        SetupUserDirectories();

        Channel channel = new Channel(channelID);

        //SparkPlugin sparky = new SparkPlugin();
        ConsoleEchoPlugin echo = new ConsoleEchoPlugin();

        channel.RegisterMessageHandler(echo);
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.setResourceBase(".");

        server.setHandler(context);


        context.addServlet(new ServletHolder(new MyEchoServlet()), "/echo");


        server.start();
        server.join();
        //server.join();

    }
}
