package com.github.kelly.http.request;

/** todo
 *  클라이언트 ----> request 읽기 ( GET /url HTTP/1.1 )
 *  1.method
 *  2.url
 *  3.프로토콜
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
