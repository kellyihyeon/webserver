package com.github.kelly.webserver;

import java.net.Socket;

// single thread         event loop  -> task         thread1(httpRequestHandler)
//                                      task         thread2
public class EventLoopTaskHandler implements Runnable{

    // 이벤트 루프로 들어온 task 를 하나씩 꺼내서 쓰레드에 할당해 주자.
    // task 마다 스레드에게 할당해 주는 게 아니라, 스레드 풀로 관리하면서 !
    private static final EventLoopTaskHandler handler = new EventLoopTaskHandler();


    private EventLoopTaskHandler() {
        // singleton
    }

    public static EventLoopTaskHandler getInstance() {
        return handler;
    }

    @Override
    public void run() {
            System.out.printf("EventLoopTaskHandler.run - [%s]\n", Thread.currentThread().getName());

            Socket task;
            while ((task = EventLoop.getInstance().getTask()) != null) {
                HttpRequestHandler requestHandler = new HttpRequestHandler(task);
                requestHandler.run();
            }

    }


}
