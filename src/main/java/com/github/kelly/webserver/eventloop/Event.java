package com.github.kelly.webserver.eventloop;

import java.net.Socket;

public class Event {

    private final Socket connection;


    public Event(Socket clientConnection) {
        this.connection = clientConnection;
    }

    public Socket getConnection() {
        return connection;
    }
}
