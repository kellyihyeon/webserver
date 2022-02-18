package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.webserver.controller.StaticFileController;
import com.github.kelly.webserver.controller.WelcomeController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RequestResolverManager 메소드 테스트: support(), resolve()")
class RequestResolverManagerTest {

    @Test
    @DisplayName("support(): static file 을 요청하면 true 가 반환된다.")
    void static_file_요청() {
        String httpRequestStr = "GET /welcome.html HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);
        RequestResolverManager requestResolverManager = new RequestResolverManager(httpRequest);

        boolean support = requestResolverManager.support();
        assertTrue(support);
    }


    @Test
    @DisplayName("resolve(): 정의 되지 않은 static file 일 경우 null 이 반환된다.")
    void 정의되지_않은_static_file_요청() {
        String httpRequestStr = "GET /welcome.html HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);
        RequestResolverManager requestResolverManager = new RequestResolverManager(httpRequest);

        assertNull(requestResolverManager.resolve());
    }

    @Test
    @DisplayName("resolve(): 정의 된 static file 일 경우 사용자 정의 컨트롤러가 반환된다.")
    void 정의된_static_file_요청() {
        String httpRequestStr = "GET /home.html HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);
        RequestResolverManager requestResolverManager = new RequestResolverManager(httpRequest);

        assertEquals(StaticFileController.class, requestResolverManager.resolve().getClass());
    }

    @Test
    @DisplayName("support(): static file 이 아닌 resource 를 요청하면 false 가 반환된다.")
    void resource_요청() {
        String httpRequestStr = "GET /welcome HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);
        RequestResolverManager requestResolverManager = new RequestResolverManager(httpRequest);

        boolean support = requestResolverManager.support();
        assertFalse(support);
    }

    @Test
    @DisplayName("resolve(): 정의 되지 않은 resource 일 경우 null 이 반환된다.")
    void 정의되지_않은_resource_요청() {
        String httpRequestStr = "GET /homeSweetHome HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);
        RequestResolverManager requestResolverManager = new RequestResolverManager(httpRequest);

        assertNull(requestResolverManager.resolve());
    }

    @Test
    @DisplayName("resolve(): 정의 된 resource 일 경우 resource 에 대응하는 컨트롤러가 반환된다.")
    void 정의된_resource_요청() {
        String httpRequestStr = "GET /welcome HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);
        RequestResolverManager requestResolverManager = new RequestResolverManager(httpRequest);

        assertEquals(WelcomeController.class, requestResolverManager.resolve().getClass());
    }
}