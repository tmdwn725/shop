package com.shop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.QProduct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;
    QProduct product = QProduct.product;
}
