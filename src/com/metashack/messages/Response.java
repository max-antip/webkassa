package com.metashack.messages;


import java.util.List;

public class Response {

    public Data Data;

    public List<Error> Errors;


    public static class Data {
        public String Token;
    }


}
