package com.shop.controller;

import com.shop.domain.enums.ProductType;
import com.shop.domain.enums.SizeType;
import com.shop.dto.CartDTO;
import com.shop.dto.FileDTO;
import com.shop.dto.MemberDTO;
import com.shop.dto.ProductDTO;
import com.shop.service.MemberService;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MyPageController {
    private final MemberService memberService;
    private final ProductService productService;

    /**
     * 내 상품관리목록 조회
     * @param model
     * @return
     */
    @RequestMapping("/getMyProductList")
    public String getMyProductList(Model model){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO member = memberService.selectMemberById(memberId);
        Page<ProductDTO> myProductList = productService.selectMyProductList(1,10, member.getMemberSeq());
        model.addAttribute("myProductList", myProductList);
        return "myPage/myProductList";
    }

    /**
     * 내상품상세정보 조회
     * @param model
     * @param product
     * @return
     */
    @RequestMapping("/getMyProductInfo")
    public String getMyProductInfo(Model model, ProductDTO product){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO member = memberService.selectMemberById(memberId);
        ProductDTO productDTO = new ProductDTO();
        ProductType myProductType = null;

        if(product.getProductSeq() > 0){
            productDTO = productService.selectProductInfo(product.getProductSeq());
            myProductType = product.getProductType();
        }

        model.addAttribute("product", productDTO);
        model.addAttribute("myProductType",myProductType);
        model.addAttribute("productType", Arrays.asList(ProductType.values()));
        model.addAttribute("sizeType", Arrays.asList(SizeType.values()));
        return "myPage/myProductInfo";
    }
    @RequestMapping("/saveMyProduct")
    public ResponseEntity<Void> saveMyProduct(ProductDTO productDTO, FileDTO fileDTO) {
        System.out.println(productDTO.getProductContent());
        return ResponseEntity.ok().build();
    }
}
