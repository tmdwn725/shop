package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class HeartDTO {
    private Long heartSeq;
    private Long productSeq;
    private String productName;
    private int price;
    private MemberDTO member;
    private ProductDTO product;
    private ProductStockDTO productStock;
    private Long heartCnt;
}
