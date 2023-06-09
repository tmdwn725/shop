package com.shop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.Member;
import com.shop.domain.QMember;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;
    public Member fingByMemberId(String memberId) {
        return queryFactory.selectFrom(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();
    }
}
