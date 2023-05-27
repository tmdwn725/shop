package com.shop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.Code;
import com.shop.domain.QCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CodeRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    QCode qCode = QCode.code;
    public Code selectCode(String cd){
        return queryFactory.selectFrom(qCode)
                .where(qCode.cd.eq(cd))
                .fetchOne();
    }
    public List<Code> selectCodeList(String grpCd){
        return queryFactory.selectFrom(qCode)
                .where(qCode.grpCd.eq(grpCd))
                .fetch();
    }
}
