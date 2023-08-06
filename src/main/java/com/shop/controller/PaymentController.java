package com.shop.controller;

import com.shop.dto.CartDTO;
import com.shop.dto.MemberDTO;
import com.shop.dto.PaymentDTO;
import com.shop.service.MemberService;
import com.shop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final MemberService memberService;
    private final PaymentService paymentService;
    /**
     * 상품 결제창 조회
     * @param model
     * @param cartDTO
     * @return
     */
    @RequestMapping(value = "/getPaymentInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public String getPaymentInfo(Model model, @ModelAttribute("CartDTO") CartDTO cartDTO){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO member = memberService.selectMemberById(memberId);
        // 총 가격
        int totalPrice = cartDTO.getCartList().stream()
                            .mapToInt(cart -> cart.getPrice() * cart.getQuantity())
                            .sum();
        model.addAttribute("member", member);
        model.addAttribute("myCartList",cartDTO.getCartList());
        model.addAttribute("totalPrice",totalPrice);
        return "payment/payment";
    }
    @RequestMapping(value = "/saveOrderInfo")
    public ResponseEntity<Void> saveOrderInfo(PaymentDTO payment){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        paymentService.savePayment(payment,memberId);
        return ResponseEntity.ok().build();
    }
}
