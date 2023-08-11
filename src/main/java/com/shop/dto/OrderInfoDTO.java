package com.shop.dto;

import com.shop.domain.Member;
import com.shop.domain.Payment;
import com.shop.domain.ProductStock;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderInfoDTO {
    private Long orderInfoSeq;
    private ProductStock productStock;
    private int quantity;
    private String address;
    private Payment payment;
    private LocalDateTime orderDate;
    private Member member;
}
