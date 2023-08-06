package com.shop.dto;

import com.shop.domain.Member;
import com.shop.domain.OrderInfo;
import com.shop.domain.enums.PaymentType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PaymentDTO {
    private Long paymentSeq;
    private OrderInfo orderInfo;
    private LocalDateTime paymentDate;
    private int totalPrice;
    private PaymentType paymentType;
    private MemberDTO member;
    private String address;
    private List<String> cartSeqList = new ArrayList();
}
