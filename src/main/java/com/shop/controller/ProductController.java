package com.shop.controller;

import com.shop.domain.Cart;
import com.shop.domain.enums.ProductType;
import com.shop.dto.CartDTO;
import com.shop.dto.MemberDTO;
import com.shop.dto.ProductDTO;
import com.shop.dto.ReviewDTO;
import com.shop.service.CartService;
import com.shop.service.MemberService;
import com.shop.service.ProductService;
import com.shop.service.ReviewService;
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
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CartService cartService;
    private final ReviewService reviewService;

    /**
     * 상품 목록 조회
     * @param page
     * @param type
     * @param searchStr
     * @param model
     * @return
     */
    @RequestMapping("/getProductList")
    public String getProductList(@RequestParam(value="page",required = false, defaultValue="1") int page
            , @RequestParam(value="type",required = false, defaultValue="") String type
            ,  @RequestParam(value="searchStr",required = false, defaultValue="") String searchStr,Model model) {
        ProductType productType = null;
        if(type.length() > 0){
            productType = ProductType.of(type);
        }
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<ProductDTO> productList = productService.selectProductList(page,6,memberId, productType, searchStr);
        model.addAttribute("productList", productList);
        model.addAttribute("productType", Arrays.asList(ProductType.values()));
        model.addAttribute("type",type);
        return "product/productList";
    }

    /**
     * 상품 상세 정보 조회
     * @param page
     * @param model
     * @param productDTO
     * @return
     */
    @RequestMapping("/getProductInfo")
    public String productInfo(@RequestParam(value="page",required = false, defaultValue="1") int page, Model model, ProductDTO productDTO){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        ProductDTO product = productService.selectProductInfo(productDTO.getProductSeq(), memberId);
        Page<ReviewDTO> reviewList = reviewService.selectReviewList(page,3,productDTO.getProductSeq());
        model.addAttribute("product",product);
        model.addAttribute("reviewList",reviewList);
        model.addAttribute("type",product.getProductType().getParentCategory().get().getCode());
        return "product/product-details";
    }

    /**
     * 리뷰목록 조회
     * @param page
     * @param model
     * @param productDTO
     * @return
     */
    @RequestMapping("/getReviewList")
    public String getReviewList(@RequestParam(value="page",required = false, defaultValue="1") int page, Model model, ProductDTO productDTO){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        ProductDTO product = productService.selectProductInfo(productDTO.getProductSeq(), memberId);
        Page<ReviewDTO> reviewList = reviewService.selectReviewList(page,3,productDTO.getProductSeq());
        model.addAttribute("product",product);
        model.addAttribute("reviewList",reviewList);
        model.addAttribute("type",product.getProductType().getParentCategory().get().getCode());
        return "product/productReview";
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

    /**
     * 상품종류 목록 조회
     * @param parentProductType
     * @return
     */
    @RequestMapping("/getChildProductType")
    public List<ProductType> getChildProductType(@RequestParam("parentProductType") ProductType parentProductType) {
        return parentProductType.getChildCategories();
    }

    /**
     * 좋아요 저장
     * @param productDTO
     */
    @RequestMapping("/updateHeartInfo")
    public ResponseEntity<Void> updateHeartInfo(ProductDTO productDTO){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        productService.updateHeartInfo(productDTO, memberId);
        return ResponseEntity.ok().build();
    }
}
