package com.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    @RequestMapping("/payment")
    public String getPaymentInfo(){
        return "payment/payment";
    }
}
