package com.github.kelly.webserver;

import com.github.kelly.http.request.HttpMethod;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.webserver.controller.Controller;
import com.github.kelly.webserver.controller.HomeController;
import com.github.kelly.webserver.controller.NotFoundController;
import java.util.HashMap;
import java.util.Map;

public class UserDefinedControllerResolver {

    public static Map<RequestKey, Controller> controllerMap = new HashMap<>();
    private final RequestKey requestKey;


    static {
        controllerMap.put(new RequestKey("/home", HttpMethod.GET), new HomeController());
    }

    public UserDefinedControllerResolver(HttpRequest httpRequest) {
        String url = httpRequest.getRequestLine().getUrl();
        HttpMethod method = httpRequest.getRequestLine().getHttpMethod();
        requestKey = new RequestKey(url, method);
    }

    public Controller getController() {
        // request 가 들어왔다. /home
        // /home 으로 들어온 요청을 HomeController 와 연결시켜서 이 컨트롤러를 반환하고 싶다.
        // 요청이 어떻게 들어왔는지 url 로부터 충분한 정보를 얻는다.
        // 미리 정의해놓은 주소와 컨트롤러일 경우
        if (controllerMap.containsKey(requestKey)) {
            return controllerMap.get(requestKey);
        }
        return new NotFoundController();
    }
}
