package com.github.kelly.controller;

import com.github.kelly.domain.Member;
import com.github.kelly.domain.MemberRepository;
import com.github.kelly.http.Session;
import com.github.kelly.http.SessionManager;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;

/**
 * happy path 구현
 */
public class LogInController implements Controller {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("LogInController.process");

        // request header 의 쿠키에서 세션 id 를 꺼낸다.
        String rawValue = httpRequest.getRequestHeaders().getHeader("Cookie"); // session id
        String sessionId = "";
        // value 에서 쿠키 value 를 뜯어내야 한다.
        if (rawValue.contains(";")) {
            String[] cookieKeys = rawValue.split(";");
            for (String cookieKey : cookieKeys) {
                String cookieName = cookieKey.split("=")[0];
                String cookieValue = cookieKey.split("=")[1];
                if (cookieName.equals("YH_COOKIE")) {
                    sessionId = cookieValue;
                }
            }
        }
        System.out.println("sessionId = " + sessionId);                 //  ** sessionId = (안나오고 있는 상황)

        // session map 에서 session id 를 넣고 세션을 가져온다.
        SessionManager manager = SessionManager.getInstance();
        Session session = manager.getSessionBeta(sessionId);
        // 세션 id 로 대응하는 유저를 찾는다.(유저 식별자를 얻는다.)
        Object memberIdentifier = session.getAttribute(session.getId());    // NullPointerException
        // member map 에서 유저 식별자로 유저를 가져온다.
        Member member = memberRepository.getMember((Long) memberIdentifier);
        // 서버에 저장된(member repository) 유저의 정보와 웹브라우저가 전달한 유저의 id, password 정보가 일치하는지 확인한다.
        if (member.getMemberId().equals(httpRequest.getParameter("userId"))) {
            if (member.getPassword().equals(httpRequest.getParameter("password"))) {
                //  유저 정보가 일치하는 경우: 로그인 성공
                httpResponse.redirect("/main.html");
            }
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //  유저 정보가 일치하지 않는 경우: 로그인 실패
        throw new IllegalArgumentException("아이디가 일치하지 않습니다.");
    }
}
