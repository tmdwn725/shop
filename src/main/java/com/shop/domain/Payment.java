package com.shop.domain;

import com.shop.domain.enums.PaymentType;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="payment_seq")
    private Long paymentSeq;
    @OneToMany(mappedBy = "payment", cascade = CascadeType.PERSIST)
    private List<OrderInfo> orderInfoList = new ArrayList<>();
    @Column(name="payment_date")
    private LocalDateTime paymentDate;
    @Column(name="total_price")
    private int totalPrice;
    @Column(name="payment_type")
    private PaymentType paymentType;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;
}
