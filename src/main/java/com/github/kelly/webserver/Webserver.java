package com.github.kelly.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 웹 서버는 서버 소켓을 생성하고 사용자의 요청이 있을 때마다 소켓을 연결한다.
 * 이 소켓을 request handler 의 생성자로 전달해준다.
 * <p>
 * Webserver Thread 는 대기 상태를 유지하다가
 * connection 이 들어올 때마다 HttpRequestHandler 의 작업을 처리할 수 있도록 task 를 thread pool 을 이용해 처리한다.
 */
public class Webserver implements Runnable {

    ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(5, 7, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(30));


    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket connection;

            while ((connection = serverSocket.accept()) != null) {
                HttpRequestHandler requestHandler = new HttpRequestHandler(connection);
                threadPoolExecutor.execute(requestHandler);

                System.out.println("Webserver.run = " + Thread.currentThread().getName());      // webserver
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }

}


