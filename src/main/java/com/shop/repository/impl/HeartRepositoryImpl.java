package com.shop.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.*;
import com.shop.dto.HeartDTO;
import com.shop.repository.custom.HeartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

@RequiredArgsConstructor
public class HeartRepositoryImpl implements HeartConfig {
    private final JPAQueryFactory queryFactory;
    QHeart qHeart = QHeart.heart;
    QHeart qHeart2 = QHeart.heart;
    QProduct qProduct = QProduct.product;
    QProductStock qProductStock = QProductStock.productStock;
    /**
     * 좋아요 정보 조회
     * @param heart
     * @return
     */
    public Heart selectHeartInfo(Heart heart){
        return queryFactory.selectFrom(qHeart)
                .where(qHeart.member.eq(heart.getMember())
                .and(qHeart.product.eq(heart.getProduct())))
                .fetchOne();
    }
    @EntityGraph(attributePaths = {"product"})
    public Page<Heart> selectHeartList(Pageable pageable, Long memberSeq){
        QueryResults<Heart> heartList = queryFactory
                .selectFrom(qHeart)
                .join(qHeart.product,qProduct)
                .fetchJoin()
                .where(qHeart.member.memberSeq.eq(memberSeq))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Heart> content = heartList.getResults();
        long total = heartList.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
