package com.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shop.common.enumConvert.ProductTypeConverter;
import com.shop.domain.enums.ProductType;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "product")
public class Product {
    @Id
    @Column(name ="product_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSeq;
    @Column(name = "seller_seq")
    private Long sellerSeq;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_content")
    private String productContent;
    @Convert(converter = ProductTypeConverter.class)
    @Column(name = "product_type")
    private ProductType productType;
    @Column(name = "price")
    private int price;
    @Column(name = "reg_dt")
    private LocalDateTime regDt;
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<ProductFile> productFileList = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<ProductStock> productStockList = new ArrayList<>();
    @Transient
    private String filePth;
    public void createProduct(Long sellerSeq, String productName ,String productContent, ProductType productType, int price, LocalDateTime regDt){
        this.sellerSeq = sellerSeq;
        this.productName = productName;
        this.productContent = productContent;
        this.productType = productType;
        this.price = price;
        this.regDt = regDt;
    }
}
