package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.controller.Controller;

/**
 * 역할:
 *    support() - request url 데이터를 가져와 StaticFile 을 요청한 request 인지 확인한다.
 *    resolve() - support 메소드 반환 결과에 따라 어느 Resolver 를 실행할 것인지 결정하고 Controller 를 반환받는다.
 */
public class RequestResolverManager implements RequestResolver {

    private HttpRequest httpRequest;

    public RequestResolverManager(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    public boolean support() {
        String url = httpRequest.getRequestLine().getUrl();
        return url.contains(".");
    }

    @Override
    public Controller resolve() {
        if (support()) {
            StaticFileRequestResolver staticFileRequestResolver = new StaticFileRequestResolver(httpRequest);
            return staticFileRequestResolver.resolve();
        } else {
            UserDefinedRequestResolver userDefinedRequestResolver = new UserDefinedRequestResolver(httpRequest);
            return userDefinedRequestResolver.resolve();
        }
    }
}
