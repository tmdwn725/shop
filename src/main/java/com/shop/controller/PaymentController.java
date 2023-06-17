package com.shop.controller;

import com.shop.dto.CartDTO;
import com.shop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @RequestMapping(value = "/payment", method = {RequestMethod.POST, RequestMethod.GET})
    public String getPaymentInfo(Model model, @ModelAttribute("CartDTO") CartDTO cartDTO){
        int totalPrice = paymentService.getTotalPrice(cartDTO.getCartList());
        model.addAttribute("myCartList",cartDTO.getCartList());
        model.addAttribute("totalPrice",totalPrice);
        return "payment/payment";
    }
}
