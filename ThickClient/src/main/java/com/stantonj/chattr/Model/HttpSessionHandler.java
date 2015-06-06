package com.stantonj.chattr.Model;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

import com.google.gson.Gson;
import com.stantonj.chattr.Security.Authenticator;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joey on 5/31/15.
 */
public class HttpSessionHandler implements ChattrSessionHandler{
    @Override
    public boolean SupportUri(URL url) {
        return url.getProtocol().equals("https")
                || (url.getProtocol().equals("http") && (url.getHost().equals("localhost") || url.getHost().equals("127.0.0.1")));
    }

    @Override
    public ChattrSession openSession(URL url, Authenticator authenticator) throws AuthenticationException, IOException {

        Map credentials = new HashMap<>();
        credentials.put("Username", authenticator.getUserName());
        credentials.put("Password", authenticator.getPassword());


        String json = new Gson().toJson(credentials);

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost loginRequest;
        try {
            loginRequest = new HttpPost(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        CloseableHttpResponse resp = httpclient.execute(loginRequest);
        if(resp.getStatusLine().getStatusCode() != 200 )
            return null;
        return null;
        //return new ChattrSession(authenticator.getUserName(), )

    }
}
