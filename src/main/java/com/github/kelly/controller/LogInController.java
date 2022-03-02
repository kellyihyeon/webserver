package com.github.kelly.controller;

import com.github.kelly.domain.Member;
import com.github.kelly.domain.MemberRepository;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import com.github.kelly.http.session.Session;
import com.github.kelly.http.session.SessionManager;

/**
 * happy path 구현
 */
public class LogInController implements Controller {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("LogInController.process");

        SessionManager sessionManager = SessionManager.getInstance();

        String userId = httpRequest.getParameter("userId");
        String password = httpRequest.getParameter("password");

        Member findMember = memberRepository.getMemberFromMemberId(userId);
        if (findMember != null) {
            if (findMember.getPassword().equals(password)) {

                Session session = sessionManager.getSession(httpRequest, httpResponse);
                session.setAttribute("memberIdentifier", findMember.getIdentifier());

                httpResponse.redirect("/main.html");
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        } else {
            System.out.println("존재하지 않는 아이디 입니다.");
        }

    }

}
