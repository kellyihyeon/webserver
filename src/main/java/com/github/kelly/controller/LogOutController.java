package com.github.kelly.controller;

import com.github.kelly.http.cookie.Cookie;
import com.github.kelly.http.cookie.CookieTypes;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import com.github.kelly.http.session.SessionManager;

public class LogOutController implements Controller {


    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("LogOutController.process");

        Cookie cookie = httpRequest.getCustomCookie(CookieTypes.YH_COOKIE.toString());
        cookie.setMaxAge(0);

        SessionManager.removeSession(cookie.getValue());

        httpResponse.addHeader("Set-Cookie", cookie.createCookie());
        httpResponse.redirect("/home.html");
    }
}
