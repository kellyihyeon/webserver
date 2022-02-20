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

        httpResponse.redirect("/home.html");
    }
}
