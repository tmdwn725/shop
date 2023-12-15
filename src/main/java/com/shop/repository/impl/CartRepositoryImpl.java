package com.shop.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.domain.*;
import com.shop.repository.custom.CartConfig;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CartRepositoryImpl implements CartConfig {
    private final JPAQueryFactory queryFactory;
    QCart qCart = QCart.cart;
    QMember qMember = QMember.member;
    QProductStock qProductStock = QProductStock.productStock;
    public List<Cart> myCartList(Member member){
        return queryFactory
                .select(Projections.fields(Cart.class,
                        qCart.cartSeq,
                        qCart.quantity,
                        qCart.member,
                        qCart.productStock,
                        qCart.productStock.product))
                .from(qCart)
                .join(qCart.productStock,qProductStock)
                .join(qCart.member, qMember)
                .where(qCart.member.eq(member))
                .fetch();
    }
    public long updateProductQuantity(Long cartSeq, int quantity){
        return queryFactory.update(qCart).where(qCart.cartSeq.eq(cartSeq))
                .set(qCart.quantity, quantity)
                .execute();
    }
}
