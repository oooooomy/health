package com.health.api.service;

import com.health.api.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User sign(String code) throws Exception;

    User update(User user);

    User findById(String id);

}
