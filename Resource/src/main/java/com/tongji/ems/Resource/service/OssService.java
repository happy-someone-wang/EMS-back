package com.tongji.ems.Resource.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    public String uploadFile(MultipartFile file);

    public Boolean deleteFile(String fileUrl);
}
