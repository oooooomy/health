package com.health.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.health.api.model.entity.User;
import com.health.api.model.params.UserLoginParams;
import com.health.api.repository.UserRepository;
import com.health.api.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User sign(String code) throws Exception {
        //微信获取openId url
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + "wx18783a513dd8b26f" + "&secret="
                + "a45f500599adaeb41de4ee22ace71084" + "&js_code="
                + code + "&grant_type=authorization_code";
        //新建 RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //请求失败
        if (entity.getStatusCodeValue() != 200) throw new Exception("request error");
        //解析params
        UserLoginParams params = JSON.parseObject(entity.getBody(), UserLoginParams.class);
        //解析失败 params为空
        if (params == null) throw new Exception("request params missing");
        //查询是否存在当前id的用户
        Optional<User> userOptional = userRepository.findById(params.getOpenId());
        //如果用户为空 插入此id的用户
        if (userOptional.isEmpty()) {
            User user = new User(params.getOpenId());
            userRepository.save(user);
            return user;
        }
        return userOptional.get();
    }

}
