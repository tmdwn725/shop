package com.shop.service;

import com.shop.common.ModelMapperUtil;
import com.shop.domain.Code;
import com.shop.dto.CodeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeService {
    //private final CodeRepository codeRepository;
    public List<CodeDTO> findCdList(String grpCd){
        List<Code> list = null;
        List<CodeDTO> codeList = ModelMapperUtil.mapAll(list, CodeDTO.class);
        return codeList;
    }
}
