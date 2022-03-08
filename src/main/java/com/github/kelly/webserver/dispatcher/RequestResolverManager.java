package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.controller.Controller;
import com.github.kelly.webserver.controller.NotFoundController;
import java.util.ArrayList;
import java.util.List;

/**
 * 역할:
 *    support() - 지원하는 resolver 가 존재하는지 여부를 반환
 *    resolve() - support 메소드 반환 결과에 따라 어느 Resolver 를 실행할 것인지 결정하고 Controller 를 반환받는다.
 */
public class RequestResolverManager implements RequestResolver {

    private final List<RequestResolver> resolvers = new ArrayList<>();
    private static final NotFoundController NOT_FOUND_CONTROLLER = new NotFoundController();


    public RequestResolverManager() {
        resolvers.add(new UserDefinedRequestResolver());
        resolvers.add(new StaticFileRequestResolver());
    }

    @Override
    public boolean support(HttpRequest httpRequest) {
        // 1. 유저 리졸버  2.스태틱 리졸버
        for (RequestResolver resolver : resolvers) {
            if (resolver.support(httpRequest)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Controller resolve(HttpRequest httpRequest) {
        if (support(httpRequest)) {    // 해당 컨트롤러가 있다.
            for (RequestResolver resolver : resolvers) {
                Controller controller = resolver.resolve(httpRequest);
                if (controller != null) {
                    return controller;
                }
            }
        }
        return NOT_FOUND_CONTROLLER;       // 어떤 리졸버도 해당 컨트롤러를 가지고 있지 않다.
    }
}
