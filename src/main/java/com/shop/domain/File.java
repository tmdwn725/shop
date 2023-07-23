package com.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "file")
public class File {
    @Id
    @Column(name ="file_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileSeq;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_pth")
    private String filePth;
    @Column(name = "file_ext")
    private String fileExt;
    @Column(name = "file_size")
    private long fileSize;
    public void CreateFile(long fileSize, String ...file){
        this.fileName = file[0];
        this.filePth = file[1];
        this.fileExt = file[2];
        this.fileSize = fileSize;

    }
}
