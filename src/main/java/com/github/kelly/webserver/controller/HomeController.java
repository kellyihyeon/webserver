package com.github.kelly.webserver.controller;

import com.github.kelly.http.response.HttpResponse;

public class HomeController implements Controller {

    @Override
    public void process(HttpResponse httpResponse) {
        // 파라미터로 response 를 받아서 response header 만들어서 writeBody 해서 적절한 응답을 내보낼 건데.
        System.out.println("HomeController.process");
    }
}
