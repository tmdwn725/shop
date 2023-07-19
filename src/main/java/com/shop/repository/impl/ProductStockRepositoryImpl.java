package com.shop.repository.impl;

import com.shop.domain.QProduct;
import com.shop.domain.QProductStock;
import com.shop.repository.custom.ProductStockConfig;

public class ProductStockRepositoryImpl implements ProductStockConfig {
    QProductStock qProductStock = QProductStock.productStock;
}
