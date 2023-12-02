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
    private Role role;
    private String newPassword;
    private String password;
    private String authority;
}
