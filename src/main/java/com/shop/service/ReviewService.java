package com.shop.service;

import com.shop.common.ModelMapperUtil;
import com.shop.domain.OrderInfo;
import com.shop.domain.Review;
import com.shop.dto.OrderInfoDTO;
import com.shop.dto.ReviewDTO;
import com.shop.repository.OrderInfoRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final OrderInfoRepository orderInfoRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productSRepository;
    public ReviewDTO findReviewInfo(OrderInfoDTO orderInfoDTO){
        ReviewDTO result = new ReviewDTO();
        OrderInfo orderInfo = orderInfoRepository.findById(orderInfoDTO.getOrderInfoSeq()).get();
        Review review = reviewRepository.selectReviewInfo(orderInfo);
        if(review != null){
            result = ModelMapperUtil.map(review,ReviewDTO.class);
        }
        return result;
    }
}
