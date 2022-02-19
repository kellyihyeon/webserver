package com.github.kelly.controller;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;

import java.util.Map;

/**
 * todo
 *  query string 으로 넘긴 데이터 가져오기
 */
public class SignUpController implements Controller {


    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("SignUpController.process");
        String contentType = httpRequest.getRequestHeaders().getHeader("Content-Type");
        System.out.println("contentType = " + contentType); // application/x-www-form-urlencoded

        System.out.println("httpRequest.url = " + httpRequest.getUrl());
        Map<String, String> queryStringMap = httpRequest.getQueryString();
        String userId = queryStringMap.get("userId");
        String password = queryStringMap.get("password");

        System.out.println("userId = " + userId);
        System.out.println("password = " + password);

    }
}
