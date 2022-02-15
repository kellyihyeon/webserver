package com.github.kelly.webserver;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.webserver.controller.Controller;
import com.github.kelly.webserver.controller.NotFoundController;

/**
 * Front Controller - A controller that handles all requests for a web site
 * responsibility: 요청에 맞는 컨트롤러 찾아서 반환해주기
 * 1. 사용자 정의 컨트롤러 반환 (UserDefinedControllerResolver)
 *      정의 해놓은 url-method 가 맞으면 맞는 Controller 를 반환한다.
 *      controller 를 반환받은 handler 가 controller 의 process(rep) 메소드를 실행한다.
 *
 * 2. static 파일 컨트롤러 반환 (StaticFileControllerResolver)
 *      정의 해놓은 static file 이 있으면 static file 전용 Controller 를 반환한다.
 *      (home.html 이 있다.
 *          String contents; (home.html 파일을 읽어서 컨텐트에 담아둠)
 *          return new StaticFileController(contents); 이렇게 생성자로 contents 를 넘겨놓고,
 *          StaticFileController 생성자에서 this.contents = contents; 저장해놓는다.
 *       )
 *      controller 를 반환받은 handler 가 controller 의 process(rep) 메소드를 실행한다. -> 실행할 때 이 경우에는 home.html 내용을 읽은 데이터를 넘겨줘야할텐데?
 *      멤버필드에 contents 가 있으니, 이 contents 를 웹 브라우저에 뿌린다.
 *
 * todo
 * redirect 후: url 에 /home.html 이 왔을 때 어떻게 할 것인가?
 * static 파일에 home.html 파일이 있는지 스캔하고 있으면 이 파일을 읽어서 웹 브라우저에 뿌려준다.
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

        // resolver. getController()
        UserDefinedRequestResolver userDefinedRequestResolver = new UserDefinedRequestResolver(httpRequest);
        Controller controller = userDefinedRequestResolver.resolve();   // controller or null
        if (controller != null) {
            return controller;
        }

        StaticFileRequestResolver staticFileRequestResolver = new StaticFileRequestResolver(httpRequest);
        Controller staticController = staticFileRequestResolver.resolve();
        if (staticController != null) {
            return staticController;
        }

        // dispatch 는 resolver 들에게 처리할 수 있는지 물어보고, 처리할 수 있다고 응답한 resolver 의 컨트롤러를 반환받는다.
        // support()? -> userResolver: true!, staticResolver: false!
        // true 면 controller 를 반환하는데 false 면 return 을 안받아도 되는데 어떻게 하냐? null 을 반환해도 되나??
        // null 을 반환하면 dispatch 가 null 인지 검사를 하고 null 이 아닐 경우에만 특정 controller 를 handler 에게 넘긴다.
        //                                                null 일 경우에는 NotFoundController 를 handler 에게 넘긴다.
        // 그럼 여기서 정해진 것: resolver 인터페이스는 1.support() 메소드로 처리할 수 있는지 여부를 확인 할 것.
        //                                         2. resolve() 메소드로 support 가 true 일 경우 특정 컨트롤러를 반환하고, false 면 null 반환할 것.

        return new NotFoundController();
    }
}
