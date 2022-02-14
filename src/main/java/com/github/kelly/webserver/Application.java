package com.github.kelly.webserver;


public class Application {

    public static void main(String[] args) {
        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
        httpRequestHandler.run();
    }

}
