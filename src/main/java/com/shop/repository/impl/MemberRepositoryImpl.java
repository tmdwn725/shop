package com.shop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.Member;
import com.shop.domain.QMember;
import com.shop.repository.custom.MemberConfig;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberConfig {
    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;
    public Member fingByMemberId(String memberId) {
        return queryFactory.selectFrom(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();
    }
}
