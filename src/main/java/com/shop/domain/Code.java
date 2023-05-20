package com.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "code")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="cd")
    private String cd;
    @Column(name ="grp_cd")
    private String grpCd;
    @Column(name = "cd_nm")
    private String cdNm;
}
