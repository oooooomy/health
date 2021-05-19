package com.health.api.service;

import com.health.api.model.entity.User;

public interface UserService {

    User sign(String code) throws Exception;

    User update(User user);

    User findById(String id);

}
