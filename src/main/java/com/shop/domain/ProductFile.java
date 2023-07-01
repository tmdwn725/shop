package com.shop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Product product;
    @Column(name ="file_cls_cd")
    private String fileClsCd;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_file_seq", referencedColumnName = "file_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private File file;
    public void createProductFile(Product product, String fileClsCd, File file){
        this.product = product;
        this.fileClsCd = fileClsCd;
        this.file =file;
    }
}
