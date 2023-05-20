package com.shop.service;

import com.shop.common.ModelMapperUtil;
import com.shop.domain.Product;
import com.shop.dto.ProductDTO;
import com.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDTO> selectProductList(){
        List<Product> pr = productRepository.selectProductList();
        List<ProductDTO> list = ModelMapperUtil.mapAll(pr, ProductDTO.class);
        return list;
    }
}
