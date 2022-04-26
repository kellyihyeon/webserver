package com.github.kelly;

import com.github.kelly.webserver.Webserver;
import com.github.kelly.webserver.eventloop.EventLoop;

/**
 *  single thread             event queue
 *                          task1 task2 task3      thread1, thread2...
 *          event loop <->
 *      (thread pool- assign task to thread.)
 */
public class Application {

    public static void main(String[] args) {

        // event loop 실행 스레드
        new Thread(() ->
                EventLoop.getInstance().run()).start();

        // 요청 받는 스레드 (single thread - like node js)
        Webserver webserver = new Webserver();
        new Thread(webserver).start();

        System.out.println("Application.main = " + Thread.currentThread().getName());
    }

}
