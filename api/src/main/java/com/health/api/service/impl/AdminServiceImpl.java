package com.health.api.service.impl;

import com.health.api.model.params.AdminLoginParams;
import com.health.api.model.entity.Admin;
import com.health.api.repository.AdminRepository;
import com.health.api.service.AdminService;
import com.health.api.service.EmailService;
import com.health.api.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminRepository adminRepository;

    @Resource
    private EmailService emailService;

    @Override
    public Admin save(Admin admin) throws Exception {
        if (admin.getEmail().length() < 8 || admin.getPassword().length() < 5) throw new Exception("请求参数异常");
        admin.setCreateAt(DataTimeUtil.getNowTimeString());
        return adminRepository.save(admin);
    }

    @Override
    public Admin findById(String id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public void sendEmail(String email) throws Exception {
        Admin admin = adminRepository.findAdminByEmail(email);
        if (admin == null) throw new Exception("不存在的邮箱账户");
        emailService.sendVerificationCode(email);
    }

    @Override
    public Admin loginByPassword(AdminLoginParams params) throws Exception {
        Admin one = adminRepository.findAdminByEmailAndPassword(params.getEmail(), params.getPassword());
        if (one == null) throw new Exception("用户名或密码错误");
        return one;
    }

    @Override
    public Admin loginByEmail(AdminLoginParams params) throws Exception {
        boolean status = emailService.checkVerificationCode(params.getEmail(), params.getCode());
        if (!status) throw new Exception("验证码错误");
        return adminRepository.findAdminByEmail(params.getEmail());
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public void delete(String id) {
        adminRepository.deleteById(id);
    }

}
