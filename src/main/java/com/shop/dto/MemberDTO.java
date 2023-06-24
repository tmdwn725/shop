package com.shop.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MemberDTO {
    private long memberSeq;
    private String memberId;
    private String name;
    private String email;
    private String address;
    private String detailAddress;
    private String telNo;
    private String password;
    private String authority;
    private Role role;
}
