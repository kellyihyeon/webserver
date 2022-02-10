package com.github.kelly.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RequestHeaders 객체 생성 테스트")
class RequestHeadersTest {

    @Test
    @DisplayName("BufferedReader 를 생성자에 넘기면 객체가 생성된다.")
    void 요청헤더_객체_생성_테스트() {
        String header = "Host: localhost:8080\r\n" +
                        "Connection: keep-alive\r\n" +
                        "Content-Type: html/text; charset=utf-8\r\n" +
                        "Content-Length: 483\r\n" +
                        "\r\n";

        InputStream inputStream = new ByteArrayInputStream(header.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            RequestHeaders requestHeaders = new RequestHeaders(br);
            assertEquals("localhost:8080", requestHeaders.getHeader("Host"));
            assertEquals("keep-alive", requestHeaders.getHeader("Connection"));
            assertEquals("html/text; charset=utf-8", requestHeaders.getHeader("Content-Type"));
            assertEquals("483", requestHeaders.getHeader("Content-Length"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}