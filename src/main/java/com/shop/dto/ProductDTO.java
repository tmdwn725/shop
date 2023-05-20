package com.shop.dto;

import com.shop.domain.ProductFile;
import lombok.Data;

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
    private List<ProductFile> productFileList = new ArrayList<>();
    private String filePth;
}
