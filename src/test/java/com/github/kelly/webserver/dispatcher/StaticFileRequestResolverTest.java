package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.controller.Controller;
import com.github.kelly.webserver.controller.StaticFileController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("StaticFileRequestResolver - 컨트롤러 반환 테스트")
class StaticFileRequestResolverTest {

    private HttpRequest httpRequest;


    @BeforeEach
    @DisplayName("StaticFileRequestResolver 를 테스트 하기 위해 HttpRequest 객체를 생성한다.")
    void HttpRequest_객체_생성() {
        String requestMessage = "GET /home.html HTTP/1.1\r\n" +
                "Connection: keep-alive\r\n" +
                "Cookie: Idea-test=a7b8c9-a1aa\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(requestMessage.getBytes());
        httpRequest = new HttpRequest(inputStream);
    }

    @Test
    @DisplayName("test 전 HttpRequest 객체가 생성 되어있는지 확인한다.")
    void HttpRequest_객체_생성_테스트() {
        assertEquals("/home.html", httpRequest.getRequestLine().getUrl());
    }


    @Test
    @DisplayName("정의되지 않은 static file 을 요청하면 NullPointerException 예외가 발생한다.")
    void 정의하지_않은_static_file_요청() {
        HttpRequest wrongRequest = getWrongRequest();
        StaticFileRequestResolver staticFileRequestResolver = new StaticFileRequestResolver(wrongRequest);
        Controller maybeNull = staticFileRequestResolver.resolve();

        assertThrows(NullPointerException.class, () -> maybeNull.getClass());
//        assertThrows(NullPointerException.class, maybeNull::getClass);
    }

    @Test
    @DisplayName("정의되어 있는 static file 을 요청하면 StaticFileController 를 반환한다.")
    void 정의되어_있는_static_file_요청() {
        StaticFileRequestResolver staticFileRequestResolver = new StaticFileRequestResolver(httpRequest);
        Controller maybeController = staticFileRequestResolver.resolve();

        assertEquals(StaticFileController.class, maybeController.getClass());
    }


    private HttpRequest getWrongRequest() {
        String wrongRequestMessage = "GET /helloWorld.html HTTP/1.1\r\n" +
                "Connection: keep-alive\r\n" +
                "Cookie: Idea-test=a7b8c9-a1aa\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(wrongRequestMessage.getBytes());
        return new HttpRequest(inputStream);
    }


}