package com.github.kelly.webserver.eventloop;

import com.github.kelly.webserver.HttpRequestHandler;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class EventLoop {

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


    public void processEvent() {
        Event event;
        if ((event = EventQueue.getInstance().getEvent()) != null) {
            System.out.printf("EventLoop.run - [%s] - working. \n", Thread.currentThread().getName());
            HttpRequestHandler httpRequestHandler = new HttpRequestHandler(event.getConnection());
            threadPoolExecutor.execute(httpRequestHandler);
        }


    }


}
