package com.shop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.querydsl.core.types.Order;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "product_stock")
public class ProductStock {
    @Id
    @Column(name ="product_stock_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productStockSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    @JsonBackReference
    private Product product;
    @Column(name ="product_size")
    private String productSize;
    @Column(name ="product_count")
    private int productCount;
    @OneToMany(mappedBy = "productStock", cascade = CascadeType.PERSIST)
    private List<Cart> cartList = new ArrayList<>();
    public void createProductStock(Product product, String size, int count){
        this.product = product;
        this.productSize = size;
        this.productCount = count;
    }
}
