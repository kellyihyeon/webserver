package com.github.kelly.http;

import com.github.kelly.http.request.HttpRequest;

public class CookieParser {


    public static String parseCookie(HttpRequest httpRequest) {
        String rawValue = httpRequest.getRequestHeaders().getHeader("Cookie");
        String value = "";

        if (rawValue.contains(";")) {
            String[] cookieKeys = rawValue.split(";");
            for (String cookieKey : cookieKeys) {
                System.out.println("cookieKey = " + cookieKey);
                String cookieName = cookieKey.split("=")[0].trim();
                String cookieValue = cookieKey.split("=")[1].trim();
                if (cookieName.equals("YH_COOKIE")) {
                    value = cookieValue;
                }
            }
        }

        System.out.println("CookieParser.parseCookie - value = " + value);
        return value;
    }
}
