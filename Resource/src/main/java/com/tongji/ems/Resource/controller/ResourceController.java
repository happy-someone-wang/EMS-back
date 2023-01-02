package com.tongji.ems.Resource.controller;

import com.tongji.ems.Resource.model.Resource;
import com.tongji.ems.Resource.service.ResourceService;
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

@CrossOrigin
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    public static String STATIC_PATH = "G:/Resource";

    @GetMapping("/download")
    public String uploadResource(
            HttpServletRequest request,
            HttpServletResponse response,
            @NotNull @RequestParam Long resourceId)
    {
        Resource resource=resourceService.downloadResource(resourceId);
        File file = new File(resource.getFilePath(), resource.getFileName());
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            try {
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" +  URLEncoder.encode(resource.getFileName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                //实现文件下载
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return "完成";
        }
        return "失败";
    }


    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadResource(@NotNull @RequestBody MultipartFile file,
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
            file.transferTo(dest);
            Resource resource=new Resource(get10UniqueId(),teacherId,courseId,type,file.getSize(),fileName,filePath);
            return ResponseEntity.ok(resourceService.addResource(resource));
        } catch (IllegalStateException | IOException e) {
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
