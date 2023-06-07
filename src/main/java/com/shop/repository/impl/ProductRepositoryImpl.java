package com.shop.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    QProduct qProduct = QProduct.product;
    QProductStock qProductStock = QProductStock.productStock;
    QProductFile qProductFile = QProductFile.productFile;
    QFile qFile = QFile.file;

    public Page<Product> selectProductList(Pageable pageable){
        QueryResults<Product> productList = queryFactory
                .select(qProduct)
                .from(qProduct)
                .join(qProduct.productFileList, qProductFile)
                .join(qProductFile.file, qFile)
                .orderBy(qProduct.regDt.desc(), qProduct.productSeq.asc()) // 최신순으로 정렬
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()) // 최대 8개만 조회
                .fetchResults();
        List<Product> content = productList.getResults();
        long total = productList.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    public Product selectProduct(Long productSeq){
        Product productInfo = queryFactory
                .selectFrom(qProduct)
                .join(qProduct.productStockList, qProductStock)
                .join(qProduct.productFileList, qProductFile)
                .join(qProductFile.file, qFile)
                .where(qProduct.productSeq.eq(productSeq))
                .fetchOne();
        return productInfo;
    }
}
