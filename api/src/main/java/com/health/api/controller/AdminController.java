package com.health.api.controller;

import com.health.api.model.params.AdminLoginParams;
import com.health.api.model.entity.Admin;
import com.health.api.model.support.ResponseResult;
import com.health.api.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping("")
    public ResponseResult<List<Admin>> findAll() {
        return new ResponseResult<>(adminService.findAll());
    }

    @DeleteMapping("")
    public ResponseResult<?> delete(String id) {
        adminService.delete(id);
        return new ResponseResult<>();
    }

    @PostMapping("")
    public ResponseResult<Admin> save(@RequestBody Admin admin) throws Exception {
        return new ResponseResult<>(adminService.save(admin));
    }

    @PostMapping("/login")
    public ResponseResult<Admin> loginByEmail(String type, @RequestBody AdminLoginParams params) throws Exception {
        Admin admin = type.equals("email") ? adminService.loginByEmail(params) : adminService.loginByPassword(params);
        return new ResponseResult<>(admin);
    }

    @GetMapping("/sendEmail")
    public ResponseResult<?> sendEmail(String email) throws Exception {
        adminService.sendEmail(email);
        return new ResponseResult<>();
    }

}
