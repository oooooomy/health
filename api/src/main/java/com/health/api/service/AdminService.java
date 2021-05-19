package com.health.api.service;

import com.health.api.model.entity.Admin;
import com.health.api.model.params.AdminLoginParams;

import java.util.List;

public interface AdminService {

    Admin save(Admin admin) throws Exception;

    Admin findById(String id);

    void sendEmail(String email) throws Exception;

    Admin loginByPassword(AdminLoginParams params) throws Exception;

    Admin loginByEmail(AdminLoginParams params) throws Exception;

    List<Admin> findAll();

    void delete(String id);

}
