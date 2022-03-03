package com.github.kelly.http.cookie;

import com.github.kelly.http.cookie.Cookie;
import com.github.kelly.http.cookie.CookieTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Cookie 객체 생성 테스트")
class CookieTest {

    @Test
    @DisplayName("Option 을 설정 하지 않으면 Default Cookie 가 생성된다.")
    void Default_Cookie_객체_생성() {
        String cookieName = CookieTypes.YH_COOKIE.name();
        String cookieValue = "cookie-value-12345-abcde";
        String expectedCookie = cookieName + "=" + cookieValue;

        Cookie cookie = new Cookie(CookieTypes.YH_COOKIE.name(), cookieValue);
        String bakedCookie = cookie.createCookie();

        assertEquals(expectedCookie, bakedCookie);
    }

    @Test
    @DisplayName("Option - Expires 를 설정하면 만료시간이 설정된 Cookie 가 생성된다.")
    void Optional_Expires_Cookie_객체_생성() {
        String cookieName = CookieTypes.YH_COOKIE.name();
        String cookieValue = "cookie-value-12345-abcde";
        String optionalExpires = "Expires=Tue, 8 Mar 2022 0:0:0 GMT";
        String expectedCookie = cookieName + "=" + cookieValue +"; " + optionalExpires;

        Cookie cookie = new Cookie(CookieTypes.YH_COOKIE.name(), cookieValue);
        cookie.setExpires(LocalDateTime.of(2022, 3, 1, 0, 0, 0).plusDays(7));
        String bakedCookie = cookie.createCookie();

        assertEquals(expectedCookie, bakedCookie);
    }


}