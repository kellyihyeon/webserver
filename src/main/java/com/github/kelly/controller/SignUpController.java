package com.github.kelly.controller;

import com.github.kelly.domain.Member;
import com.github.kelly.domain.MemberRepository;
import com.github.kelly.http.Cookie;
import com.github.kelly.http.Session;
import com.github.kelly.http.SessionManager;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import java.time.LocalDateTime;

public class SignUpController implements Controller {

    MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("SignUpController.process");

        String userId = httpRequest.getParameter("userId");
        String password = httpRequest.getParameter("password");

        Member createMember = new Member(userId, password);
        memberRepository.save(createMember);
        System.out.println("createMember = " + createMember);
        // request headers 에 Cookie 가 있는 경우, 없는 경우

        SessionManager manager = SessionManager.getInstance();
        Session session = manager.getSession();
        session.setAttribute(session.getId(), createMember.getIdentifier());
        System.out.printf("session id = %s, member identifier = %d\r\n", session.getId(), createMember.getIdentifier());
        Object attribute = session.getAttribute(session.getId());
        System.out.println("멤버의 식별자가 나와야한다. = " + attribute.toString());

        Cookie cookie = new Cookie("YH_COOKIE", session.getId());
        cookie.setExpires(LocalDateTime.now().plusDays(7));
        httpResponse.addHeader("Set-Cookie", cookie.createCookie());

        httpResponse.redirect("/home.html");
    }
}
