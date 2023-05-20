package com.shop.domain;

import com.shop.dto.Role;
import lombok.Getter;

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
    @Column(name = "price")
    private int price;
    @Column(name = "reg_dt")
    private LocalDateTime regDt;
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<ProductFile> productFileList = new ArrayList<>();
    @Transient
    private String filePth;
    public void createProduct(Long sellerSeq, String productName, int price, LocalDateTime regDt){
        this.sellerSeq = sellerSeq;
        this.productName = productName;
        this.price = price;
        this.regDt = regDt;
    }
}
