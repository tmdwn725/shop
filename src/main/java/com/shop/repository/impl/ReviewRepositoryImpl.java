package com.shop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.OrderInfo;
import com.shop.domain.QFile;
import com.shop.domain.QReview;
import com.shop.domain.Review;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    QReview qReview = QReview.review;
    QFile qFile = QFile.file;
    public Review selectReviewInfo(OrderInfo orderInfo){
        return queryFactory.selectFrom(qReview)
                .join(qReview.file,qFile)
                .where(qReview.orderInfo.eq(orderInfo))
                .fetchOne();
    }

}
