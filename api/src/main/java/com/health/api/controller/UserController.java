package com.health.api.controller;

import com.health.api.model.entity.User;
import com.health.api.model.support.ResponseResult;
import com.health.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录或者注册
     *
     * @param code 小程序code
     * @return user
     * @throws Exception .
     */
    @GetMapping("/sign")
    public ResponseResult<User> signInOrUp(String code) throws Exception {
        return new ResponseResult<>(userService.sign(code));
    }

    @GetMapping("/info/{id}")
    public ResponseResult<User> getUserInfo(@PathVariable String id) {
        return new ResponseResult<>(userService.findById(id));
    }

    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return user
     */
    @PutMapping("")
    public ResponseResult<User> update(@RequestBody User user) {
        return new ResponseResult<>(userService.update(user));
    }

}
