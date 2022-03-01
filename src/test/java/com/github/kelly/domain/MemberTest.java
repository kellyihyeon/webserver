package com.github.kelly.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Member 객체 생성 테스트")
class MemberTest {

    @Test
    @DisplayName("Member 식별자는 MemberRepository 에 저장된 member 의 수에서 +1씩 증가한다. ")
    void 식별자_테스트() {
        MemberRepository memberRepository = MemberRepository.getInstance();

        Member memberA = new Member("memberA", "password1!");
        memberRepository.save(memberA);

        Member memberB = new Member("memberB", "password2@");
        memberRepository.save(memberB);

        Member memberC = new Member("memberC", "password3#");
        memberRepository.save(memberC);

        assertEquals(1, memberA.getIdentifier());
        assertEquals(2, memberB.getIdentifier());
        assertEquals(3, memberC.getIdentifier());
    }
}