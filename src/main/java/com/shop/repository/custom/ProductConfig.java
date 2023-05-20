package com.shop.repository.custom;

import com.shop.domain.Product;

import java.util.List;

public interface ProductConfig {
    List<Product> selectProductList();
}
