package com.tongji.ems.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
@Component
@Service
@FeignClient("notice")
public interface FileStoreClient {
    /**
     * 上传文件至阿里云OSS
     *
     * @param file 要上传的文件
     * @return 访问文件的URL
     */
    @PostMapping(value = "/file" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestPart(value = "file") MultipartFile file);

    /**
     * 删除存储在OSS中的文件
     *
     * @param fileUrl 访问文件的URL
     * @return 是否删除成功
     */
    @DeleteMapping("/file")
    public Boolean deleteFile(@RequestParam("fileUrl") String fileUrl);
}
