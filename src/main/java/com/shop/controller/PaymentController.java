package com.shop.controller;

import com.shop.dto.CartDTO;
import com.shop.dto.MemberDTO;
import com.shop.service.MemberService;
import com.shop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final MemberService memberService;
    @RequestMapping(value = "/payment", method = {RequestMethod.POST, RequestMethod.GET})
    public String getPaymentInfo(Model model, @ModelAttribute("CartDTO") CartDTO cartDTO){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO member = memberService.selectMemberById(memberId);
        int totalPrice = paymentService.getTotalPrice(cartDTO.getCartList());

        model.addAttribute("member", member);
        model.addAttribute("myCartList",cartDTO.getCartList());
        model.addAttribute("totalPrice",totalPrice);
        return "payment/payment";
    }
}
