package com.github.kelly;

import com.github.kelly.webserver.Webserver;

/**
 * Application 의 main thread 는 web server 를 구동시킨다.
 */
public class Application {

    public static void main(String[] args) {
        Webserver webserver = new Webserver();
        new Thread(webserver).start();
        System.out.println("Application.main = " + Thread.currentThread().getName());
    }

}
