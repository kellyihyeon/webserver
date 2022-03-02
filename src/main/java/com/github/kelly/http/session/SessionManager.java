package com.github.kelly.http.session;

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

    private void createSession() {   // 세션을 만들어준다.
        makeSessionId();
        sessionMap.put(sessionId, new Session(sessionId));  // 동일성 구현 - equals and hashCode
    }

    private void makeSessionId() {
        this.sessionId = UUID.randomUUID().toString();
    }

}
