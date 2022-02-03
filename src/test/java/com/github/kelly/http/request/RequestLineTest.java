package com.github.kelly.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("요청라인 객체 생성 테스트")
class RequestLineTest {

    @Test
    @DisplayName("요청 라인을 생성자에 넘기면 객체가 생성된다.")
    void 요청라인_객체_생성_테스트() {
        // request line
        String line = "GET /tdd HTTP/1.1";
        RequestLine requestLine = new RequestLine(line);

        assertEquals(HttpMethod.GET, requestLine.getHttpMethod());
        assertEquals("/tdd", requestLine.getUrl());
        assertEquals("HTTP/1.1", requestLine.getProtocol());
    }


    @DisplayName("requestLine 객체가 HttpMethod 를 파싱하는지 검사한다.")
    @TestFactory
    Stream<DynamicTest> HttpMethod_파싱_테스트() {
        HttpMethodParserTestCase getCase = new HttpMethodParserTestCase("GET /get_uri HTTP/1.1", HttpMethod.GET);
        HttpMethodParserTestCase postCase = new HttpMethodParserTestCase("POST /post_uri HTTP/1.1", HttpMethod.POST);
        HttpMethodParserTestCase putCase = new HttpMethodParserTestCase("PUT /put_uri HTTP/1.1", HttpMethod.PUT);
        HttpMethodParserTestCase deleteCase = new HttpMethodParserTestCase("DELETE /put_uri HTTP/1.1", HttpMethod.DELETE);

        return Stream.of(getCase, postCase, putCase, deleteCase)
                .map(methodTestCase -> DynamicTest.dynamicTest(
                        "테스트 할 요청 라인은 " + methodTestCase.requestLine + " 이고 파싱된 결과, 메소드는 " + methodTestCase.method + " 이다.",
                        () -> {
                            RequestLine requestLine = new RequestLine(methodTestCase.requestLine);
                            assertEquals(methodTestCase.method, requestLine.getHttpMethod());
                        }
                ));
    }


    private static class HttpMethodParserTestCase {
        private final String requestLine;
        private final HttpMethod method;

        public HttpMethodParserTestCase(String requestLine, HttpMethod method) {
            this.requestLine = requestLine;
            this.method = method;
        }
    }

}