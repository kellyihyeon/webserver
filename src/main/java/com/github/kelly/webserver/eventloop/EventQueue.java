package com.github.kelly.webserver.eventloop;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class EventQueue<T extends Event> {

    private static final EventQueue eventQueue = new EventQueue<>();

    private final Queue<T> queue = new LinkedBlockingQueue<>();


    private EventQueue() {
        // singleton
    }

    public static EventQueue getInstance() {
        return eventQueue;
    }

    public void insertToEvent(T event) {
        queue.offer(event);
    }

    public T getEvent() {
        if (!queue.isEmpty()) {
            return queue.poll();
        } else {
            return null;
        }
    }

}
