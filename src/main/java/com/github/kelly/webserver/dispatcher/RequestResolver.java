package com.github.kelly.webserver.dispatcher;

import com.github.kelly.controller.Controller;
import com.github.kelly.http.request.HttpRequest;

public interface RequestResolver {

    boolean support(HttpRequest httpRequest);

    /**
     * 정의 되어 있는지 support 의 결과 여부에 따라 특정 Controller 혹은 Null 반환
     * @return Controller
     * Null 을 반환해도 되는지?
     * @param httpRequest
     */
    Controller resolve(HttpRequest httpRequest);

}
