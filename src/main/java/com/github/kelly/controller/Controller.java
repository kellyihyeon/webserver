package com.github.kelly.controller;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;

public interface Controller {

    void process(HttpRequest httpRequest, HttpResponse httpResponse);
}
