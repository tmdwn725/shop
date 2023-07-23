package com.shop.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
public class FileUtil {
    public static void saveFile(MultipartFile file, String filePth){
        try {
            File dir = new File(filePth);
            if(!dir.exists()){
                dir.mkdir();
            }
            File destFile = new File(filePth + "/" + file.getOriginalFilename());
            if(!destFile.exists()){
                file.transferTo(destFile);
            }
        }catch (Exception e){
            log.error("파일 동록 에러!!!");
        }
    }
}
