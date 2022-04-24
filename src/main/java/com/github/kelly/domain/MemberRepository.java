package com.github.kelly.domain;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {

    private static final Map<Long, Member> memberMap = new HashMap<>();
    private static final MemberRepository memberRepository = new MemberRepository();

    private MemberRepository() {
        // 싱글톤
    }

    public static MemberRepository getInstance() {
        return memberRepository;
    }


    public void save(Member member) {
        memberMap.put(member.getIdentifier(), member);
    }

    public Member getMember(Long id) {
        return memberMap.get(id);
    }

    public Member getMemberFromMemberId(String memberId) {
        return memberMap.values().stream()
                                .filter(member -> member.getMemberId().equals(memberId))
                                .findFirst()
                                .orElse(null);

    }

    public Long getNumberOfMember() {
        return (long) memberMap.size();
    }

    public void deleteAll() {
        memberMap.clear();
    }


}
