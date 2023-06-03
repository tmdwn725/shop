package com.shop.controller;

import com.shop.domain.enums.ProductType;
import com.shop.dto.ProductDTO;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @RequestMapping("/product")
    public String getProductList(Model model) {
        List<ProductDTO> productList = productService.selectProductList(6);
        model.addAttribute("productList", productList);
        model.addAttribute("productType", Arrays.asList(ProductType.values()));
        return "product/productList";
    }
}
