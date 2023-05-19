package com.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "product_file")
public class ProductFile {
    @Id
    @Column(name ="product_file_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productFileSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    private Product product;
}
