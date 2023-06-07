package com.shop.dto;

import lombok.Data;

@Data
public class ProductFileDTO {
    private Long productFileSeq;
    private ProductDTO product;
    private String fileClsCd;
    private FileDTO file;
}
