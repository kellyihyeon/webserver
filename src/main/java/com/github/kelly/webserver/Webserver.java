package com.github.kelly.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


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

                System.out.println("Webserver.run = " + Thread.currentThread().getName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }

}


