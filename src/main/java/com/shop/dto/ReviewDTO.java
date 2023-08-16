package com.shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {
    private Long reviewSeq;
    private Long orderSeq;
    private String content;
    private int score;
    private LocalDateTime reviewDate;
}
