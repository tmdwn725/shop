package com.shop.domain.enums;

public enum ProductType implements BaseEnum {
    OUTER("O", "아우터"),
    TOP("T","상의"),
    PANTS("P","하의"),
    SHOES("S","신발"),
    ACCESSORIES("A","악세사리");
    private String code;
    private String value;
    ProductType(String code, String value){
        this.code = code;
        this.value = value;
    }
    public String getCode(){
        return code;
    }
    public String getValue(){
        return value;
    }
}
