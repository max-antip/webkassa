package com.metashack.messages;


public class XReport implements Message {
    public static final String URL = "";
    public String token;
    public String cashboxUniqueNumber;

    @Override
    public String getUrl() {
        return URL;
    }
}
