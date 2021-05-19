package com.health.api.repository;

import com.health.api.model.entity.HealthForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthFormRepository extends JpaRepository<HealthForm, String> {

    List<HealthForm> findAllByUid(String uid);

    HealthForm findByUidAndCreateAt(String uid, String createAt);

}

