package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.webserver.controller.NotFoundController;
import com.github.kelly.webserver.controller.StaticFileController;
import com.github.kelly.controller.WelcomeController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RequestResolverManager 메소드 테스트: support(), resolve()")
class RequestResolverManagerTest {

    @Test
    @DisplayName("support(): 정의된 static file 을 요청하면 true 가 반환된다.")
    void support_메소드_테스트_정의된_static_file_요청() {
        final HttpRequest httpRequest = getHttpRequestFromDefinedStaticFile();

        RequestResolverManager requestResolverManager = new RequestResolverManager();
        boolean maybeTrue = requestResolverManager.support(httpRequest);

        assertTrue(maybeTrue);
    }

    @Test
    @DisplayName("support(): 정의되지 않은 static file 을 요청하면 false 가 반환된다.")
    void support_메소드_테스트_정의되지_않은_static_file_요청() {
        HttpRequest httpRequest = getHttpRequestFromNotDefinedStaticFile();

        RequestResolverManager requestResolverManager = new RequestResolverManager();
        boolean maybeFalse = requestResolverManager.support(httpRequest);

        assertFalse(maybeFalse);
    }

    @Test
    @DisplayName("support(): 정의된 resource 를 요청하면 true 가 반환된다.")
    void support_메소드_테스트_정의된_resource_요청() {
        HttpRequest httpRequest = getHttpRequestFromDefinedResource();

        RequestResolverManager requestResolverManager = new RequestResolverManager();
        boolean maybeTrue = requestResolverManager.support(httpRequest);

        assertTrue(maybeTrue);
    }

    @Test
    @DisplayName("support(): 정의되지 않은 resource 를 요청하면 false 가 반환된다.")
    void support_메소드_테스트_정의되지_않은_resource_요청() {
        HttpRequest httpRequest = getHttpRequestFromNotDefinedResource();

        RequestResolverManager requestResolverManager = new RequestResolverManager();
        boolean maybeFalse = requestResolverManager.support(httpRequest);

        assertFalse(maybeFalse);
    }


    @Test
    @DisplayName("resolve(): 정의 되지 않은 static file 일 경우 NotFoundController 가 반환된다.")
    void resolve_메소드_테스트_정의되지_않은_static_file_요청() {
        HttpRequest httpRequest = getHttpRequestFromNotDefinedStaticFile();

        RequestResolverManager requestResolverManager = new RequestResolverManager();

        assertEquals(NotFoundController.class, requestResolverManager.resolve(httpRequest).getClass());
    }

    @Test
    @DisplayName("resolve(): 정의된 static file 일 경우 사용자 정의 컨트롤러가 반환된다.")
    void resolve_메소드_테스트_정의된_static_file_요청() {
        HttpRequest httpRequest = getHttpRequestFromDefinedStaticFile();

        RequestResolverManager requestResolverManager = new RequestResolverManager();

        assertEquals(StaticFileController.class, requestResolverManager.resolve(httpRequest).getClass());
    }


    @Test
    @DisplayName("resolve(): 정의 되지 않은 resource 일 경우 NotFoundController 가 반환된다.")
    void resolve_메소드_테스트_정의되지_않은_resource_요청() {
        HttpRequest httpRequest = getHttpRequestFromNotDefinedResource();
        RequestResolverManager requestResolverManager = new RequestResolverManager();

        assertEquals(NotFoundController.class, requestResolverManager.resolve(httpRequest).getClass());
    }

    @Test
    @DisplayName("resolve(): 정의 된 resource 일 경우 resource 에 대응하는 컨트롤러가 반환된다.")
    void resolve_메소드_정의된_resource_요청() {
        HttpRequest httpRequest = getHttpRequestFromDefinedResource();

        RequestResolverManager requestResolverManager = new RequestResolverManager();

        assertEquals(WelcomeController.class, requestResolverManager.resolve(httpRequest).getClass());
    }


    // 정의된 스태틱 파일 or not
    // 정의된 리소스 or not
    private HttpRequest getHttpRequestFromDefinedStaticFile() {
        String httpRequestStr = "GET /home.html HTTP/1.1\r\n\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        return new HttpRequest(inputStream);
    }

    private HttpRequest getHttpRequestFromNotDefinedStaticFile() {
        String httpRequestStr = "GET /world.html HTTP/1.1\r\n\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        return new HttpRequest(inputStream);
    }

    private HttpRequest getHttpRequestFromDefinedResource() {
        String httpRequestStr = "GET /welcome HTTP/1.1\r\n\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        return new HttpRequest(inputStream);
    }

    private HttpRequest getHttpRequestFromNotDefinedResource() {
        String httpRequestStr = "GET /world HTTP/1.1\r\n\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(httpRequestStr.getBytes());
        return new HttpRequest(inputStream);
    }
}