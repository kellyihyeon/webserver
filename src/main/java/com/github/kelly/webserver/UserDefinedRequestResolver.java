package com.github.kelly.webserver;

import com.github.kelly.http.request.HttpMethod;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.webserver.controller.Controller;
import com.github.kelly.webserver.controller.WelcomeController;
import java.util.HashMap;
import java.util.Map;


public class UserDefinedRequestResolver implements RequestResolver{

    public static Map<RequestKey, Controller> controllerMap = new HashMap<>();
    private final RequestKey requestKey;


    static {
        controllerMap.put(new RequestKey("/welcome", HttpMethod.GET), new WelcomeController());
    }

    public UserDefinedRequestResolver(HttpRequest httpRequest) {
        String url = httpRequest.getRequestLine().getUrl();
        HttpMethod method = httpRequest.getRequestLine().getHttpMethod();
        requestKey = new RequestKey(url, method);
    }

    @Override
    public boolean support() {
        return controllerMap.containsKey(requestKey);
    }

    @Override
    public Controller resolve() {
        if (support()) {
            return controllerMap.get(requestKey);
        }
        return null;
    }
}