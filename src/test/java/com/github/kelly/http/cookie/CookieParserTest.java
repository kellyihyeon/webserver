package com.github.kelly.http.cookie;

import com.github.kelly.http.request.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("CookieParser 가 YH_COOKIE value 값을 추출하는지 테스트")
class CookieParserTest {

    private HttpRequest httpRequest;
    private HttpRequest httpRequestWithoutYhCookie;

    @BeforeEach
    @DisplayName("HttpRequest 객체 생성")
    void HttpRequest_객체_생성() {
        String rawRequest = "GET /httpRequest HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Content-Type: html/text; charset=utf-8\r\n" +
                "Cookie: Idea-admin=asdf-asdf-abcd-eqfw-qefx3xqw4tf; YH_COOKIE=2d1672d2-e0b5-45ec-928f-c3da110ae44f\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawRequest.getBytes());
        httpRequest = new HttpRequest(inputStream);
    }

    @BeforeEach
    @DisplayName("YH_COOKIE 가 없는 HttpRequest 객체 생성")
    void 특정_Cookie_Value가_없는_HttpRequest_객체_생성() {
        String rawRequest = "GET /httpRequest HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Content-Type: html/text; charset=utf-8\r\n" +
                "Cookie: Idea-admin=asdf-asdf-abcd-eqfw-qefx3xqw4tf\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawRequest.getBytes());
        httpRequestWithoutYhCookie = new HttpRequest(inputStream);
    }

    @Test
    @DisplayName("여러 개의 쿠키 중 cookie-name=YH_COOKIE 의 value 값을 추출한다.")
    void YH_COOKIE_value_추출_테스트() {
        String expectedValue = "2d1672d2-e0b5-45ec-928f-c3da110ae44f";
        String cookieValue = CookieParser.parseYhCookie(httpRequest);

        assertEquals(expectedValue, cookieValue);
    }

    @Test
    @DisplayName("cookie-name=YH_COOKIE 가 없는 경우 value 값으로 null 이 반환된다.")
    void YH_COOKIE_value가_없는_경우_null_반환() {
        assertNull(CookieParser.parseYhCookie(httpRequestWithoutYhCookie));
    }
}