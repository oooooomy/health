package com.health.api.service;

import com.health.api.model.entity.HealthForm;

import java.util.List;

public interface HealthFormService {

    HealthForm save(HealthForm healthForm) throws Exception;

    List<HealthForm> listAll();

    List<HealthForm> listAllByUser(String uid);

    HealthForm findByUidAndCreateAt(String uid, String createAt);

}
