package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.controller.Controller;
import com.github.kelly.webserver.controller.NotFoundController;

/**
 * Front Controller - A controller that handles all requests for a web site
 * responsibility: resolver manager 에게 request 에 맞는 컨트롤러를 반환할 것을 요청하고
 *                 반환 받은 컨트롤러를 handler 에 전달한다.
*/
public class DispatcherServlet {

    RequestResolverManager resolverManager = new RequestResolverManager();


    public Controller dispatch(HttpRequest httpRequest) {
        Controller controllerOrNull = resolverManager.resolve(httpRequest);

        if (controllerOrNull == null) {
            return new NotFoundController();    // todo: 매번 새 객체 생성하지 않고 객체 하나만 접근할 수 있도록
        }

        return controllerOrNull;

    }
}
