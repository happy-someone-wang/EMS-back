package com.tongji.ems.personalinfo.controller;

import com.tongji.ems.personalinfo.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
