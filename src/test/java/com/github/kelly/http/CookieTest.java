package com.github.kelly.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Cookie 객체 생성 테스트")
class CookieTest {

    @Test
    @DisplayName("Option 을 설정 하지 않으면 Default Cookie 가 생성된다.")
    void Default_Cookie_객체_생성() {
        String expectedCookie = "YH_COOKIE_TEST=cookie-value-12345-abcde";
        Cookie cookie = new Cookie("YH_COOKIE_TEST", "cookie-value-12345-abcde");
        String bakedCookie = cookie.createCookie();

        assertEquals(expectedCookie, bakedCookie);
    }

    @Test
    @DisplayName("Cookie 에 Expires(현재 시간 + 7일) 를 설정한다.")
    void Optional_Setting한_객체_생성() {
        String expectedCookie = "YH_COOKIE_TEST=cookie-value-12345-abcde; Expires=Tue, 8 Mar 2022 0:0:0 GMT";
        Cookie cookie = new Cookie("YH_COOKIE_TEST", "cookie-value-12345-abcde");
        cookie.setExpires(LocalDateTime.of(2022, 3, 1, 0, 0, 0).plusDays(7));
        String bakedCookie = cookie.createCookie();

        assertEquals(expectedCookie, bakedCookie);
    }


}