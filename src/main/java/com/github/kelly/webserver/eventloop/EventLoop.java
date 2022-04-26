package com.github.kelly.webserver.eventloop;

import com.github.kelly.webserver.HttpRequestHandler;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class EventLoop implements Runnable{

    private static final EventLoop eventLoop = new EventLoop();
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                                                            7,
                                                            10,
                                                            TimeUnit.SECONDS,
                                                            new LinkedBlockingQueue<>(20));


    private EventLoop() {
        // singleton
    }

    public static EventLoop getInstance() {
        return eventLoop;
    }

    // event queue 에 추가된 task 를 하나씩 꺼내서 스레드에 할당.
    // 1 task != 1 스레드. thread pool 로 스레드 관리.
    @Override
    public void run() {
        System.out.printf("EventLoop.run - [%s]\n", Thread.currentThread().getName());

        Event event;
        while (true) {
            if ((event = EventQueue.getInstance().getEvent()) != null) {
                HttpRequestHandler httpRequestHandler = new HttpRequestHandler(event.getConnection());
                threadPoolExecutor.execute(httpRequestHandler);
            }
        }

    }


}
