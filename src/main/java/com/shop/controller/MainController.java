package com.shop.controller;

import com.shop.domain.enums.ProductType;
import com.shop.dto.ProductDTO;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    @RequestMapping("/main")
    public String main(Model model) {
        Page<ProductDTO> productList = productService.selectProductList(1,8);
        model.addAttribute("productType",Arrays.asList(ProductType.values()));
        model.addAttribute("productList", productList.getContent());
        return "main/main";
    }
}
