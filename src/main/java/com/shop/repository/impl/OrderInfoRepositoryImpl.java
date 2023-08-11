package com.shop.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.OrderInfo;
import com.shop.domain.QOrderInfo;
import com.shop.domain.QProduct;
import com.shop.domain.QProductStock;
import com.shop.repository.custom.OrderInfoConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

@RequiredArgsConstructor
public class OrderInfoRepositoryImpl implements OrderInfoConfig {
    private final JPAQueryFactory queryFactory;
    QOrderInfo qOrderInfo = QOrderInfo.orderInfo;
    QProductStock qProductStock = QProductStock.productStock;
    QProduct qProduct = QProduct.product;
    @EntityGraph(attributePaths = {"productStock", "product"})
    public Page<OrderInfo> selectOrderList(Pageable pageable, Long memberSeq){
        QueryResults<OrderInfo> orderInfoList = queryFactory.selectFrom(qOrderInfo)
                .join(qOrderInfo.productStock,qProductStock)
                .fetchJoin()
                .join(qProductStock.product,qProduct)
                .fetchJoin()
                .where(qOrderInfo.member.memberSeq.eq(memberSeq))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<OrderInfo> content = orderInfoList.getResults();
        long total = orderInfoList.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
