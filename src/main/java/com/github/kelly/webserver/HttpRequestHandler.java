package com.github.kelly.webserver;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import com.github.kelly.controller.Controller;
import com.github.kelly.webserver.dispatcher.DispatcherServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;


public class HttpRequestHandler implements Runnable {

    private final Socket socket;
    private static final DispatcherServlet DISPATCHER_SERVLET = new DispatcherServlet();


    public HttpRequestHandler(Socket connection) {
        this.socket = connection;
    }


    @Override
    public void run() {
        try {
            System.out.printf("HttpRequestHandler.run - [%s]\n", Thread.currentThread().getName());

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            HttpRequest httpRequest = new HttpRequest(inputStream);
            HttpResponse httpResponse = new HttpResponse(outputStream);

            Controller controller = DISPATCHER_SERVLET.dispatch(httpRequest);
            controller.process(httpRequest, httpResponse);
            System.out.printf("서버 응답 - [%s]\r\n", LocalDateTime.now());

        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }
}
