package com.tongji.ems.notice.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFile(MultipartFile file);

    Boolean deleteFile(String fileUrl);
}
