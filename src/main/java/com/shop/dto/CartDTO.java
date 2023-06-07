package com.shop.dto;

import lombok.Data;

@Data
public class CartDTO {
    private Long cartSeq;
    private MemberDTO member;
    private ProductStockDTO productStock;
    private int quantity;
    private Long memberSeq;
    private Long productStockSeq;
}
