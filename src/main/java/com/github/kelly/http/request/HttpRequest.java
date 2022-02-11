package com.github.kelly.http.request;

import java.io.*;

/**
 * 객체 지향 신경 쓰지 말고 우선 미션부터 구현하기
 * YAGNI! 필요하지 않을까 미리 생각하지 말고 지금 당장 필요 없으면 만들지 말 것
 */
public class HttpRequest {

    private RequestLine requestLine;

    private RequestHeaders requestHeaders;


    public HttpRequest(InputStream inputStream) {

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            this.requestLine = new RequestLine(br.readLine());
            this.requestHeaders = new RequestHeaders(br);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public RequestHeaders getRequestHeaders() {
        return requestHeaders;
    }
}
