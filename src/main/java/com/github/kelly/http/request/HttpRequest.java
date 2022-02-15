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

    /**
     * todo - refactoring or not
     * 외부에서 접근 시 httpRequest.getRequestLine().getUrl(); httpRequest.getRequestLine().getHttpMethod();
     * request 에 접근 해서 requestLine 까지 가는데, requestLine 에까지 접근하게 두는 게 맞는가?
     * httpRequest 에서 getRequestUrl(), getRequestHttpMethod() 를 별도로 둬서 외부 객체가 requestLine 객체까지 접근 할 필요가 없게 두면 되지 않나?
     * @return
     */
    public RequestLine getRequestLine() {
        return requestLine;
    }

    public RequestHeaders getRequestHeaders() {
        return requestHeaders;
    }
}
