package com.github.kelly.http.request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 객체 지향 신경 쓰지 말고 우선 미션부터 구현하기
 * YANGNI! 필요하지 않을까 미리 생각하지 말고 지금 당장 필요 없으면 만들지 말 것
 */
public class HttpRequest {

    private RequestLine requestLine;

    private RequestHeaders requestHeaders;



    // runnable 구현 받은 메서드에서 request 실행
    public HttpRequest() {
        try (ServerSocket serverSocket = new ServerSocket(8080)){

            Socket connection;
            while ((connection = serverSocket.accept()) != null) {
                InputStream inputStream = connection.getInputStream();
                OutputStream outputStream = connection.getOutputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                this.requestLine = new RequestLine(br.readLine());
                this.requestHeaders = new RequestHeaders(br);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
