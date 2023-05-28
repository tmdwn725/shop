package com.shop.dto;

import com.shop.domain.enums.ProductType;
import lombok.Data;

@Data
public class MemberDTO {
    private long memberSeq;
    private String memberId;
    private String memberNm;
    private String password;
    private String authority;
    private Role role;
}
