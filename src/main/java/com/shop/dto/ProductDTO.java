package com.shop.dto;

import com.shop.domain.ProductFile;
import com.shop.domain.ProductStock;
import com.shop.domain.enums.ProductType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.collection.internal.PersistentList;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ProductDTO {
    private Long productSeq;
    private String sellerSeq;
    private String productName;
    private String productContent;
    private String rpImageSeq;
    private int price;
    private LocalDateTime regDt;
    private ProductType productType;
    private List<ProductFileDTO> productFileList = new ArrayList<>();
    private List<ProductStock> productStockList = new ArrayList<>();
    private String filePth;
    private Map<String,String> sizeTypes = new HashMap<>();
}
