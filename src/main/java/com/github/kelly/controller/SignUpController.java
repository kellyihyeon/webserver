package com.github.kelly.controller;

import com.github.kelly.domain.Member;
import com.github.kelly.domain.MemberRepository;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;

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

        httpResponse.redirect("/home.html");
    }
}
