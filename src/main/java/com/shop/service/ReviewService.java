package com.shop.service;

import com.shop.common.ModelMapperUtil;
import com.shop.domain.OrderInfo;
import com.shop.domain.Review;
import com.shop.dto.OrderInfoDTO;
import com.shop.dto.ReviewDTO;
import com.shop.repository.OrderInfoRepository;
import com.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final OrderInfoRepository orderInfoRepository;
    private final ReviewRepository reviewRepository;
    public ReviewDTO findReviewInfo(OrderInfoDTO orderInfoDTO){
        OrderInfo orderInfo = orderInfoRepository.findById(orderInfoDTO.getOrderInfoSeq()).get();
        Review result = reviewRepository.selectReviewInfo(orderInfo);
        ReviewDTO review = ModelMapperUtil.map(result,ReviewDTO.class);
        return review;

    }
}
