package com.tongji.ems.Resource.controller;

import com.tongji.ems.Resource.model.Resource;
import com.tongji.ems.Resource.service.OssService;
import com.tongji.ems.Resource.service.ResourceService;
import com.tongji.ems.feign.clients.FileStoreClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.tongji.ems.Resource.util.GenerateIdTenth.get10UniqueId;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private OssService ossService;


    public static String STATIC_PATH = "G:/Resource/";

    @GetMapping("/download")
    public ResponseEntity<Map<String, Object>> downloadResource(
            @NotNull @RequestParam Long resourceId)
    {
        return ResponseEntity.ok(resourceService.downloadResource(resourceId));
    }

    @GetMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteResource(
            @NotNull @RequestParam Long resourceId)
    {
        return ResponseEntity.ok(resourceService.deleteResource(resourceId));
    }


    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadResource(@RequestBody MultipartFile file,
                                                              @RequestParam Long teacherId,
                                                              @RequestParam Long courseId,
                                                              @RequestParam String type

    )
    {
        if(file.isEmpty())
        {
            return ResponseEntity.status(400).body(null);
        }

        Date date =new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String sj=dateFormat.format(date);
        //原文件名
        String fileName = file.getOriginalFilename();
        //新名字（重复文件名问题）格式为：时间+原名文件名称
        String newname=sj+fileName;
        // 文件上传路径
        String filePath = STATIC_PATH +newname;
        //创建文件夹
        File dest = new File(filePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //文件保存到本地
            //file.transferTo(dest);
            filePath=ossService.uploadFile(file);
            Resource resource=new Resource(get10UniqueId(),teacherId,courseId,type,file.getSize(),fileName,filePath);
            return ResponseEntity.ok(resourceService.addResource(resource));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<Map<String, Object>> checkResource(@RequestParam Long courseId) {
        try {
            return ResponseEntity.ok(resourceService.checkResource(courseId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
}
