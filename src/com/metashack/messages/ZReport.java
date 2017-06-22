package com.metashack.messages;

public class ZReport implements Message {
    public String token;
    public String cashboxUniqueNumber;
    public static final String URL = "";


    @Override
    public String getUrl() {
        return URL;
    }
}
