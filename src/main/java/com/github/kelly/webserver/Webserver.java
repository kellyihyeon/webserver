package com.github.kelly.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 *  single thread         event loop  -> task         thread1
 *                                       task         thread2
 * 싱글 스레드는 요청을 받는다.
 * 요청을 받아서 이벤트 루프에 죄다 넘겨준다.
 *
 * -> 클라이언트가 요청을 하면 서버소켓에서 감지하고 연결을 한다. -> 싱글 스레드
 */
public class Webserver implements Runnable{

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
            7,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(20));


    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket connection;

            while ((connection = serverSocket.accept()) != null) {
                // 요청을 받아서 이 요청을 핸들러에 바로 넘겨준다. 그럼 핸들러를 처리하는 스레드가 요청부터 응답까지 책임지고 처리한다.
                // 여기서 핸들러에 바로 넘겨주는 게 아니라, 이벤트 루프로 요청을 넘겨주자.
                EventLoop.getInstance().insertToTask(connection);

                // 큐에 작업이 들어가 있다면?
                threadPoolExecutor.execute(new Thread(EventLoopTaskHandler.getInstance()));

                System.out.println("Webserver.run = " + Thread.currentThread().getName());
            }
            System.out.println("이 메시지는 안찍힐 것");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


