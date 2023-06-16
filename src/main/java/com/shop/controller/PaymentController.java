package com.shop.controller;

import com.shop.dto.CartDTO;
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
    @RequestMapping(value = "/payment", method = {RequestMethod.POST, RequestMethod.GET})
    public String getPaymentInfo(Model model, @ModelAttribute("CartDTO") CartDTO cartDTO){
        model.addAttribute("myCartList",cartDTO.getCartList());
        return "payment/payment";
    }
}
