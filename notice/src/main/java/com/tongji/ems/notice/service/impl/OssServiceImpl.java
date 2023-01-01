package com.tongji.ems.notice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.tongji.ems.notice.service.OssService;
import com.tongji.ems.notice.util.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFile(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        if (file != null) {
            try {
                System.out.println("接收到文件"+file);
                //创建OSS实例
                OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
                // 获取上传文件流
                InputStream inputStream = file.getInputStream();
                //获取文件名称
                String fileName = file.getName();
                // 1 在文件名称里面添加随机唯一的值
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName = uuid + "/" + fileName;
                // 2把文件按照日期进行分类
                // 获取当前日期
                String datePath = new DateTime().toString("yyyy/MM/dd");
                fileName = datePath + "/" + fileName;

                //设置OSS请求头
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("text/plain;charset=utf-8");
                // 调用OSS方法上传
                ossClient.putObject(bucketName, fileName, inputStream, metadata);
                // 关闭OSS客户端
                ossClient.shutdown();
                // 文件访问路径
                String url = "https://" + bucketName + "." + endPoint + '/' + fileName;
                return url;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return "Unable to upload. File is empty.";
        }
        return null;
    }


    @Override
    public Boolean deleteFile(String fileUrl) {
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

            ossClient.deleteObject(bucketName, fileUrl);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
