package com.shop.controller;

import com.shop.domain.enums.ProductType;
import com.shop.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class CartController {
    @RequestMapping("/cart")
    public String cart(Model model) {
        return "cart/shop-cart";
    }
}
