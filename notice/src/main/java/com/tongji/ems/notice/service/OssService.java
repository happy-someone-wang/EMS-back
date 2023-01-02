package com.tongji.ems.notice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface OssService {
    String uploadFile(MultipartFile file);

    Boolean deleteFile(String fileUrl);

    Map<String,Object> uploadImage(String file, String filename);
}
