package com.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="order_info_seq")
    private Long orderInfoSeq;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_stock_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductStock productStock;
    @Column(name ="quantity")
    private int quantity;
    @Column(name ="address")
    private String address;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Payment payment;
    @Column(name ="order_date")
    private LocalDateTime orderDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    public void createOrderInfo(ProductStock productStock, int quantity, String address, Payment payment, LocalDateTime orderDate, Member member){
        this.productStock = productStock;
        this.quantity = quantity;
        this.address = address;
        this.payment = payment;
        this.orderDate = orderDate;
        this.member = member;
    }
}
