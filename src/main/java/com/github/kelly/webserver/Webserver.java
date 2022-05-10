package com.github.kelly.webserver;

import com.github.kelly.webserver.eventloop.Event;
import com.github.kelly.webserver.eventloop.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;


public class Webserver implements Runnable{


    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket connection;

            while ((connection = serverSocket.accept()) != null) {
                System.out.printf("클라이언트의 요청 - [%s]\r\n", LocalDateTime.now());

                Event event = new Event(connection);
                EventQueue.getInstance().insertToEvent(event);

                System.out.printf("Webserver.run - [%s]\n", Thread.currentThread().getName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


