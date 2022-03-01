package com.github.kelly.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Session {

    private final String id;

    // setAttribute(String name, Object value) 메소드를 통해 이 세션저장소에 유저 정보를 저장한다.
    private final Map<String, Object> attributeMap = new HashMap<>();
    //  예시: User id 는 name 으로 하고, Object 에 User 를 넣어놓는다. 그럼 저장소가 필요한데 map 자료구조를 이용한다. attributeMap 이 좋겠다.
    //  attribute 를 찾을 때는 getAttribute() 메소드를 이용한다. 우선 이 정도로만 만들어보자. start!


    public Session(String id) {
        this.id = id;
    }

    /**
     *
     * @param name      Session id
     * @param value     Object 식별자 (User id)
     */
    public void setAttribute(String name, Object value) {
        attributeMap.put(name, value);
    }

    public String getId() {
        return id;
    }

    public Object getAttribute(String sessionId) {
        return attributeMap.get(sessionId);
    }

    /**
     * Session Id 가 일치하면 동일성 인정
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
