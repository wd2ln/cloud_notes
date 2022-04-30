package com.zp.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUploadUtil {

    public static String upload(MultipartFile multipartFile){
        // 准备一个存放图片的路径
        String path = "/home/long-an/Downloads/apache-tomcat-8.5.75/webapps/upload";

        File file = new File(path);
        //判断路径是否存在
        if (!file.exists()){
            file.mkdirs();
        }
        //获取文件名
        String filename = multipartFile.getOriginalFilename();
        //判断文件是否可用
        String[] split = filename.split(".");
        if (split[0].equals("")){
            return null;
        }
        System.out.println("=====");
        //获取文件后缀
        String suf = filename.substring(filename.indexOf("."));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = simpleDateFormat.format(new Date());
        //拼接文件名
        String newFile=format+suf;
        try {
            multipartFile.transferTo(new File(file,newFile));
            return newFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
