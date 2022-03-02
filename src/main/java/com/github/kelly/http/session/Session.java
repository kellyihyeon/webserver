package com.github.kelly.http.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Session {

    private final String id;
    private final Map<String, Object> attributeMap = new HashMap<>();


    public Session(String id) {
        this.id = id;
    }

    /**
     * @param name      memberIdentifier
     * @param value     Object 식별자 (member 식별자)
     */
    public void setAttribute(String name, Object value) {
        attributeMap.put(name, value);
    }

    public String getId() {
        return id;
    }

    // *** 변수: 고쳐야 함 String attributeName
    public Object getAttribute(String sessionId) {
        System.out.println("Session.getAttribute - sessionId = " + sessionId);
        return attributeMap.get(sessionId);
    }

    public void invalidate() {
        attributeMap.remove(id);
    }

    /**
     * Session Id 가 일치하면 동일성 인정 -> 삭제
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
