package com.github.kelly.webserver;

/**
 * Application 의 main thread 는 web server 를 구동시킨다.
 */
public class Application {

    public static void main(String[] args) {
        Webserver webserver = new Webserver();
        new Thread(webserver).start();


//        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
//        httpRequestHandler.run();
    }

}
