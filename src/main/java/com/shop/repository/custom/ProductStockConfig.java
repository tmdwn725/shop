package com.shop.repository.custom;

import com.shop.domain.ProductStock;

import java.util.ArrayList;
import java.util.List;

public interface ProductStockConfig {
    ProductStock selectProductStock(Long productSeq, String sizeType);
    void updateProductStockCount(Long productSeq, String size, int count);
}
