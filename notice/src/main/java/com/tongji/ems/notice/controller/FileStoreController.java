package com.tongji.ems.notice.controller;

import com.tongji.ems.notice.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileStoreController {
    @Autowired
    private OssService ossService;

    @PostMapping()
    public ResponseEntity<String> uploadFile(
            @RequestPart("file") MultipartFile file
    ) {
        try {
            return ResponseEntity.ok(ossService.uploadFile(file));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteFile(
            @RequestParam(value = "fileUrl") String fileUrl
    ) {
        try {
            return ResponseEntity.ok(ossService.deleteFile(fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
}
