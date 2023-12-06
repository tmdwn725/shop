package com.shop.dto;

import lombok.Data;

@Data
public class HeartDTO {
    private Long heartSeq;
    private MemberDTO member;
    private ProductDTO product;
}
