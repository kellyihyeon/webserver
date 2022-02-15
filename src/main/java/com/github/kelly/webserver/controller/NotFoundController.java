package com.github.kelly.webserver.controller;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;

public class NotFoundController implements Controller {

    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        // 사용자 정의 컨트롤러가 없을 때 잘못된 주소라는 걸 보여주는 응답
        System.out.println("NotFoundController.process");
        String html = "<HTML>" +
                    "<HEAD><TITLE>NOT FOUND PAGE</TITLE></HEAD>" +
                    "<BODY>" +
                    "<h2><center><b>잘못된 요청입니다.</b></center></h2>" +
                    "<p><center>요청 정보를 다시 한번 확인해주세요 :(</center></p>"+
                    "</BODY>" +
                    "</HTML>";
        httpResponse.addHeader("Content-Type", "text/html; charset=utf-8 ");
        httpResponse.writeBody(html.getBytes());
    }
}
