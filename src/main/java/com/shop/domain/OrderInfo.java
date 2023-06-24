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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_seq")
    private Payment payment;
    @Column(name ="order_date")
    private LocalDateTime orderDate;
}
