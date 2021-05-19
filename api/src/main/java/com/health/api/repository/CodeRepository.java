package com.health.api.repository;

import com.health.api.model.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, String> {

    Code findByEmailAndValue(String email, String value);

}
