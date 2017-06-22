package com.metashack;


import com.google.gson.GsonBuilder;
import com.metashack.messages.Check;
import com.metashack.messages.Logon;
import com.metashack.messages.Message;
import com.metashack.messages.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Webkassa {

    private String host;
    private int port;
    private CloseableHttpClient httpClient;
    private GsonBuilder gsonBuilder;

    public Webkassa(String host) {
        this.host = host;
        httpClient = HttpClients.custom().build();
        gsonBuilder = new GsonBuilder();

    }

    public HttpResponse logon(String login, String pass) throws IOException {
        Logon logon = new Logon(login, pass);
        HttpPost post = getHttpPost(logon);
        return httpClient.execute(post);

    }


    public HttpResponse sendCheck(Check check) throws IOException {
        HttpPost post = getHttpPost(check);
        return httpClient.execute(post);

    }

    private HttpPost getHttpPost(Message mess) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(host + mess.getUrl());
        String content = gsonBuilder.create().toJson(mess);
        post.setHeader("Content-Type", "application/json");
        HttpEntity entity = new ByteArrayEntity(content.getBytes("UTF-8"));
        post.setEntity(entity);
        return post;
    }

    public Response getResponse(String strResp) {
        return gsonBuilder.create().fromJson(strResp, Response.class);
    }


}
