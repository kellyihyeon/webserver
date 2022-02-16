package com.github.kelly.webserver;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.webserver.controller.Controller;
import com.github.kelly.webserver.dispatcher.DispatcherServlet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DispatcherServlet 객체 생성 테스트")
class DispatcherServletTest {

    @ParameterizedTest
    @ValueSource(strings = {"POST /home HTTP/1.1\r\nHost: localhost:8080/home\r\n\r\n",
                            "GET /hello HTTP/1.1\r\nHost: localhost:8080/home\r\n\r\n"})
    @DisplayName("RequestKey(url, method) 중 정의해놓지 않은 url-method 정보가 들어오면 NotFoundController 가 반환된다.")
    void 정의되지_않은_RequestKey(String request) {
        InputStream inputStream = new ByteArrayInputStream(request.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);

        DispatcherServlet dispatcher = new DispatcherServlet(httpRequest);
        Controller controller = dispatcher.dispatch();

        String path = "com.github.kelly.webserver.controller";
        assertEquals(path + ".NotFoundController", controller.getClass().getName());
    }


    @Test
    @DisplayName("정의해놓은 url-method 정보가 들어오면 사용자 정의 컨트롤러가 반환된다.")
    @Disabled("dispatch() 메소드 아직 작업 중이므로 NotFoundController 만 반환되고 있음")
    void 사용자_정의_RequestKey() {
        String httpRequestStr = "GET /home HTTP/1.1\r\n" +
                                "Host: localhost:8080/home\r\n" +
                                "Connection: keep-alive\r\n" +
                                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        HttpRequest httpRequest = new HttpRequest(inputStream);

        DispatcherServlet dispatcher = new DispatcherServlet(httpRequest);
        Controller controller = dispatcher.dispatch();

        String path = "com.github.kelly.webserver.controller";
        assertEquals(path + ".HomeController", controller.getClass().getName());
    }

}