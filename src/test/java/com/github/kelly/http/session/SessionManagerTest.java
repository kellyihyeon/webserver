package com.github.kelly.http.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SessionManager 객체 생성 테스트")
class SessionManagerTest {

    @Test
    @DisplayName("싱글톤 객체인 SessionManager 는 같은 객체를 반환한다.")
    void sessionManager_싱글톤_테스트() {
        SessionManager instanceA = SessionManager.getInstance();
        SessionManager instanceB = SessionManager.getInstance();

        assertEquals(instanceA, instanceB);
        assertEquals(instanceA.getClass().hashCode(), instanceB.getClass().hashCode());
    }

}