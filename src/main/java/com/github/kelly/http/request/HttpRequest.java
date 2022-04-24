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


    public Map<String, String> getQueryStringMap() {
        if (requestLine.getQueryString() != null) {
            return requestLine.getQueryString().getQueryStringMap();
        }
        return null;
    }


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
        // 스토리지에서 cookie 를 다 날려버린 경우
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

}
