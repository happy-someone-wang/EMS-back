package com.tongji.ems.personalinfo.controller;

import com.tongji.ems.personalinfo.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonalInfoController {
    @Autowired
    private PersonalInfoService personalInfoService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getPersonInfo(
            @RequestParam(value = "id", defaultValue = "-1") String id,
            @RequestParam(value = "role", defaultValue = "null") String role) {
        if (id.equals("-1") && role.equals("null")) {
            return ResponseEntity.status(400).body(null);
        }
        try {
            Integer Id = Integer.valueOf(id);
            return ResponseEntity.ok(personalInfoService.getPersonalInfo(Id, role));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> postPersonalInfo(
            @RequestBody Object person,
            @RequestParam("role") String role
    ) {
        try {
            return ResponseEntity.ok(personalInfoService.postPersonalInfo(person, role));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping()
    public ResponseEntity<Map<String, Object>> updatePersonalInfo(
            @RequestBody Object person,
            @RequestParam("role") String role
    ) {
        try {
            return ResponseEntity.ok(personalInfoService.updatePersonalInfo(person, role));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/avatar")
    public ResponseEntity<String> updatePersonalAvatar(
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("id") String id,
            @RequestParam("role") String role
    ) {
        try {
            System.out.println("接收到数据" + avatar.getName());
            System.out.println("接收到数据" + id + role);
            Integer Id = Integer.valueOf(id);
            return ResponseEntity.ok(personalInfoService.changeAvatar(avatar, Id, role));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
}
