package com.tongji.ems.admin.controller;

import com.tongji.ems.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> PostUsers(@RequestBody List<User> Users) {
        if (Users.isEmpty()) {
            return ResponseEntity.status(400).body(null);
        }
        try {
            return ResponseEntity.ok(adminService.addUsers(Users));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
}
