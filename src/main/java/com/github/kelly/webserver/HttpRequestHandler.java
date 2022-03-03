package com.github.kelly.webserver;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import com.github.kelly.controller.Controller;
import com.github.kelly.webserver.dispatcher.DispatcherServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * HTTP Request Handler
 * 사용자의 요청이 들어오면 handler 는 dispatcher 에게 요청에 맞는 컨트롤러를 반환할 것을 요청하고 반환 받은 컨트롤러를 실행시킨다.
 *
 */
public class HttpRequestHandler implements Runnable {

    private final Socket socket;
    private static final DispatcherServlet DISPATCHER_SERVLET = new DispatcherServlet();    // 요청에 맞는 컨트롤러를 반환하므로 객체가 계속 생성될 필요가 없다.


    public HttpRequestHandler(Socket connection) {
        this.socket = connection;
    }


    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            HttpRequest httpRequest = new HttpRequest(inputStream);
            HttpResponse httpResponse = new HttpResponse(outputStream);

            Controller controller = DISPATCHER_SERVLET.dispatch(httpRequest);
            controller.process(httpRequest, httpResponse);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
