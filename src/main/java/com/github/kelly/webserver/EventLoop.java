package com.github.kelly.webserver;

import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// single thread         event loop  -> task         thread1
//                                      task         thread2
public class EventLoop {

    // 클라이언트의 요청이 오면 싱글 스레드가 이 요청을 받아서 이벤트 루프로 넘겨준다.
    // 이벤트 루프는 이 요청을 이벤트 루프에 넣어둔다.
    // 이벤트 루프는 싱글톤이어야 한다. 싱글톤 인스턴스인 이벤트 루프에서 테스크를 하나씩 꺼내어 스레드에게 할당해준다.
    private static final EventLoop eventLoop = new EventLoop();
    private final Queue<Socket> queue = new LinkedBlockingQueue<>();


    private EventLoop() {
        // singleton
    }

    public static EventLoop getInstance() {
        return eventLoop;
    }

    public void insertToTask(Socket clientConnection) {
        queue.offer(clientConnection);
    }

    public Socket getTask() {
        if (!queue.isEmpty()) {
            return queue.poll();
        } else {
            return null;
        }
    }

}
