package com.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "code")
public class Code {
    @Id
    @Column(name ="cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cd;
    @Column(name ="grp_cd")
    private String grpCd;
    @Column(name = "cd_nm")
    private String cdNm;
    @Column(name = "eng_cd_nm")
    private String engCdNm;
    public void createCd(String ...cd){
        this.cd = cd[0];
        this.grpCd = cd[1];
        this.cdNm = cd[2];
        this.engCdNm = cd[3];
    }
}
