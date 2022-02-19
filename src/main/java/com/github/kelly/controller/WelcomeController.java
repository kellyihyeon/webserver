package com.github.kelly.controller;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;


public class WelcomeController implements Controller {

    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("WelcomeController.process");
        System.out.println("지금 쓰레드 = " + Thread.currentThread().getName());

        httpResponse.redirect("/home.html");
    }
}
