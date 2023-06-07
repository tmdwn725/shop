package com.shop.controller;

import com.shop.domain.Cart;
import com.shop.domain.enums.ProductType;
import com.shop.dto.CartDTO;
import com.shop.dto.MemberDTO;
import com.shop.dto.ProductDTO;
import com.shop.service.CartService;
import com.shop.service.MemberService;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MemberService memberService;
    private final CartService cartService;
    @RequestMapping("/product")
    public String getProductList(@RequestParam("page") int page, Model model) {
        Page<ProductDTO> productList = productService.selectProductList(page,6);
        model.addAttribute("productList", productList);
        model.addAttribute("productType", Arrays.asList(ProductType.values()));
        return "product/productList";
    }
    @RequestMapping("/productInfo")
    public String productInfo(Model model, ProductDTO productDTO){
        ProductDTO product = productService.selectProductInfo(productDTO.getProductSeq());
        model.addAttribute("product",product);
        return "product/product-details";
    }

    @RequestMapping("/addProductCart")
    public void addProductCart(Model model, CartDTO cartDTO){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.addCart(cartDTO, memberId);
    }
}
