package com.metashack.messages;

public class Logon implements Message {
    public static final String URL = "authorize";
    public String login;
    public String password;

    public Logon(String login, String password) {
        this.login = login;
        this.password = password;
    }


    @Override
    public String getUrl() {
        return URL;
    }
}
