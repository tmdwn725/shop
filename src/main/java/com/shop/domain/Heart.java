package com.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="heart")
public class Heart {
    @Id
    @Column(name ="heart_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartSeq;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;
    public void createHeart(Member member, Product product){
        this.member = member;
        this.product = product;
    }
}
