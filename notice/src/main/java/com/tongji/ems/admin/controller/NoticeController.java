package com.tongji.ems.admin.controller;

import com.tongji.ems.admin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getNoticeList(
            @RequestParam(value = "courseId", defaultValue = "-1") String courseId,
            @RequestParam(value = "offset", defaultValue = "1") String offset,
            @RequestParam(value = "pageSize", defaultValue = "10") String pageSize
    ) {
        if (courseId.equals("-1")) {
            return ResponseEntity.status(400).body(null);
        }
        try {
            Integer course_id = Integer.valueOf(courseId);
            Integer off_set = Integer.valueOf(offset);
            Integer page_size = Integer.valueOf(pageSize);
            return ResponseEntity.ok(noticeService.getNoticeListByPage(course_id, off_set, page_size));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
}
