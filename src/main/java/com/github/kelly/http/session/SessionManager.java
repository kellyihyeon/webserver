package com.github.kelly.http.session;

import com.github.kelly.http.cookie.Cookie;
import com.github.kelly.http.cookie.CookieTypes;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class SessionManager {

    private static final SessionManager sessionManager = new SessionManager();
    private final Map<String, Session> sessionMap = new HashMap<>();


    private SessionManager() {
        // this is SINGLETON
    }


    public static Session getSession(HttpRequest request, HttpResponse response) {
        return sessionManager.parseSession(request, response);
    }



    // 생명주기: 로그인 ~ 로그아웃
    private Session parseSession(HttpRequest request, HttpResponse response) {
        Cookie remainCookie = request.getCustomCookie(CookieTypes.YH_COOKIE.name());
        System.out.println("remainCookie = " + remainCookie);
        if (remainCookie != null) {
            String valueFromRemainCookie = remainCookie.getValue();
            Session remainSession = sessionMap.get(valueFromRemainCookie);
            if (remainSession != null) {
                return remainSession;
            }
        }

        return createSession(response);
    }


    /**
     * 세션을 만들어준다.
     * 만든 세션을 쿠키에 넣고, 해당 쿠키는 response header 에 추가해 내려준다.
     *
     * @return Session
     */
    private Session createSession(HttpResponse response) {
        String sessionId = UUID.randomUUID().toString();
        sessionMap.put(sessionId, new Session(sessionId));

        Cookie cookie = new Cookie(CookieTypes.YH_COOKIE.name(), sessionId);
        cookie.setExpires(LocalDateTime.now().plusDays(7));
        response.addHeader("Set-Cookie", cookie.createCookie());

        return sessionMap.get(sessionId);
    }

    public static void removeSession(String sessionId) {
        sessionManager.sessionMap.remove(sessionId);
    }


}
