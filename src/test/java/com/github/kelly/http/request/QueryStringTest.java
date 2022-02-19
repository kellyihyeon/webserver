package com.github.kelly.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class QueryStringTest {

    @Test
    @DisplayName("QueryString 객체 생성 테스트")
    void QuertString_객체_생성() {
        String parameters = "username=kiki";

        QueryString queryString = new QueryString(parameters);
        Map<String, String> queryStringMap = queryString.getQueryStringMap();

        assertEquals("kiki", queryStringMap.get("username"));
    }

    @Test
    @DisplayName("parameter 를 여러개 넘겼을 때 QueryStringMap 테스트")
    void 여러개의_parameter() {
        String parameters = "username=kiki&position=magician&power=99";

        QueryString queryString = new QueryString(parameters);
        Map<String, String> queryStringMap = queryString.getQueryStringMap();

        assertEquals("kiki", queryStringMap.get("username"));
        assertEquals("magician", queryStringMap.get("position"));
        assertEquals("99", queryStringMap.get("power"));
    }
}