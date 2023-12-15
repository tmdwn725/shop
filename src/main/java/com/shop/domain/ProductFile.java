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
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;
    @Column(name ="file_cls_cd")
    private String fileClsCd;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_file_seq", referencedColumnName = "file_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private File file;
    @Column(name ="odr")
    private int odr;
    public void createProductFile(Product product, String fileClsCd, File file, int odr){
        this.product = product;
        this.fileClsCd = fileClsCd;
        this.file = file;
        this.odr = odr;
    }
}
