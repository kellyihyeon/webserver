package com.github.kelly.http.request;

import com.github.kelly.http.cookie.Cookie;
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

    @Test
    @DisplayName("request 의 getCustomCookie 메소드를 실행하여 쿠키를 제대로 반환하는지 테스트.")
    void getCustomCookie_메소드_테스트() {
        String header = "POST /httpRequest HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Content-Type: html/text; charset=utf-8\r\n" +
                "Cookie: Idea-admin=asdf-asdf-abcd-eqfw-qefx3xqw4tf; YH_COOKIE=2d1672d2-e0b5-45ec-928f-c3da110ae44f\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(header.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);

        Cookie ideaCookie = httpRequest.getCustomCookie("Idea-admin");
        Cookie yhCookie = httpRequest.getCustomCookie("YH_COOKIE");

        String expectedIdeaCookieValue = "asdf-asdf-abcd-eqfw-qefx3xqw4tf";
        String expectedYhCookieValue = "2d1672d2-e0b5-45ec-928f-c3da110ae44f";

        assertEquals(expectedIdeaCookieValue, ideaCookie.getValue());
        assertEquals(expectedYhCookieValue, yhCookie.getValue());
    }
}
