package com.github.kelly;

import com.github.kelly.webserver.Webserver;
import com.github.kelly.webserver.eventloop.EventLoop;


public class Application {

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                EventLoop.getInstance().processEvent();
            }
        }).start();


        Webserver webserver = new Webserver();
        new Thread(webserver).start();

        System.out.println("Application.main = " + Thread.currentThread().getName());
    }

}
