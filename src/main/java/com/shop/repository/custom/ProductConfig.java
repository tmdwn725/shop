package com.shop.repository.custom;

import com.shop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductConfig {
    Page<Product> selectProductList(Pageable pageable, Long sellerSeq);
    Product selectProduct(Long productSeq);
}
