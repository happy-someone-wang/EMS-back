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

    @GetMapping("/check")
    public ResponseEntity<Map<String, Object>> CheckUsers(@RequestParam Long id,@RequestParam String type) {
        User user=new User();
        if(id==0) {
            user.setType("all");
        }
        else {
            user.setType(type);
            user.setId(id);
        }
        try {
            return ResponseEntity.ok(adminService.checkUsers(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
    @GetMapping("/checknotice")
    public ResponseEntity<Map<String, Object>> CheckNotice() {
        try {
            return ResponseEntity.ok(adminService.checkNotice());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody User user) {
        if(user.getId()==null)
        {
            return ResponseEntity.status(400).body(null);
        }
        try {
            return ResponseEntity.ok(adminService.updateUser(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
}
