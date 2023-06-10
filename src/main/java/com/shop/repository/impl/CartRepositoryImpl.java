package com.shop.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CartRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    QCart qCart = QCart.cart;
    QMember qMember = QMember.member;
    QProductStock qProductStock = QProductStock.productStock;
    QProduct qProduct = QProduct.product;
    public List<Cart> myCartList(Member member){
        return queryFactory
                .select(Projections.fields(Cart.class,
                        qCart.cartSeq,
                        qCart.quantity,
                        qCart.member,
                        qCart.productStock,
                        qCart.productStock.product.productName,
                        qCart.productStock.product.price))
                .from(qCart)
                .join(qCart.productStock.product,qProduct)
                .join(qCart.member, qMember)
                .where(qCart.member.eq(member))
                .fetch();
    }
}
