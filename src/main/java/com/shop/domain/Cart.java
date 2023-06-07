package com.shop.domain;

import lombok.Getter;

import javax.persistence.*;
@Entity
@Getter
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name ="cart_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_stock_seq")
    private ProductStock productStock;
    @Column(name ="quantity")
    private int quantity;

    public void createCart(Member member, ProductStock productStock, int quantity){
        this.member = member;
        this.productStock = productStock;
        this.quantity = quantity;
    }
}
