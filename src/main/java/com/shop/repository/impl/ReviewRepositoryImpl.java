package com.shop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.OrderInfo;
import com.shop.domain.QReview;
import com.shop.domain.Review;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    QReview qReview = QReview.review;
    public Review selectReviewInfo(OrderInfo orderInfo){
        return queryFactory.selectFrom(qReview)
                .where(qReview.orderInfo.eq(orderInfo))
                .fetchOne();
    }

}
