package com.github.kelly.http.session;

import java.util.HashMap;
import java.util.Map;

public class Session {

    private final String id;
    private final Map<String, Object> attributeMap = new HashMap<>();


    public Session(String id) {
        this.id = id;
    }

    /** Session 을 사용자가 로그인 했음을 알 수 있는 데이터 저장소로 이용한다.
     *
     * @param name      memberIdentifier ...
     * @param value     Session attribute map 에는 member 의 식별자(로그인 했음을 알 수 있는 정보)를 담는다.
     */
    public void setAttribute(String name, Object value) {
        attributeMap.put(name, value);
    }

    public String getId() {
        return id;
    }


    public Object getAttribute(String name) {
        return attributeMap.get(name);
    }

    public void invalidate() {
        attributeMap.clear();
    }

}
