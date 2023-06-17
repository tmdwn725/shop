package com.shop.service;

import com.shop.dto.CartDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    public int getTotalPrice(List<CartDTO> cartList){
        int result = 0;
        for(CartDTO cart : cartList){
            result += cart.getPrice() * cart.getQuantity();
        }
        return result;
    }

}
