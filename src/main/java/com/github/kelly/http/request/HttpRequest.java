package com.github.kelly.http.request;

import com.github.kelly.http.cookie.Cookie;
import com.github.kelly.http.cookie.CookieParser;
import com.github.kelly.http.session.Session;
import java.io.*;
import java.util.Map;

/**
 * YAGNI! 필요하지 않을까 미리 생각하지 말고 지금 당장 필요 없으면 만들지 말 것
 */
public class HttpRequest {

    private RequestLine requestLine;
    private RequestHeaders requestHeaders;
    private RequestBody requestBody;

    private Cookie[] cookies;
    private Session session;        //


    public HttpRequest(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            this.requestLine = new RequestLine(br.readLine());
            this.requestHeaders = new RequestHeaders(br);
            this.cookies = parseCookie();
            this.requestBody = new RequestBody(br, requestHeaders);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * getRequestLine, getUrl, getHttpMethod
     */
    public RequestLine getRequestLine() {
        return requestLine;
    }

    public String getUrl() {
        return requestLine.getUrl();
    }

    public HttpMethod getHttpMethod() {
        return requestLine.getHttpMethod();
    }

    // query string map, request body(optional) map -> get,post.. 등 메소드로 요청했을 때 map 에서 value 를 꺼내준다.
    public Map<String, String> getQueryStringMap() {
        if (requestLine.getQueryString() != null) {
            return requestLine.getQueryString().getQueryStringMap();
        }
        return null;
    }

    // 각 controller 에서 parameter value 꺼낼 때
    public String getParameter(String key) {
        String parameterValue = requestLine.getParameterValue(key);
        if (parameterValue != null) {
            return parameterValue;
        }
        return requestBody.getBodyResourceValue(key);

    }


    public RequestHeaders getRequestHeaders() {
        return requestHeaders;
    }


    private Cookie[] parseCookie() {
        return CookieParser.parseCookies(requestHeaders.getHeader("Cookie"));
    }


    public Cookie getCustomCookie(String cookieName) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }


//    public Session getSessionFromRequestHeaders() {
//        Cookie yhCookie = getCustomCookie(CookieTypes.YH_COOKIE.toString());
//        String value = yhCookie.getValue();
//
//        SessionManager sessionManager = SessionManager.getInstance();
//        return sessionManager.getSessionInRequest(value);
//    }
}
