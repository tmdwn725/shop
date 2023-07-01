package com.shop.dto;

import com.shop.domain.ProductFile;
import com.shop.domain.ProductStock;
import com.shop.domain.enums.ProductType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class ProductDTO {
    private Long productSeq;
    private String sellerSeq;
    private String productName;
    private String rpImageSeq;
    private int price;
    private LocalDateTime regDt;
    private ProductType productType;
    private List<ProductFileDTO> productFileList = new ArrayList<>();
    private List<ProductStockDTO> productStockList = new ArrayList<>();
    private String filePth;
}
