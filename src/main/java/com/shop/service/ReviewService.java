package com.shop.service;

import com.shop.common.FileUtil;
import com.shop.common.ModelMapperUtil;
import com.shop.domain.File;
import com.shop.domain.OrderInfo;
import com.shop.domain.Product;
import com.shop.domain.Review;
import com.shop.domain.enums.ProductType;
import com.shop.dto.OrderInfoDTO;
import com.shop.dto.ProductDTO;
import com.shop.dto.ReviewDTO;
import com.shop.repository.FileRepository;
import com.shop.repository.OrderInfoRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Value("${root.path}")
    private String rootPth;
    @Value("${image.review.path}")
    private String imageUploadPath;
    private final OrderInfoRepository orderInfoRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productSRepository;
    private final FileRepository fileRepository;

    /**
     * 리뷰 조회
     * @param orderInfoDTO
     * @return
     */
    public ReviewDTO findReviewInfo(OrderInfoDTO orderInfoDTO){
        ReviewDTO result = new ReviewDTO();
        OrderInfo orderInfo = orderInfoRepository.findById(orderInfoDTO.getOrderInfoSeq()).get();
        Review review = reviewRepository.selectReviewInfo(orderInfo);
        if(review != null){
            result = ModelMapperUtil.map(review,ReviewDTO.class);
        }
        return result;
    }

    /**
     * 상품 리뷰정보 저장
     * @param reviewDTO
     */
    public void saveReviewInfo(ReviewDTO reviewDTO){
        // 현재 날짜와 시간 취득
        LocalDateTime nowdatetime = LocalDateTime.now();

        Review review = new Review();
        OrderInfo orderInfo = orderInfoRepository.findById(reviewDTO.getOrderSeq()).get();

        String filePth = imageUploadPath + "/" + reviewDTO.getOrderSeq();
        String saveFilePth = FileUtil.saveFile(reviewDTO.getImgFile(), rootPth, filePth);
        File fileInfo = new File();
        fileInfo.CreateFile(reviewDTO.getImgFile().getSize(), reviewDTO.getImgFile().getOriginalFilename(), saveFilePth, "jpg");
        fileRepository.save(fileInfo);

        review.createReview(orderInfo,fileInfo,reviewDTO.getContent(),reviewDTO.getScore(),nowdatetime);

        reviewRepository.save(review);
    }

    public Page<ReviewDTO> selectReviewList(int start, int limit,Long productSeq){
        PageRequest pageRequest = PageRequest.of(start-1, limit);
        Page<Review> result = reviewRepository.selectReviewList(pageRequest,productSeq);
        int total = result.getTotalPages();
        pageRequest = PageRequest.of((total-1), limit);
        List<ReviewDTO> list = ModelMapperUtil.mapAll(result.getContent(), ReviewDTO.class);
        return new PageImpl<>(list, pageRequest, total);
    }
}
