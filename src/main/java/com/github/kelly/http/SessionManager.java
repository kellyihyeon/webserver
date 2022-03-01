package com.github.kelly.http;

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
    // session 을 만들어주는 객체가 하나 있다. SessionManager 라고 하자.
    // SessionManager 가 session id 를 하나 만들고 (String sessionId = "blabla~~", new Session(sessionId) 를 생성한다.)
    //  ** SessionManager 가 여러개여도 될까? session id 는 식별자 이어야 한다. 세션 매니저를 싱글톤으로 만들고 세션을 관리하는 객체는 하나로 만든다.
    //     SessionManager 를 싱글톤으로 만들 때 주의사항: 임계영역 문제, 공유되는 영역에 특히 주의할 것.
    private static SessionManager manager;
    private final Map<String, Session> sessionMap = new HashMap<>();
    private String sessionId;

    /**
     * SINGLETON !!!
     */
    private SessionManager() {
    }

    // 이렇게 세션을 생성한다. 이렇게 만든 세션은 서버가 웹브라우저에 response 할 때 response header 의 쿠키 속에 session id 를 넣어서 보낸다.
    // 다음번 요청 때 쿠키 속에 있는 세션 id 를 꺼내고, 이 세션 id 에 맞는 세션을 꺼내야 한다. 그러려면 map 이 있어야 겠구나.(session map-String, Session)
    // 세션맵을 통해 세션 id에 맞는 value(Session)을 꺼내고, 이 세션이 가지고 있는 attribute 를 통해서 유저 확인을 한다.

    public static SessionManager getInstance() {     // 싱글톤인 manager 반환
        if (manager == null) {
            manager = new SessionManager();
        }
        return manager;
    }


    public Session getSession() {
        createSession();
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
