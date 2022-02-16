package com.github.kelly.webserver;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.webserver.controller.Controller;
import com.github.kelly.webserver.controller.NotFoundController;

/**
 * Front Controller - A controller that handles all requests for a web site
 * responsibility: resolver 통합체에게 request 에 맞는 컨트롤러를 반환할 것을 요청한다.
 *                 dispatcher-resolver -> UserDefinedRequestResolver, StaticFileRequestResolver
 *                 dispatcher-resolver:
 *                          일단 request 를 보자. /home 인가 /home.html 인가?
 *                          그럼 통합체는 어느 resolver 에게 요청을 줄 것인지를 결정해서 주는 역할을 해야 하는데
 *                          support() - 여기서 확장자가 있으면(true) Static, 없으면(false) UserDefined
 *                          resolve() - support-true 면 StaticResolver 를, false 면 UserResolver 를
 *                                      -> Controller 를 반환해야해 ㅠㅜ........하...
 *                                      잠깐... dispatcher.dispatch() 를 했을 때 실행 내용은
 *                                      resolver 통합체.resolve() 를 호출하게끔 하고, 통합체.resolve() 실행 내용은
 *                                      resolver 를 반환하는 게 아니라 아예 resolver.resolve()를 호출해서 Controller 를 반환받으면?
 *                                      if (support) {
 *                                          어차피 이걸 하려고 하는 거니까 애초에 resolve 까지 시키는 거지
 *                                          return StaticResolver.resolve();  -> support 하면 Controller, 안하면 null 반환
 *                                      }
 *
 *                          일반 resolver 가 정의 하고 있는 메소드 (구체적으로 map 을 뒤져서 있는지 없는지 검사)
 *                          support() - 각 resolver map 에 key(url,method)가 있는지 검사 true, false
 *                          resolve() - support-true 면 해당 controller, false 면 null 반환
 *
 */
public class DispatcherServlet {

    private HttpRequest httpRequest;


    public DispatcherServlet(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    /**
     * todo
     * resolver 인터페이스 하나 만들어서 통합하기?
     * resolver 해결하기
     */
    // 책임: resolver 들에게 request 에 대해 물어본다.
    // 현재 resolver 들 -  1. 사용자 정의 컨트롤러 반환 (UserDefinedControllerResolver)
    //                    2. static 파일 컨트롤러 반환 (StaticFileControllerResolver)
    // /home, GET
    public Controller dispatch() {

        // resolver 통합체에게 시킬 코드를 짜보자.
        String url = httpRequest.getRequestLine().getUrl();
        if (url.contains(".")) {
            StaticFileRequestResolver staticFileRequestResolver = new StaticFileRequestResolver(httpRequest);
            Controller staticController = staticFileRequestResolver.resolve();
            if (staticController != null) {
                return staticController;
            }
        } else {
            UserDefinedRequestResolver userDefinedRequestResolver = new UserDefinedRequestResolver(httpRequest);
            // resolve() 호출하면서 request 를 넘겨줘도 될 것 같은데 -> 일단 패스
            Controller controller = userDefinedRequestResolver.resolve();   // controller or null
            if (controller != null) {
                return controller;
            }
        }

        return new NotFoundController();
    }
}
