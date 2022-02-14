package com.github.kelly.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@DisplayName("HttpResponse 객체 생성 테스트")
class HttpResponseTest {

    @Test
    @DisplayName("OutputStream 을 생성자로 넘기면 객체가 생성된다.")
//    @Disabled("증명을 어떻게 하지..?")
    void HttpResponse_객체_생성_테스트() {
        OutputStream outputStream = new ByteArrayOutputStream();
        HttpResponse httpResponse = new HttpResponse(outputStream);

        String maybeBody = "This is response body :)";
        httpResponse.addHeader("Content-Type", "html/text; charset=UTF-8");
        httpResponse.addHeader("Content-Owner", "kelly");
        httpResponse.addHeader("Content-Version", "1.0");

        httpResponse.writeBody(maybeBody.getBytes());
    }

}