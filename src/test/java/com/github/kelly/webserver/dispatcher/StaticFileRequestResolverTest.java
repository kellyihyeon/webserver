package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.controller.Controller;
import com.github.kelly.webserver.controller.StaticFileController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("StaticFileRequestResolver - support(), resolve() 메소드 테스트")
class StaticFileRequestResolverTest {


    @Test
    @DisplayName("support(): 정의되지 않은 static file 을 요청하면 false 를 반환한다.")
    void support_메소드_테스트_정의되지_않은_static_file_요청() {
        HttpRequest wrongRequest = getWrongRequest();

        final StaticFileRequestResolver staticFileRequestResolver = new StaticFileRequestResolver();
        boolean maybeFalse = staticFileRequestResolver.support(wrongRequest);

        assertFalse(maybeFalse);
    }

    @Test
    @DisplayName("support(): 정의된 static file 을 요청하면 true 를 반환한다.")
    void support_메소드_테스트_정의된_static_file_요청() {
        HttpRequest request = getRequest();

        final StaticFileRequestResolver staticFileRequestResolver = new StaticFileRequestResolver();
        boolean maybeTrue = staticFileRequestResolver.support(request);

        assertTrue(maybeTrue);
    }

    @Test
    @DisplayName("resolve(): 정의되지 않은 static file 을 요청하면 null 을 반환한다.")
    void resolve_메소드_테스트_정의되지_않은_static_file_요청() {
        HttpRequest wrongRequest = getWrongRequest();

        final StaticFileRequestResolver staticFileRequestResolver = new StaticFileRequestResolver();
        Controller maybeNull = staticFileRequestResolver.resolve(wrongRequest);

        assertNull(maybeNull);
    }

    @Test
    @DisplayName("resolve(): 정의된 static file 을 요청하면 StaticFileController 를 반환한다.")
    void resolver_메소드_테스트_정의된_static_file_요청() {
        HttpRequest request = getRequest();

        final StaticFileRequestResolver staticFileRequestResolver = new StaticFileRequestResolver();
        final Controller controller = staticFileRequestResolver.resolve(request);

        assertEquals(StaticFileController.class, controller.getClass());
    }


    private HttpRequest getWrongRequest() {
        String wrongRequestMessage = "GET /helloWorld.html HTTP/1.1\r\n\r\n";
        InputStream inputStream = new ByteArrayInputStream(wrongRequestMessage.getBytes());
        return new HttpRequest(inputStream);
    }

    private HttpRequest getRequest() {
        String rawHttpRequest = "GET /home.html HTTP/1.1\r\n\r\n";
        InputStream inputStream = new ByteArrayInputStream(rawHttpRequest.getBytes());
        return new HttpRequest(inputStream);
    }


}