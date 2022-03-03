package com.github.kelly.webserver.dispatcher;

import com.github.kelly.controller.*;
import com.github.kelly.http.request.HttpMethod;
import com.github.kelly.http.request.HttpRequest;
import java.util.HashMap;
import java.util.Map;


public class UserDefinedRequestResolver implements RequestResolver {

    public static Map<RequestKey, Controller> controllerMap = new HashMap<>();


    static {
        controllerMap.put(new RequestKey("/", HttpMethod.GET), new WelcomeController());
        controllerMap.put(new RequestKey("/welcome", HttpMethod.GET), new WelcomeController());
        controllerMap.put(new RequestKey("/signup", HttpMethod.POST), new SignUpController());
        controllerMap.put(new RequestKey("/login", HttpMethod.POST), new LogInController());
        controllerMap.put(new RequestKey("/logout", HttpMethod.GET), new LogOutController());
    }


    @Override
    public boolean support(HttpRequest httpRequest) {
        RequestKey requestKey = new RequestKey(httpRequest.getUrl(), httpRequest.getHttpMethod());
        return controllerMap.containsKey(requestKey);
    }

    @Override
    public Controller resolve(HttpRequest httpRequest) {
        if (support(httpRequest)) {
            RequestKey requestKey = new RequestKey(httpRequest.getUrl(), httpRequest.getHttpMethod());
            return controllerMap.get(requestKey);
        }
        return null;
    }
}
