package com.shop.service;

import com.querydsl.core.types.Order;
import com.shop.domain.Cart;
import com.shop.domain.Member;
import com.shop.domain.OrderInfo;
import com.shop.domain.Payment;
import com.shop.dto.PaymentDTO;
import com.shop.repository.CartRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderInfoRepository;
import com.shop.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderInfoRepository orderInfoRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    @Transactional
    public void savePayment(PaymentDTO paymentDTO, String memberId){
        // 현재 날짜와 시간 취득
        LocalDateTime nowDateTime = LocalDateTime.now();
        Member member  = memberRepository.fingByMemberId(memberId);

        for(String cartSeq : paymentDTO.getCartSeqList()){
            Optional<Cart> cart = cartRepository.findById(Long.parseLong(cartSeq));
            Payment payment = new Payment();
            payment.createPayment(cart.get().getProductStock().getProduct().getPrice() * cart.get().getQuantity(), paymentDTO.getPaymentType(),nowDateTime);
            paymentRepository.save(payment);

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.createOrderInfo(cart.get().getProductStock(),cart.get().getQuantity(),paymentDTO.getAddress(),payment,nowDateTime,member);
            orderInfoRepository.save(orderInfo);
        }
    }

}
