package com.github.kelly.http.cookie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("CookieParser.parseCookies 메소드가 쿠키들을 파싱해오는지 테스트한다.")
class CookieParserTest {


    @Test
    @DisplayName("여러 개의 쿠키 중 YH_COOKIE 의 value 값을 추출한다.")
    void YH_COOKIE_value_추출_테스트() {
        String rawCookie = "Cookie: Idea-admin=asdf-asdf-abcd-eqfw-qefx3xqw4tf; YH_COOKIE=2d1672d2-e0b5-45ec-928f-c3da110ae44f";
        final Cookie[] cookies = CookieParser.parseCookies(rawCookie);

        String expectedYhCookieValue = "2d1672d2-e0b5-45ec-928f-c3da110ae44f";

        final List<String> findYhCookieValue = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("YH_COOKIE"))
                .map(Cookie::getValue).collect(Collectors.toList());

        for (String value : findYhCookieValue) {
            assertEquals(expectedYhCookieValue, value);
        }
    }

    @Test
    @DisplayName("쿠키는 있지만, YH_COOKIE 가 없는 경우")
    void YH_COOKIE가_없는_경우_() {
        String rawCookie = "Cookie: Idea-admin=asdf-asdf-abcd-eqfw-qefx3xqw4tf";
        final Cookie[] cookies = CookieParser.parseCookies(rawCookie);
        String maybeNull = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("YH_COOKIE")) {
                maybeNull = cookie.getValue();
            }
        }

        assertNull(maybeNull);
    }

    @Test
    @DisplayName("request headers 에 Cookie 가 없는 경우 null 을 반환한다.")
    void 쿠키가_없는_경우() {
        String rawCookie = null;

        assertNull(CookieParser.parseCookies(rawCookie));
    }
}