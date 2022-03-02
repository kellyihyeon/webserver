package com.github.kelly.controller;

import com.github.kelly.domain.Member;
import com.github.kelly.domain.MemberRepository;
import com.github.kelly.http.cookie.CookieParser;
import com.github.kelly.http.session.Session;
import com.github.kelly.http.session.SessionManager;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;

public class MyPageController implements Controller {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        String cookieValue = CookieParser.parseYhCookie(httpRequest);     // session in
        // 세션 아이디로 세션 찾고, 세션에 저장된 멤버 찾고, 멤버 아이디를 뽑는 것이 목표다.
        SessionManager sessionManager = SessionManager.getInstance();
        Session session = sessionManager.getSession(cookieValue);
        Object maybeMemberIdentifier = session.getAttribute(session.getId());

        Member findMember = memberRepository.getMember((Long) maybeMemberIdentifier);
        String memberId = findMember.getMemberId();
        System.out.println("memberId = " + memberId);

        httpResponse.writeBody(memberId.getBytes());

    }
}
