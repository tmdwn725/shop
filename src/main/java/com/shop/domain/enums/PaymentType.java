package com.shop.domain.enums;

public enum PaymentType {
    CARD("C", "카드"),
    ACCOUNT("A","계좌이체");
    private final String code;
    private final String value;
    public String getCode(){
        return code;
    }
    public String getValue(){
        return value;
    }
    PaymentType(String code, String value){
        this.code = code;
        this.value = value;
    }
}
