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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getProductList(@RequestParam(value="page",required = false, defaultValue="1") int page
            , @RequestParam(value="type",required = false, defaultValue="") String type
            ,  @RequestParam(value="searchStr",required = false, defaultValue="") String searchStr,Model model) {
        ProductType productType = null;
        if(type.length() > 0){
            productType = ProductType.of(type);
        }
        Page<ProductDTO> productList = productService.selectProductList(page,6,productType, searchStr);
        model.addAttribute("productList", productList);
        model.addAttribute("productType", Arrays.asList(ProductType.values()));
        model.addAttribute("type",type);
        return "product/productList";
    }
    @RequestMapping("/productInfo")
    public String productInfo(Model model, ProductDTO productDTO){
        ProductDTO product = productService.selectProductInfo(productDTO.getProductSeq());
        model.addAttribute("product",product);
        model.addAttribute("type",product.getProductType().getParentCategory().get().getCode());
        return "product/product-details";
    }
    /**
     * 장바구니 추가
     * @param model
     * @param cartDTO
     * @return
     */
    @RequestMapping("/addProductCart")
    public ResponseEntity<Void> addProductCart(Model model, CartDTO cartDTO){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.addCart(cartDTO, memberId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/getChildProductType")
    public List<ProductType> getChildProductType(@RequestParam("parentProductType") ProductType parentProductType) {
        return parentProductType.getChildCategories();
    }
}
