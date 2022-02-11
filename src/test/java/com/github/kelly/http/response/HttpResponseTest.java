package com.github.kelly.http.response;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HttpResponse 객체 생성 테스트")
class HttpResponseTest {

    @Test
    @DisplayName("OutputStream 을 생성자로 넘기면 객체가 생성된다.")
    @Disabled("response 를 찍어보기 위해 임시로 뒀던 getResponseBody 메소드 삭제로 인해 테스트 불가")
    void HttpResponse_객체_생성_테스트() {
        OutputStream outputStream = new ByteArrayOutputStream();

        HttpResponse httpResponse = new HttpResponse(outputStream);
        String maybeBody = "hi! I am a html!!! trust me :)";

        assertEquals(maybeBody, httpResponse.getResponseBody());
    }

}