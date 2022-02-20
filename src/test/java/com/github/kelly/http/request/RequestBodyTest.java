package com.github.kelly.http.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static org.junit.jupiter.api.Assertions.*;

class RequestBodyTest {

    private RequestHeaders requestHeaders;

    @BeforeEach
    @DisplayName("RequestHeader 객체 생성 ")
    void RequestHeader_객체_생성() throws IOException {
        String requestBodyString = "userId=admin&password=admin98";
        int contentLength = requestBodyString.length();

        String requestHeaders = "Host: localhost:8080\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Content-Length: " + contentLength + "\r\n" +
                "\r\n";

        InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(requestHeaders.getBytes()));
        BufferedReader bufferedReader = new BufferedReader(reader);
        RequestHeaders headers = new RequestHeaders(bufferedReader);
        getHeaders(headers);    // -> 이렇게 해도 되나?
    }

    @Test
    @DisplayName("RequestBody 객체 생성")
    void RequestBody_객체_생성() {
        String requestBodyString = "userId=admin&password=admin98";

        InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(requestBodyString.getBytes()));// InputStream
        BufferedReader br = new BufferedReader(inputStreamReader);// Reader
        RequestBody requestBody = new RequestBody(br, requestHeaders);

        String userId = requestBody.getBodyResourceValue("userId");
        String password = requestBody.getBodyResourceValue("password");

        assertEquals("admin", userId);
        assertEquals("admin98", password);
    }


    void getHeaders(RequestHeaders headers) {
        requestHeaders = headers;
    }


}