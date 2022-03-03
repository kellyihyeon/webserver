package com.github.kelly.webserver;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 웹 서버는 서버 소켓을 생성하고 사용자의 요청이 있을 때마다 소켓을 연결한다.
 * 이 소켓을 request handler 의 생성자로 전달해준다.
 *
 * Webserver Thread 는 대기 상태를 유지하다가
 * connection 이 들어올 때마다 HttpRequestHandler 의 작업을 처리할 수 있도록 새로운 Thread 를 생성하고 구동시킨다.
 *
 */
public class Webserver implements Runnable {


    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket connection;

            // 사용자의 요청이 있을 때 요청을 처리한다. -> 여러 쓰레드가 요청을 처리하면 좋겠는데
            // webserver thread 는 connection 이 될 때마다 handler 작업을 처리하는 새로운 thread 를 생성해서 구동시킨다.
            while ((connection = serverSocket.accept()) != null) {
                // 사용자 요청마다 각각의 쓰레드를 부여받는다.
                // (사용자가 100번 요청하면 100개의 쓰레드가 생겨난다. -> 쓰레드 풀로 관리해야 할 필요가 있다.)
                // 각각의 쓰레드는 고유의 저장소가 필요할테니 스레드 로컬 또한 필요하다.
                Thread handlerThread = new Thread(new HttpRequestHandler(connection));
                handlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


