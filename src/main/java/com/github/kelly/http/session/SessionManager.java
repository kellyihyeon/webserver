package com.github.kelly.http.session;

import com.github.kelly.http.cookie.Cookie;
import com.github.kelly.http.cookie.CookieTypes;
import com.github.kelly.http.request.HttpRequest;
import com.github.kelly.http.response.HttpResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 역할: 세션을 관리한다.
 * 책임: 세션을 생성한다. 생성한 세션을 세션맵(저장소)을 이용해 관리한다.
 *      세션에 관한 처리는 SessionManager 를 통해서만 이루어져야 한다.
 *
 */
public class SessionManager {

    private static SessionManager manager;
    private final Map<String, Session> sessionMap = new HashMap<>();
    private String sessionId;

    /**
     * SINGLETON !!!
     */
    private SessionManager() {
    }


    public static SessionManager getInstance() {     // 싱글톤인 manager 반환
        if (manager == null) {
            manager = new SessionManager();
        }
        return manager;
    }


    public Session getSession(String value) {
        if (sessionMap.get(value) == null) {
            System.out.println("SessionManager.getSession - sessionMap 에 session 이 없습니다.");
            createSession();
            value = sessionId;
        }
        return sessionMap.get(value);
    }


    // 생명주기: 로그인 ~ 로그아웃
    public Session getSession(HttpRequest request, HttpResponse response) {

        Cookie remainCookie = request.getCustomCookie(CookieTypes.YH_COOKIE.toString());
        if (remainCookie != null) {

            String valueFromRemainCookie = remainCookie.getValue();

            remainCookie.setMaxAge(0);      // request header cookie 에서 삭제
            Session remainSession = sessionMap.get(valueFromRemainCookie);
            if (remainSession != null) {
                remainSession.invalidate();     // session map 에서 삭제
            }
            // 로그아웃 한 경우: request 쿠키에서만 제거, 로그아웃 안했을 경우: request 쿠키에서 제거, 세션맵에서도 제거
        }

        createSession();
        // *** refactoring
        Session newSession = sessionMap.get(sessionId);
        Cookie cookie = new Cookie(CookieTypes.YH_COOKIE.toString(), newSession.getId());
        cookie.setExpires(LocalDateTime.now().plusDays(7));
        response.addHeader("Set-Cookie", cookie.createCookie());

        System.out.println("newSession = " + newSession);
        return newSession;

    }

    public Session getSessionInRequest(String sessionId) {
        return sessionMap.get(sessionId);
    }

    private void createSession() {   // 세션을 만들어준다.
        makeSessionId();
        sessionMap.put(sessionId, new Session(sessionId));  // 동일성 구현 - equals and hashCode
    }

    private void makeSessionId() {
        this.sessionId = UUID.randomUUID().toString();
    }

}
