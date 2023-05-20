package com.shop.controller;

import com.shop.dto.ProductDTO;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    @RequestMapping("/main")
    public String main(Model model) {
        List<ProductDTO> productList = productService.selectProductList();
        model.addAttribute("productList", productList);
        return "main/main";
    }
    @RequestMapping("/productInfo")
    public String productInfo(){
        return "product/product-details";
    }
}
