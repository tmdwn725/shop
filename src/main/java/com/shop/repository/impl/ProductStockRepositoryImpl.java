package com.shop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.ProductStock;
import com.shop.domain.QProduct;
import com.shop.domain.QProductStock;
import com.shop.repository.custom.ProductStockConfig;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductStockRepositoryImpl implements ProductStockConfig {
    private  final JPAQueryFactory queryFactory;
    QProductStock qProductStock = QProductStock.productStock;
    public ProductStock selectProductStock(Long productSeq, String sizeType){
        return queryFactory.selectFrom(qProductStock)
                .where(qProductStock.product.productSeq.eq(productSeq),qProductStock.productSize.eq(sizeType))
                .fetchOne();
    }
    public void updateProductStockCount(Long productSeq, String size, int count){
        queryFactory.update(qProductStock)
                .set(qProductStock.productCount,count)
                .where(qProductStock.product.productSeq.eq(productSeq)
                .and(qProductStock.productSize.eq(size)))
                .execute();
    }

}
