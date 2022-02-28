package com.github.kelly.controller;

import com.github.kelly.http.Cookie;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import java.time.LocalDateTime;

public class SignUpController implements Controller {


    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("SignUpController.process");

        String userId = httpRequest.getParameter("userId");
        String password = httpRequest.getParameter("password");
        System.out.printf("userId: %s, password: %s\r\n", userId, password);
        // request headers 에 Cookie 가 있는 경우, 없는 경우

        // 1. user 가 보낸 데이터를 추출한다. (user id, password)
        // 2. 쿠키를 생성해서 response 하고, 다음번 request 때 만들어준 쿠키와 함께 데이터를 요청하게 한다.
        Cookie cookie = new Cookie("YH_COOKIE", "cookie-value=sessionId");
        // 2.1 쿠키를 세팅할 때, 다양한 옵션을 달 수 있다. session id, Expires, Max-Age, Path, HttpOnly ...
        //     옵션을 하나씩 추가할 때마다 쿠키의 값을 변경해준다. (StringBuilder 로 String 을 추가해서)
        //     완성된 쿠키를 response header 에 전달한다. cookie.createCookie() 같은 메소드면 좋겠다. 쿠키를 생성한다는 걸 알 수 있게
        cookie.setExpires(LocalDateTime.now().plusDays(7));
        httpResponse.addHeader("Set-Cookie", cookie.createCookie());

        httpResponse.redirect("/home.html");
    }
}
