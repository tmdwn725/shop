package com.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "review")
public class Review {
    @Id
    @Column(name="review_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewSeq;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_info_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private OrderInfo orderInfo;
    @OneToOne(fetch = FetchType.LAZY)
    @Column(name="review_file_seq")
    @JoinColumn(name = "file_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private File file;
    @Column(name ="title")
    private String title;
    @Column(name ="content")
    private String content;
    @Column(name ="score")
    private int score;
    @Column(name ="review_date")
    private LocalDateTime reviewDate;
}
