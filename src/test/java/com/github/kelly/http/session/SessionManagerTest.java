package com.github.kelly.http.session;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SessionManager 객체 생성 테스트")
class SessionManagerTest {


    @Test
    @DisplayName("세션 삭제 후, 세션 매니저가 세션을 가져올 때 세션 맵에서 삭제된 세션을 반환하는지, 새로운 세션을 생성해 반환하는지 테스트한다.")
    void removerSession_메소드_테스트() {
        // 테스트 대상이 되는 메소드의 반환 타입이 void 이기 때문에 세션 제거 후, 세션을 다시 가져올 때 일부러 제거된 세션의 id 를 넣어서 테스트
        HttpRequest httpRequest = getHttpRequest();
        HttpResponse httpResponse = getHttpResponse();

        Session session = SessionManager.getSession(httpRequest, httpResponse);
        String id = session.getId();
        SessionManager.removeSession(id);

        HttpRequest newHttpRequest = getNewHttpRequest(id);
        Session newSession = SessionManager.getSession(newHttpRequest, httpResponse);

        assertNotEquals(session, newSession);
    }




    private HttpRequest getHttpRequest() {
        String rawHttpRequestString = "GET /welcome HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Content-Type: html/text; charset=utf-8\r\n" +
                "Cookie: Idea-admin=asdf-asdf-abcd-eqfw-qefx3xqw4tf\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawHttpRequestString.getBytes());
        return new HttpRequest(inputStream);
    }

    private HttpRequest getNewHttpRequest(String value) {
        String rawHttpRequestString = "GET /welcome HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Content-Type: html/text; charset=utf-8\r\n" +
                "Cookie: Idea-admin=asdf-asdf-abcd-eqfw-qefx3xqw4tf; YH_COOKIE="+value+"\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawHttpRequestString.getBytes());
        return new HttpRequest(inputStream);
    }

    private HttpResponse getHttpResponse() {
        OutputStream outputStream = new ByteArrayOutputStream();
        return new HttpResponse(outputStream);
    }
}