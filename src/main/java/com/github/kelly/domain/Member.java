package com.github.kelly.domain;


public class Member {

    private final Long identifier;  // 식별자
    private final String memberId;   // 유저 아이디
    private final String password;


    public Member(String memberId, String password) {
        this.identifier = MemberRepository.getInstance().getNumberOfMember() + 1;
        this.memberId = memberId;
        this.password = password;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "identifier=" + identifier +
                ", memberId='" + memberId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
