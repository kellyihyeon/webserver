package com.github.kelly.controller;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;

public class SignUpController implements Controller {


    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("SignUpController.process");

        String userId = httpRequest.getParameter("userId");
        String password = httpRequest.getParameter("password");
        System.out.printf("userId: %s, password: %s\r\n", userId, password);

        // user 가 서비스에 가입 했을 때, userId = user, password = 1234
        // 다음 번에 이 데이터로 로그인 한다면? 서버에서는 어떻게 이 상태를 기억하고 있어야 할까?
        // 회원 가입 하면서, 요청이 이 컨트롤러로 들어온다.
        // userId 와 password 를 저장한다. 어디에?
        // request headers 에 보면 Cookie: Idea-1afefe=efseblabla 라고 돼있는데...
        // 이 Cookie 뒤의 값을 내가 만들어서 내려주면 이걸 활용할 수 있지 않을까?
        // 넘겨받은 id, password 를 저장할 건데, 그 전에 Cookie 값을 생성해서 Cookie: abcd-blabla 이 값을 id 로 삼고,
        // 서버에는 데이터 저장소를 둔다(이게 세션인가?)

        // 세션 객체: 세션id(쿠키값), Member(id, password) 일단 객체로 안 만들더라도, 세션 id, 유저 id, 유저 password 를 한 세트로 만든다.
        // 그리고 response 하면서 Cookie 를 설정해서 같이 내려준다.(id 값 넘김)
        // 작성자만 접근 할 수 있는 api 를 요청하면?(예를 들면 로그인 요청)
        // request 에서 cookie 값을 가져온다. session 에서 이 id 값을 넣어서 유저 id, password 를 비교해본다.
        // id, 유저 id, password 가 모두 맞으면 로그인 시켜준다.
        // 2. 세션: Idea-cookie=123456789 -> 쿠키의 밸류 값인 123456789 를 세션 아이디로 하고, (123456789, session) 세트로 저장.
        //         request 에 담아온 cookie 를 열어서 내가 만들어 준 cookie value 를 꺼낸다. (request 에 cookie 가 있는 경우, 없는 경우 둘 다 생각)
        //         이 value 로 session 들이 담겨져 있는 session map 에서 해당 세션을 찾는다.
        //         해당 세션이 나오면 request 로 보낸 데이터를 session 이 들고 있는 map 에 저장한다.
        //

        // Set-Cookie: <cookie-name>=<cookie-value>
        // Set-Cookie: <cookie-name>=<cookie-value>; Expires=<date>
        // Permanent cookie
        // Set-Cookie: id=a3fWa; Expires=Wed, 21 Oct 2015 07:28:00 GMT; Secure; HttpOnly
        // request headers 에 Cookie 가 있는 경우, 없는 경우
        /**
         * todo
         * map 자료구조에 저장하고 있으므로 뒤에 쿠키만 세팅됨. 내일 여기부터 뜯어볼 것.
         */
        httpResponse.addHeader("Set-Cookie", "yh-cookie=choco-mint");
        httpResponse.addHeader("Set-Cookie", "Expires=Mon, 21 Feb 2022 07:28:00 GMT");


        httpResponse.redirect("/home.html");
    }
}
