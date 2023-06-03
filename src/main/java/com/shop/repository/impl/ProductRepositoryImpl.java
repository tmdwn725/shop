package com.shop.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    QProduct qProduct = QProduct.product;
    QProductFile qProductFile = QProductFile.productFile;
    QFile qFile = QFile.file;
    public List<Product> selectProductList(int limit){
        List<Product> productList = queryFactory
                .select(qProduct)
                .from(qProduct)
                .join(qProduct.productFileList, qProductFile)
                .join(qProductFile.file, qFile)
                .orderBy(qProduct.regDt.desc()) // 최신순으로 정렬
                .limit(limit) // 최대 8개만 조회
                .fetch();
        return productList;
    }

    public Product selectProduct(Long productSeq){
        Product productInfo = queryFactory
                .selectFrom(qProduct)
                .join(qProduct.productFileList, qProductFile)
                .join(qProductFile.file, qFile)
                .where(qProduct.productSeq.eq(productSeq))
                .fetchOne();
        return productInfo;
    }
}
