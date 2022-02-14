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

    // UserDefinedControllerResolver 로 이사 ////


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
        new UserDefinedControllerResolver(httpRequest);
        new StaticFileControllerResolver(httpRequest);


        return new NotFoundController();
    }
}
