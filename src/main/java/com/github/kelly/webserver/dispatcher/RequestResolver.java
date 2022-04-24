package com.github.kelly.webserver.dispatcher;

import com.github.kelly.controller.Controller;
import com.github.kelly.http.request.HttpRequest;

public interface RequestResolver {

    boolean support(HttpRequest httpRequest);

    Controller resolve(HttpRequest httpRequest);

}
