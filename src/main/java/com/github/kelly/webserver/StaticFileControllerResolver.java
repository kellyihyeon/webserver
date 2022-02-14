package com.github.kelly.webserver;

import com.github.kelly.http.request.HttpRequest;

public class StaticFileControllerResolver {

    private HttpRequest httpRequest;

    public StaticFileControllerResolver(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }
}
