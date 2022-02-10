package com.github.kelly.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HttpRequest 객체 생성 테스트")
public class HttpRequestTest {

    @Test
    @DisplayName("InputStream 생성자로 넘기면 객체가 생성된다.")
    void HttpRequest_객체_생성_테스트() {
        String header = "POST /httpRequest HTTP/1.1\r\n" +
                        "Host: localhost:8080\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Content-Type: html/text; charset=utf-8\r\n" +
                        "\r\n";

        InputStream inputStream = new ByteArrayInputStream(header.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);

        RequestLine requestLine = httpRequest.getRequestLine();
        RequestHeaders requestHeaders = httpRequest.getRequestHeaders();

        assertEquals(HttpMethod.POST, requestLine.getHttpMethod());
        assertEquals("/httpRequest", requestLine.getUrl());
        assertEquals("HTTP/1.1", requestLine.getProtocol());

        assertEquals("localhost:8080", requestHeaders.getHeader("Host"));
        assertEquals("keep-alive", requestHeaders.getHeader("Connection"));
        assertEquals("html/text; charset=utf-8", requestHeaders.getHeader("Content-Type"));
    }
}
