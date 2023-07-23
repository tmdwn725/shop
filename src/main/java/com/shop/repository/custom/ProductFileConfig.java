package com.shop.repository.custom;

import com.shop.domain.ProductFile;

public interface ProductFileConfig {
    void deleteProductFile(long productSeq,String fileClsCd, int odr);
}
