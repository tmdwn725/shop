package com.shop.repository.custom;

import com.shop.domain.Code;

import java.util.List;

public interface CodeConfig {
    Code selectCode(String cd);
    List<Code> selectCodeList(String grpCd);
}
