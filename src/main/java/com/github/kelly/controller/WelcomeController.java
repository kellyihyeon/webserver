package com.github.kelly.controller;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;


public class WelcomeController implements Controller {

    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("WelcomeController.process");
        System.out.println("httpRequest.getUrl() = " + httpRequest.getUrl());
        System.out.println("httpRequest.getQueryString() = " + httpRequest.getQueryString());

        httpResponse.redirect("/home.html");
    }
}
