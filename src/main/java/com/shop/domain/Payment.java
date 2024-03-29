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
    @Column(name="total_price")
    private int totalPrice;
    @Column(name="payment_type")
    private PaymentType paymentType;
    @Column(name="payment_date")
    private LocalDateTime paymentDate;

    public void createPayment(int totalPrice, PaymentType paymentType, LocalDateTime paymentDate){
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }
}
