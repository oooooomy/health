package com.health.api.service.impl;

import com.health.api.service.HealthFormService;
import com.health.api.repository.HealthFormRepository;
import com.health.api.model.entity.HealthForm;
import com.health.api.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HealthFormServiceImpl implements HealthFormService {

    @Resource
    private HealthFormRepository healthFormRepository;

    @Override
    public HealthForm save(HealthForm healthForm) throws Exception {
        if (findByUidAndCreateAt(healthForm.getUid(), DataTimeUtil.getTodayString()) != null) {
            throw new Exception("今日已上传");
        }
        return healthFormRepository.save(healthForm);
    }

    @Override
    public List<HealthForm> listAll() {
        return healthFormRepository.findAll();
    }

    @Override
    public List<HealthForm> listAllByUser(String uid) {
        return healthFormRepository.findAllByUid(uid);
    }

    @Override
    public HealthForm findByUidAndCreateAt(String uid, String createAt) {
        return healthFormRepository.findByUidAndCreateAt(uid, createAt);
    }

}
