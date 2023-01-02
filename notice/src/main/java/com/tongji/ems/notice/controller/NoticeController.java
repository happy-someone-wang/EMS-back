package com.tongji.ems.notice.controller;

import com.tongji.ems.notice.dto.ImageUploadDto;
import com.tongji.ems.notice.model.CourseNotice;
import com.tongji.ems.notice.model.SystemNotice;
import com.tongji.ems.notice.service.NoticeService;
import com.tongji.ems.notice.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private OssService ossService;

    @PostMapping("/image")
    public ResponseEntity<Map<String,Object>> uploadFile(
            @RequestBody ImageUploadDto image
            ) {
        try {
            return ResponseEntity.ok(ossService.uploadImage(image.getFile(), image.getFileName()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("courseNoticeList")
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

    @GetMapping("courseNotice")
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

    @PostMapping("/courseNotice")
    public ResponseEntity<Map<String, Object>> postNotice(
            @RequestBody CourseNotice notice
    ) {
        try {
            return ResponseEntity.ok(noticeService.postNoticeByTeacher(notice));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/systemNoticeList")
    public ResponseEntity<Map<String, Object>> getSystemNoticeList(
            @RequestParam(value = "offset", defaultValue = "1") String offset,
            @RequestParam(value = "pageSize", defaultValue = "10") String pageSize
    ) {
        try {
            Integer os = Integer.valueOf(offset);
            Integer ps = Integer.valueOf(pageSize);
            return ResponseEntity.ok(noticeService.getSystemNoticeList(os, ps));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/systemNotice")
    public ResponseEntity<Map<String, Object>> getSystemNotice(
            @RequestParam(value = "noticeId") String Id
    ) {
        try {
            Long noticeId = Long.valueOf(Id);
            return ResponseEntity.ok(noticeService.getSystemNotice(noticeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/systemNotice")
    public ResponseEntity<Map<String, Object>> publishSystemNotice(
            @RequestBody SystemNotice systemNotice
    ) {
        try {
            return ResponseEntity.ok(noticeService.postNoticeByAdmin(systemNotice));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/courseNotice/utop")
    public ResponseEntity<Boolean> courseNoticeCancelTop(
            @RequestParam(value = "noticeId") String Id
    ) {
        try {
            Long noticeId = Long.valueOf(Id);
            return ResponseEntity.ok(noticeService.cancelCourseTop(noticeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/systemNotice/utop")
    public ResponseEntity<Boolean> systemNoticeCancelTop(
            @RequestParam(value = "noticeId") String Id
    ) {
        try {
            Long noticeId = Long.valueOf(Id);
            return ResponseEntity.ok(noticeService.cancelSystemTop(noticeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/courseNotice")
    public ResponseEntity<Boolean> deleteCourseNotice(
            @RequestParam(value = "noticeId") String Id
    ) {
        try {
            Long noticeId = Long.valueOf(Id);
            return ResponseEntity.ok(noticeService.deleteCourseNotice(noticeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/systemNotice")
    public ResponseEntity<Boolean> deleteSystemNotice(
            @RequestParam("noticeId") String Id
    ) {
        try {
            Long noticeId = Long.valueOf(Id);
            return ResponseEntity.ok(noticeService.deleteSystemNotice(noticeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
}
