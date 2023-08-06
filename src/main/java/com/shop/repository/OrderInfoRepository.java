package com.shop.repository;

import com.shop.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo,Long> {
}
