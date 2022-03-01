package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.controller.Controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DispatcherServlet 객체 생성 테스트")
class DispatcherServletTest {

    @ParameterizedTest
    @ValueSource(strings = {"POST /welcome HTTP/1.1\r\nHost: localhost:8080\r\n\r\n",
                            "GET /hello HTTP/1.1\r\nHost: localhost:8080\r\n\r\n",
                            "POST /home HTTP/1.1\r\nHost: localhost:8080\r\n\r\n"})
    @DisplayName("정의하지 않은 url-method 정보가 들어오면 NotFoundController 가 반환된다.")
    void 정의되지_않은_url_method(String request) {

        InputStream inputStream = new ByteArrayInputStream(request.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);

        DispatcherServlet dispatcher = new DispatcherServlet(httpRequest);
        Controller controller = dispatcher.dispatch();

        String path = "com.github.kelly.webserver.controller";
        assertEquals(path + ".NotFoundController", controller.getClass().getName());
    }


    @Test
    @DisplayName("정의해놓은 url-method 정보가 들어오면 사용자 정의 컨트롤러가 반환된다.")
    void 정의해놓은_url_method() {
        String httpRequestStr = "GET /welcome HTTP/1.1\r\n" +
                                "Host: localhost:8080\r\n" +
                                "Connection: keep-alive\r\n" +
                                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);

        DispatcherServlet dispatcher = new DispatcherServlet(httpRequest);
        Controller controller = dispatcher.dispatch();

        String path = "com.github.kelly.controller";
        assertEquals(path + ".WelcomeController", controller.getClass().getName());
    }

}