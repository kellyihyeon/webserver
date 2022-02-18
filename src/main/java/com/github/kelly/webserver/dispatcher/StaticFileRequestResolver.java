package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.controller.Controller;
import com.github.kelly.webserver.controller.StaticFileController;
import java.io.*;


/**
 * Resolver 의 역할:
 * 1. 들어온 요청을 지원하는지 확인한다.
 * 2. 지원하지 않으면 null, 지원 한다면 StaticController 반환
 * */
public class StaticFileRequestResolver implements RequestResolver {

    private HttpRequest httpRequest;
    private final String DIRECTORY = "static";


    public StaticFileRequestResolver(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }


    @Override
    public boolean support() {
        String filePath = DIRECTORY + httpRequest.getRequestLine().getUrl();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
        try {
            return inputStream != null;

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Controller resolve() {
        if (support()) {
            return new StaticFileController();
        }

        return null;
    }
}
