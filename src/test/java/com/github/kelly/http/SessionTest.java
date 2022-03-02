package com.github.kelly.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Session 객체 생성 테스트")
class SessionTest {

    @Test
    @DisplayName("session id 와 member id 를 바인딩 한다.")
    void session에_attribute_저장하기() {
        String sessionId1 = UUID.randomUUID().toString();
        Session session1 = new Session(sessionId1);
        Long memberId1 = 1L;
        session1.setAttribute(sessionId1, memberId1);

        String sessionId2 = UUID.randomUUID().toString();
        Session session2 = new Session(sessionId2);
        Long memberId2 = 1L;
        session2.setAttribute(sessionId2, memberId2);

        assertEquals(memberId1, session1.getAttribute(sessionId1));
        assertEquals(memberId2, session2.getAttribute(sessionId2));
    }

    @Test
    @DisplayName("session id 가 일치하면 같은 객체이다.")
    void session_동일성_테스트() {
        String sessionId = UUID.randomUUID().toString();
        Session sessionA = new Session(sessionId);
        Session sessionB = new Session(sessionId);

        assertEquals(sessionA, sessionB);
        assertEquals(sessionA.getClass().hashCode(), sessionB.getClass().hashCode());
    }

    @Test
    @DisplayName("세션 id 를 제거하면 세션 id 와 바인딩 된 유저의 정보는 null 이 된다.")
    void session_제거_테스트() {
        String sessionId = UUID.randomUUID().toString();
        Session session = new Session(sessionId);

        session.setAttribute(sessionId, 1L);
        assertEquals(1L, session.getAttribute(sessionId));

        session.invalidate();
        assertNull(session.getAttribute(sessionId));
    }
}