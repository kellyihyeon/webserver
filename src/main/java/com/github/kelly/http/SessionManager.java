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


    public Session getSession() {
        createSession();
        return sessionMap.get(sessionId);
    }


    /**
     * getSession() 을 호출하면 새로운 세션을 만들어서 반환을 한다.
     * 세션을 무분별하게 만들면 안된다. (회원가입 시 생성, 로그인 시 검사)
     *      1. request header 에 세션이 있다면
     *          이전 response 로 이미 세션을 만들어 내려준 경우
     *          header 로 보낸 session id 로 member 를 꺼낸다. 이 member 의 id 와 body 에 담긴 member 가 일치하는지 확인한다.
     *          존재하지 않는 session id(유효기간 만료)일 때: 유저 id, password 가 일치하면 새 세션 생성.
     *          세션은 존재하나 member 데이터가 일치하지 않을 때: 잘못된 접근 (에러 처리)
     *          일치한다면 기존 세션을 사용하면 되므로 세션을 만들지 않고 로그인 시켜준다.(해피 패쓰)
     *
     *      2. request header 에 세션이 없다면
     *          response 할 때 쿠키에 세션을 넣어서 내려준다.
     */
    private void createSession() {   // 세션을 만들어준다.
        makeSessionId();
        sessionMap.put(sessionId, new Session(sessionId));  // 동일성 구현 - equals and hashCode
    }

    private void makeSessionId() {
        this.sessionId = UUID.randomUUID().toString();
    }

}
