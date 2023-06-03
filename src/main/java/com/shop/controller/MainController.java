package com.shop.controller;

import com.shop.domain.enums.ProductType;
import com.shop.dto.CodeDTO;
import com.shop.dto.ProductDTO;
import com.shop.service.CodeService;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    @RequestMapping("/main")
    public String main(Model model) {
        List<ProductDTO> productList = productService.selectProductList(8);
        model.addAttribute("productType",Arrays.asList(ProductType.values()));
        model.addAttribute("productList", productList);
        return "main/main";
    }
    @RequestMapping("/productInfo")
    public String productInfo(Model model, ProductDTO productDTO){
        ProductDTO product = productService.selectProductInfo(productDTO.getProductSeq());
        model.addAttribute("product",product);
        return "product/product-details";
    }
}
