package com.shop.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
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

    /**
     * 상품목록조회
     * @param pageable
     * @return
     */
    public Page<Product> selectProductList(Pageable pageable, Long sellerSeq){
        JPAQuery<Product> productList = queryFactory
                .select(qProduct)
                .from(qProduct)
                .join(qProduct.productFileList, qProductFile)
                .join(qProductFile.file, qFile)
                .orderBy(qProduct.regDt.desc(), qProduct.productSeq.asc()); // 최신순으로 정렬

        if (sellerSeq > 0) {
            productList = productList.where(qProduct.sellerSeq.eq(sellerSeq));
        }

        List<Product> content = productList
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = productList.fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    /**
     * 상품정보조회
     * @param productSeq
     * @return
     */
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
