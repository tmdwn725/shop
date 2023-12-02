package com.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MyPageController {
    private final MemberService memberService;
    private final ProductService productService;
    private final ProductStockService productStockService;
    private final OrderInfoService orderInfoService;
    private final ReviewService reviewService;
    private final PasswordEncoder passwordEncoder;

    private final MailService mailService;

    @RequestMapping("/getMyPage")
    public String getMyPage(Model model){
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO member = memberService.selectMemberById(memberId);
        model.addAttribute("member", member);
        return "myPage/myPage";
    }

    @RequestMapping("/changePassword")
    public void changePassword(HttpServletRequest request, HttpServletResponse response, MemberDTO memberDTO) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> responseData = new HashMap<>();
        long result = 0;
        boolean passwordCheck = false;

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDTO member = memberService.selectMemberById(memberId);
        // 현재 비밀번호 확인
        if(passwordEncoder.matches(memberDTO.getPassword(),member.getPassword())){
            passwordCheck = true;
        }

        if(passwordCheck){
            memberDTO.setMemberId(memberId);
            memberDTO.setNewPassword(passwordEncoder.encode(memberDTO.getNewPassword()));
            result = memberService.changeMyPassword(memberDTO);
            if(result > 0) {
                responseData.put("result", "success");
                responseData.put("message", "비밀번호가 성공적으로 변경되었습니다.");
            }else {
                responseData.put("result", "failure");
                responseData.put("message", "비밀번호 변경에 실패했습니다.");
            }
        }else{
            responseData.put("result", "failure");
            responseData.put("message", "비밀번호 변경에 실패했습니다. 현재 비밀번호를 확인하세요.");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(responseData);

        // JSON 응답을 클라이언트에 보냄
        response.getWriter().write(jsonResponse);
    }
    @RequestMapping("sendEmail")
    public void emailSend(HttpServletRequest request, HttpServletResponse response, MemberDTO memberDTO){
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> responseData = new HashMap<>();
        long result = 0;
        boolean passwordCheck = false;
        int number = mailService.sendMail(memberDTO.getEmail());
    }

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
    public String getMyReviewInfo(Model model, OrderInfoDTO orderInfoDTO, ProductDTO productDTO) {
        ProductDTO product =  productService.selectProductInfo(productDTO.getProductSeq());
        ReviewDTO reviewInfo = reviewService.findReviewInfo(orderInfoDTO);
        product.setSizeType(productDTO.getSizeType());
        model.addAttribute("product",product);
        model.addAttribute("orderInfo", orderInfoDTO);
        model.addAttribute("reviewInfo",reviewInfo);
        return "myPage/myReviewInfo";
    }

    /**
     * 리뷰 등록
     * @param file
     * @param reviewDTO
     * @return
     * @throws IOException
     */
    @RequestMapping("/saveMyReview")
    public ResponseEntity<Void> saveMyReview(@RequestParam("file-img") MultipartFile file, ReviewDTO reviewDTO) throws IOException {
        reviewDTO.setImgFile(file);
        reviewService.saveReviewInfo(reviewDTO);
        return ResponseEntity.ok().build();
    }
}
