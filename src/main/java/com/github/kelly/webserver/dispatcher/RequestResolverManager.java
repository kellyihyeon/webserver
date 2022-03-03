package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.controller.Controller;
import java.util.ArrayList;
import java.util.List;

/**
 * 역할:
 *    support() - request url 데이터를 가져와 StaticFile 을 요청한 request 인지 확인한다.
 *    resolve() - support 메소드 반환 결과에 따라 어느 Resolver 를 실행할 것인지 결정하고 Controller 를 반환받는다.
 */
public class RequestResolverManager implements RequestResolver {

    private final HttpRequest httpRequest;
    private final List<RequestResolver> resolvers = new ArrayList<>();

    public RequestResolverManager(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;

        resolvers.add(new UserDefinedRequestResolver(httpRequest));
        resolvers.add(new StaticFileRequestResolver(httpRequest));
    }

    @Override
    public boolean support() {
        // 1. 유저 리졸버  2.스태틱 리졸버
        for (RequestResolver resolver : resolvers) {
            if (resolver.support()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Controller resolve() {
        if (support()) {    // 해당 컨트롤러가 있다.
            for (RequestResolver resolver : resolvers) {
                Controller controller = resolver.resolve();
                if (controller != null) {
                    return controller;
                }
            }
        }
        return null;       // 어떤 리졸버도 해당 컨트롤러를 가지고 있지 않다.
    }
}
