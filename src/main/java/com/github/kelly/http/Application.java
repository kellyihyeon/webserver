package com.github.kelly.http;


import com.github.kelly.http.request.HttpRequestHandler;

public class Application {

    public static void main(String[] args) {
        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
        httpRequestHandler.run();
    }

}
