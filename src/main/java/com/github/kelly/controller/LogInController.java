package com.github.kelly.controller;

import com.github.kelly.domain.Member;
import com.github.kelly.domain.MemberRepository;
import com.github.kelly.http.*;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import java.time.LocalDateTime;

/**
 * happy path 구현
 */
public class LogInController implements Controller {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("LogInController.process");

        SessionManager sessionManager = SessionManager.getInstance();

        // request headers 에 남아있는 기존 쿠키
        String remainCookie = CookieParser.parseCookie(httpRequest);
        if (remainCookie != null) {
            Session remainSession = sessionManager.getSession(remainCookie);
            remainSession.invalidate();
            Object maybeNull = remainSession.getAttribute(remainCookie);
            System.out.println("maybeNull = " + maybeNull);
        }

        String userId = httpRequest.getParameter("userId");
        String password = httpRequest.getParameter("password");

        Member findMember = memberRepository.getMemberFromMemberId(userId);
        if (findMember != null) {
            if (findMember.getPassword().equals(password)) {

                Session session = sessionManager.getSession(null);
                String sessionId = session.getId();

                Cookie cookie = new Cookie(CookieTypes.YH_COOKIE, sessionId);
                cookie.setExpires(LocalDateTime.now().plusDays(1));

                httpResponse.addHeader("Set-Cookie", cookie.createCookie());
                httpResponse.redirect("/main.html");
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        } else {
            System.out.println("존재하지 않는 아이디 입니다.");
        }

    }

}
