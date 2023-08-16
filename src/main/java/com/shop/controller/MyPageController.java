package com.shop.controller;

import com.shop.domain.OrderInfo;
import com.shop.domain.enums.ProductType;
import com.shop.dto.MemberDTO;
import com.shop.dto.OrderInfoDTO;
import com.shop.dto.ProductDTO;
import com.shop.dto.ReviewDTO;
import com.shop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MyPageController {
    private final MemberService memberService;
    private final ProductService productService;
    private final ProductStockService productStockService;
    private final OrderInfoService orderInfoService;
    private final ReviewService reviewService;
    /**
     * 내 상품관리목록 조회
     * @param model
     * @return
     */
    @RequestMapping("/getMyProductList")
    public String getMyProductList(Model model, @RequestParam(value="page", required = false, defaultValue="1") int page){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO member = memberService.selectMemberById(memberId);
        Page<ProductDTO> myProductList = productService.selectMyProductList(page,10, member.getMemberSeq());
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
        ProductDTO productDTO = new ProductDTO();
        ProductType myProductType = ProductType.CARDIGAN;

        if(product.getProductSeq() > 0){
            productDTO = productService.selectProductInfo(product.getProductSeq());
            myProductType = productDTO.getProductType();
        }

        model.addAttribute("product", productDTO);
        model.addAttribute("myProductType",myProductType);
        model.addAttribute("productType", Arrays.asList(ProductType.values()));
        return "myPage/myProductInfo";
    }

    /**
     * 내 상품 등록
     * @param productDTO
     * @return
     */
    @RequestMapping("/saveMyProduct")
    public ResponseEntity<Void> saveMyProduct(ProductDTO productDTO
            , @RequestParam("file-img1") MultipartFile file1, @RequestParam("file-img2") MultipartFile file2
            , @RequestParam("file-img3") MultipartFile file3, @RequestParam("file-img4") MultipartFile file4) throws IOException {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO member = memberService.selectMemberById(memberId);
        productDTO.setProductType(ProductType.of(productDTO.getProductTypeCd()));
        productDTO.setSellerSeq(member.getMemberSeq());
        MultipartFile[] fileList = {file1, file2, file3, file4};
        productService.saveProductInfo(productDTO, fileList);
        return ResponseEntity.ok().build();
    }

    /**
     * 내 상품 삭제
     * @param product
     * @return
     */
    @RequestMapping("/removeProduct")
    public ResponseEntity<Void> removeProduct(ProductDTO product) {
        productService.removeProduct(product);
        return ResponseEntity.ok().build();
    }
    /**
     * 내 상품 재고 삭제
     * @param product
     * @return
     */
    @RequestMapping("/removeProductStock")
    public ResponseEntity<Void> removeProductStock(ProductDTO product) {
        productStockService.deleteProductStock(product);
        return ResponseEntity.ok().build();
    }

    /**
     * 내 주문 내역 조회
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/getMyOrderList")
    public String getMyOrderList(Model model, @RequestParam(value="page", required = false, defaultValue="1") int page) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<OrderInfoDTO> myOrderList = orderInfoService.selectOrderInfoList(page, 10,memberId);
        model.addAttribute("myOrderList", myOrderList);
        return "myPage/myOrderList";
    }

    /**
     * 내 리뷰 관리 화면
     * @param model
     * @return
     */
    @RequestMapping("/getMyReviewInfo")
    public String getMyReviewInfo(Model model, OrderInfoDTO orderInfoDTO) {
        model.addAttribute("orderInfo", orderInfoDTO);
        ReviewDTO reviewInfo = reviewService.findReviewInfo(orderInfoDTO);
        model.addAttribute("reviewInfo",reviewInfo);
        return "myPage/myReviewInfo";
    }

    @RequestMapping("/saveMyReview")
    public ResponseEntity<Void> saveMyReview(@RequestParam("file-img1") MultipartFile file1, @RequestParam("file-img2") MultipartFile file2
            , @RequestParam("file-img3") MultipartFile file3, @RequestParam("file-img4") MultipartFile file4) throws IOException {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO member = memberService.selectMemberById(memberId);
        return ResponseEntity.ok().build();
    }
}
