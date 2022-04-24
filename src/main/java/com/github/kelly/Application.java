package com.github.kelly;

import com.github.kelly.webserver.EventLoop;
import com.github.kelly.webserver.EventLoopTaskHandler;
import com.github.kelly.webserver.Webserver;

/**
 * Application 의 main thread 는 web server 를 구동시킨다.
 */
public class Application {

    public static void main(String[] args) {
        Webserver webserver = new Webserver();
        new Thread(webserver).start();

        // 이 코드는 실행 되지 않음
//        Thread threadForEventLoopTaskHandler = new Thread(EventLoopTaskHandler.getInstance());
//        while (!EventLoop.getInstance().getQueue().isEmpty()) {
//            threadForEventLoopTaskHandler.start();
//        }

        System.out.println("Application.main = " + Thread.currentThread().getName());
    }

}
