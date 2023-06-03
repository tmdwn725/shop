package com.shop.domain.enums;

public enum ProductDetailType implements BaseEnum {
    COAT("COAT", "코트", ProductType.OUTER),
    CARDIGAN("Cardigan", "카디건", ProductType.OUTER),
    PADDING("Padding", "패딩", ProductType.OUTER),
    NEAT("neat", "니트", ProductType.TOP),
    TEE("Tee", "티셔츠", ProductType.TOP);

    private String code;
    private String value;
    private ProductType productType;
    ProductDetailType(String code, String value, ProductType productType){
        this.code = code;
        this.value = value;
        this.productType = productType;
    }
    public String getCode(){
        return code;
    }
    public String getValue(){
        return value;
    }
    public ProductType productType(){
        return productType;
    }
}
