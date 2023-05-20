package com.shop.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.Product;
import com.shop.domain.QFile;
import com.shop.domain.QProduct;
import com.shop.domain.QProductFile;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    QProduct product = QProduct.product;
    QProductFile productFile = QProductFile.productFile;
    QFile file = QFile.file;
    public List<Product> selectProductList(){
        List<Product> productList = queryFactory
                .select(product)
                .from(product)
                .join(product.productFileList, productFile)
                .join(productFile.file, file)
                .orderBy(product.regDt.desc()) // 최신순으로 정렬
                .limit(8) // 최대 8개만 조회
                .fetch();
        return productList;
    }
}
