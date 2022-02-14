package com.github.kelly.webserver.controller;

import com.github.kelly.http.response.HttpResponse;

public class NotFoundController implements Controller {

    @Override
    public void process(HttpResponse httpResponse) {
        // 사용자 정의 컨트롤러가 없을 때 잘못된 주소라는 걸 보여주는 응답
        System.out.println("NotFoundController.process");
    }
}
