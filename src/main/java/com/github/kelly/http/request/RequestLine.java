package com.github.kelly.http.request;

/** todo
 * query string 으로 넘긴 데이터 가져오기 -> /sighUp?userId=what&password=1234
 * query string 을 처리하는 객체 만들기
 */
public class RequestLine {

    private final HttpMethod httpMethod;
    private final String url;
    private final String protocol;

    final int METHOD_INDEX = 0;
    private final int URI_INDEX = 1;
    private final int PROTOCOL_INDEX = 2;



    public RequestLine(String line) {
        String[] components = line.split(" ");

        this.httpMethod = HttpMethod.valueOf(components[METHOD_INDEX]);
        this.url = components[URI_INDEX];
        this.protocol = components[PROTOCOL_INDEX];
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public String getProtocol() {
        return protocol;
    }
}
