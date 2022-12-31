package com.tongji.ems.notice.controller;

import com.tongji.ems.notice.model.CourseNotice;
import com.tongji.ems.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("courseNotices")
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

    @GetMapping("singleNotice")
    public ResponseEntity<Map<String, Object>> getNotice(
            @RequestParam(value = "noticeId", defaultValue = "-1") String Id
    ) {
        if (Id.equals("-1")) {
            return ResponseEntity.status(400).body(null);
        }
        try {
            Long noticeId = Long.valueOf(Id);
            return ResponseEntity.ok(noticeService.getNoticeById(noticeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<Boolean> postNotice(
            @RequestBody CourseNotice notice
    ) {
        try {
            return ResponseEntity.ok(noticeService.postNoticeByTeacher(notice));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
}
