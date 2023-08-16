package com.shop.repository.custom;

import com.shop.domain.OrderInfo;
import com.shop.domain.Review;

public interface ReviewConfig {
    Review selectReviewInfo(OrderInfo orderInfo);
}
