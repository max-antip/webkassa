package com.metashack.messages;


public class ChangeToken implements Message {

    public static final String URL="";

    public String token;
    public String cashBoxUniqueNumber;
    public String ofdToken;

    @Override
    public String getUrl() {
        return null;
    }
}
