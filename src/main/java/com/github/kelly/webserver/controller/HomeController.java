package com.github.kelly.webserver.controller;

import com.github.kelly.http.response.HttpResponse;

/**
 * localhost:8080/home
 * 요청 시 단순한 문구가 아니라 home.html 파일을 웹 브라우저의 응답으로 보내고 싶다.
 * HomeController.process() 메소드를 실행하면 home.html 파일로 redirect 하게 해야한다.
 * 흐름:
 * localhost:8080/home -> dispatcher 에서 HomeController 를 반환 -> controller.process() 실행
 * localhost:8080/home.html 로 리다이렉트 됨.
 * todo
 * 1. response.redirect("/home.html") 을 호출할 시 어떻게 할 것인가? - done
 * 1. url 에 /home.html 이 왔을 때 어떻게 할 것인가?
 */
public class HomeController implements Controller {

    @Override
    public void process(HttpResponse httpResponse) {
        System.out.println("HomeController.process");
        httpResponse.redirect("/home.html");
    }
}
