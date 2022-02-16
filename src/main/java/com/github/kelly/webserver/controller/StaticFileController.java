package com.github.kelly.webserver.controller;

import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import com.github.kelly.utils.FileReadUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * 역할:
 *  request url 요청 들어온 파일을 읽어서 웹 브라우저의 응답으로 내보낸다.
 *
 */
public class StaticFileController implements Controller{

    public static final String DIRECTORY = "static";
    private static final Map<String, String> mimeTypeMap = new HashMap<>();

    static {
        mimeTypeMap.put("html", "text/html; charset=utf-8");
        mimeTypeMap.put("php", "text/html; charset=utf-8");
        mimeTypeMap.put("text", "text/plain");
        mimeTypeMap.put("jpeg", "image/jpeg");
        mimeTypeMap.put("png", "image/png");
        mimeTypeMap.put("json", "application/json");
    }

    /**
     * StaticFileRequestResolver 는 request url 을 읽고 static 파일을 뒤져서 찾는다
     * 파일이 존재하기 때문에 'resolver 통합체' 에게 StaticFileController 를 반환한다.
     */
    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("StaticFileController.process");
        // 어떤 url 을 요청받았는지 확인한다.
        String url = httpRequest.getRequestLine().getUrl();
        String filePath = DIRECTORY + url; // /home.html
        // static -> url 주소에서 뽑아낸 데이터로 파일 찾아서 읽기
        byte[] body = FileReadUtil.readFromStaticFile(filePath);
        // 읽은 데이터를 response 로 내보내기
        // response 로 내보낼 때 필요한 것: content-type: mime 타입을 알아내야 한다.
        if (url.contains(".")) {
            String extension = url.split("\\.")[1];
            httpResponse.addHeader("Content-Type", mimeTypeMap.get(extension));
            httpResponse.writeBody(body);
        }

    }
}
