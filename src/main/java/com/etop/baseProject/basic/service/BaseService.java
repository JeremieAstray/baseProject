package com.etop.baseProject.basic.service;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * 业务处理基类
 * @author Pengo.Wen
 * Created by pengo on 14-9-13.
 */
public class BaseService implements Serializable {

    protected Logger log = Logger.getLogger(this.getClass());

    protected String getKeyWords(String keyWords) {
        return "%" + keyWords + "%";
    }

    protected List<Object> getParam(Object... param) {
        return Arrays.asList(param);
    }

    protected Map<String, Object> createParamMap() {
        return new HashMap<>();
    }

    protected int parseInt(Object obj) {
        return ((Long) obj).intValue();
    }

    /**
     * 上传多媒体文件
     * @param file 文件
     * @param path 路径
     * @param type 文件类型:image,voice,video,thumb
     * @return
     *//*
    public String uploadMedia(MultipartFile file,String path,String type){
        log.debug("******上传多媒体文件******");
        int limitedSize=1024*1024;
        String newFileName = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        switch(type)
        {
            case "image":limitedSize = 1024*1024;
                if(!(fileName.toLowerCase().endsWith(".jpg")))
                {
                    log.debug("******上传文件格式不正确，应为JPG格式******");
                    return "error:上传文件格式不正确，应为JPG格式";
                }
                break;
            case "voice":limitedSize = 1024*1024*1024;
                if(!(fileName.toLowerCase().endsWith(".arm")||fileName.toLowerCase().endsWith(".mp3")))
                {
                    log.debug("******上传文件格式不正确，应为ARM/MP3格式******");
                    return "error:上传文件格式不正确，应为ARM/MP3格式";
                }
                break;
            case "video":limitedSize = 10*1024*1024;
                if(!(fileName.toLowerCase().endsWith(".mp4")))
                {
                    log.debug("******上传文件格式不正确，应为MP4格式******");
                    return "error:上传文件格式不正确，应为MP4格式";
                }
                break;
            case "thumb":limitedSize = 64*1024;
                if(!(fileName.toLowerCase().endsWith(".jpg")))
                {
                    log.debug("******上传文件格式不正确，应为JPG格式******");
                    return "error:上传文件格式不正确，应为JPG格式";
                }
                break;
        }
        if(file.getSize()>limitedSize)
        {
            log.debug("******文件过大，应小于" + limitedSize + "B******");
            return "error:文件过大，应小于" + limitedSize/1024 + "KB";
        }
        String fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        newFileName += fileType;
        File targetFile = new File(path, newFileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFileName;
    }

    *//**
     * 删除图像
     * @param path
     * @param fileName
     * @return
     *//*
    public boolean deleteImg(String path,String fileName){
        log.debug("******删除图像******");
        File file = new File(path,fileName);
        if(file.exists())
            return file.delete();
        else
            return false;
    }*/
}
