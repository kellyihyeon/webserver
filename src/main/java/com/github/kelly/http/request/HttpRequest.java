package com.github.kelly.http.request;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 객체 지향 신경 쓰지 말고 우선 미션부터 구현하기
 * YAGNI! 필요하지 않을까 미리 생각하지 말고 지금 당장 필요 없으면 만들지 말 것
 */
public class HttpRequest {

    private RequestLine requestLine;
    private RequestHeaders requestHeaders;
    private Map<String, String> parameterMapForPostRequest = new HashMap<>();


    public HttpRequest(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            this.requestLine = new RequestLine(br.readLine());
            this.requestHeaders = new RequestHeaders(br);

            // post 요청 -> Request Headers 에 Content-Type, Content-Length 가 전달 된다. (form 에 적은 데이터)
            String contentType = requestHeaders.getHeader("Content-Type");
            String contentLength = requestHeaders.getHeader("Content-Length");
            System.out.println("contentType = " + contentType);
            System.out.println("contentLength = " + contentLength);

            if (contentLength != null) {
                if (contentType.equals("application/x-www-form-urlencoded")) {
                    char[] charBuffer = new char[Integer.parseInt(contentLength)];
                    br.read(charBuffer);
                    String parametersInRequestBody = String.valueOf(charBuffer);
                    System.out.println("parametersInRequestBody = " + parametersInRequestBody);
                    /**
                     * 드디어 body 를 읽었다!!! ㅠ_ㅜ...
                     * 뜯어서 map 에 담는 것부터 내일 하자!
                     */

                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * getRequestLine, getUrl, getHttpMethod
     */
    public RequestLine getRequestLine() {
        return requestLine;
    }

    public String getUrl() {
        return requestLine.getUrl();
    }

    public HttpMethod getHttpMethod() {
        return requestLine.getHttpMethod();
    }

    public Map<String, String> getQueryStringMap() {
        if (requestLine.getQueryString() != null) {
            return requestLine.getQueryString().getQueryStringMap();
        }
        return null;
    }

    public String getParameter(String key) {
        // controller 에서 parameter 의 value 가 필요할 때.
        // /welcome?username=hohobear&power=99   -> get 으로 파라미터 넘겼을 때  -> query string map 에서 꺼내오자.
        String parameterValue = requestLine.getParameterValue(key);
        if (parameterValue != null) {
            return parameterValue;
        }
        // /signup?userId=admin123&password=1234 -> post 로 파라미터 넘겼을 때   -> parameterMapForPostRequest 에서 꺼내오자.
        // 계속 null 이 나왔잖아.
        return parameterMapForPostRequest.get(key);

    }


    public RequestHeaders getRequestHeaders() {
        return requestHeaders;
    }
}
