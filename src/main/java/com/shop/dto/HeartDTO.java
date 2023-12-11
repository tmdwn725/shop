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
    private String productImgFlPth;
    private Long heartCnt;
    public HeartDTO(){

    }

    @QueryProjection
    public HeartDTO(Long heartSeq, Long productSeq, String productName, int price, String productImgFlPth, Long heartCnt){
        this.heartSeq = heartSeq;
        this.productSeq = productSeq;
        this.productName = productName;
        this.price = price;
        this.productImgFlPth = productImgFlPth;
        this.heartCnt = heartCnt;
    }
}
