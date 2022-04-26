package com.github.kelly.webserver;

import com.github.kelly.webserver.eventloop.Event;
import com.github.kelly.webserver.eventloop.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Webserver implements Runnable{


    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket connection;

            while ((connection = serverSocket.accept()) != null) {

                Event event = new Event(connection);
                EventQueue.getInstance().insertToEvent(event);


                System.out.println("Webserver.run = " + Thread.currentThread().getName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


