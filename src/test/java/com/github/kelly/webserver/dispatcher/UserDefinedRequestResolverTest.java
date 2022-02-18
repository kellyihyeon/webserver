package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.controller.Controller;
import com.github.kelly.controller.WelcomeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserDefinedRequestResolver - 컨트롤러 반환 테스트")
class UserDefinedRequestResolverTest {

    private HttpRequest httpRequest;


    @BeforeEach
    @DisplayName("UserDefinedRequestResolver 를 테스트 하기 위해 HttpRequest 객체를 생성한다.")
    void HttpRequest_객체_생성() {
        String requestMessage = "GET /welcome HTTP/1.1\r\n" +
                                "Connection: keep-alive\r\n" +
                                "Cookie: Idea-test=a7b8c9-a1aa\r\n" +
                                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(requestMessage.getBytes());
        httpRequest = new HttpRequest(inputStream);
    }

    @Test
    @DisplayName("test 전 HttpRequest 객체가 생성 되어있는지 확인한다.")
    void HttpRequest_객체_생성_테스트() {
        assertEquals("/welcome", httpRequest.getRequestLine().getUrl());
    }


    @ParameterizedTest
    @ValueSource(strings = {"DELETE /welcome HTTP/1.1\r\n",
                            "GET /welcomeWorld HTTP/1.1\r\n"})
    @DisplayName("정의 되지 않은 method 또는 url 를 요청하면 NullPointerException 이 발생한다.")
    void 정의_되지_않은_url_or_method_요청(String requestLine) {
        HttpRequest wrongRequest = getWrongRequest(requestLine);

        UserDefinedRequestResolver userDefinedRequestResolver = new UserDefinedRequestResolver(wrongRequest);
        Controller maybeNull = userDefinedRequestResolver.resolve();

        assertThrows(NullPointerException.class, () -> maybeNull.getClass());
    }


    @Test
    @DisplayName("정의 되어있는 method-url 을 요청하면 해당 Controller 가 반환된다.")
    void 정의_되어있는_url_요청() {
        UserDefinedRequestResolver userDefinedRequestResolver = new UserDefinedRequestResolver(httpRequest);
        Controller maybeController = userDefinedRequestResolver.resolve();

        assertEquals(WelcomeController.class, maybeController.getClass());
    }


    private HttpRequest getWrongRequest(String requestLine) {
        String wrongRequestMessage = requestLine +
                "Connection: keep-alive\r\n" +
                "Cookie: Idea-test=a7b8c9-a1aa\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(wrongRequestMessage.getBytes());
        return new HttpRequest(inputStream);
    }
}