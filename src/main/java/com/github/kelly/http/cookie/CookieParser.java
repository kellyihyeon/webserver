package com.github.kelly.http.cookie;

public class CookieParser {


    public static Cookie[] parseCookies(String rawCookieValue) {
        Cookie[] allCookies = null;
        if (rawCookieValue != null) {
            String[] cookies = rawCookieValue.split(";");
            allCookies = new Cookie[cookies.length];

            for (int i = 0; i < allCookies.length; i++) {
                System.out.println("cookie = " + cookies[i]);
                String cookieName = cookies[i].split("=")[0].trim();
                String cookieValue = cookies[i].split("=")[1].trim();
                allCookies[i] = new Cookie(cookieName, cookieValue);
                System.out.println("CookieParser.parseCookie - value = " + cookieValue);
            }
        }

        return allCookies;
    }
}
