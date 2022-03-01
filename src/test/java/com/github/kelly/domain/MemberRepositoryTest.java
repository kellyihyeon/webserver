package com.github.kelly.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemberRepository 객체 생성 테스트")
class MemberRepositoryTest {

    MemberRepository repository;

    @BeforeEach
    void repository_생성() {
        repository = MemberRepository.getInstance();
    }

    @Test
    @DisplayName("MemberRepository 는 싱글톤 객체이다.")
    void 싱글톤_테스트() {
        MemberRepository instance = MemberRepository.getInstance();

        assertEquals(repository, instance);
        assertEquals(repository.getClass().hashCode(), instance.getClass().hashCode());
    }

    @Test
    @DisplayName("Member 가 생성된 수만큼 저장이 되었는지 테스트한다.")
    void save_메소드_테스트() {
        Member memberA = new Member("memberA", "password1!");
        repository.save(memberA);

        Member memberB = new Member("memberB", "password2@");
        repository.save(memberB);

        MemberRepository instance = MemberRepository.getInstance();
        assertEquals(2, (long) instance.getNumberOfMember());
    }

    @Test
    @DisplayName("member 식별자에 바인딩 된 member 를 반환받는 테스트")
    void getMember_메소드_테스트() {
        Member memberA = new Member("memberA", "password1!");
        repository.save(memberA);

        Member memberB = new Member("memberB", "password2@");
        repository.save(memberB);

        MemberRepository instance = MemberRepository.getInstance();
        assertEquals(memberA, instance.getMember(1L));
        assertEquals(memberB, instance.getMember(2L));
    }
}