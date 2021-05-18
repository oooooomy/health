package com.health.api.controller;

import com.health.api.model.entity.User;
import com.health.api.model.support.ResponseResult;
import com.health.api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/sign")
    public ResponseResult<User> signInOrUp(String code) throws Exception {
        return new ResponseResult<>(userService.sign(code));
    }

}
