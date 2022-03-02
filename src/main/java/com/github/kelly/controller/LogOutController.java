package com.github.kelly.controller;

import com.github.kelly.http.cookie.Cookie;
import com.github.kelly.http.cookie.CookieTypes;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;

public class LogOutController implements Controller {


    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("LogOutController.process");

        // 로그아웃 - 1.invalidate(), 2.cookie.setMaxAge(0)
        Cookie cookie = httpRequest.getCustomCookie(CookieTypes.YH_COOKIE.toString());
        cookie.setMaxAge(0);
        // -> 로그아웃 할 때는 쿠키에서만 제거하고 다음 로그인 시 request 에 남아있는 쿠키 -세션까지 제거.

        httpResponse.addHeader("Set-Cookie", cookie.createCookie());    // 중복 리팩토링 하자
        httpResponse.redirect("/home.html");
    }
}
