package com.shop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.ProductFile;
import com.shop.domain.QProductFile;
import com.shop.repository.custom.ProductFileConfig;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductFileRepositoryImpl implements ProductFileConfig {
    private final JPAQueryFactory queryFactory;
    QProductFile qProductFile = QProductFile.productFile;

    @Override
    public void deleteProductFile(long productSeq, String fileClsCd, int odr) {
        queryFactory.delete(qProductFile)
                .where(qProductFile.product.productSeq.eq(productSeq)
                    .and(qProductFile.fileClsCd.eq(fileClsCd)
                    .and(qProductFile.odr.eq(odr))))
                .execute();
    }
}
