package com.github.kelly.webserver.controller;

import com.github.kelly.http.response.HttpResponse;

public interface Controller {

    void process(HttpResponse httpResponse);
}
