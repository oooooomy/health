package com.health.api.repository;

import com.health.api.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    Image getImageByMd5(String md5);

    Image getImageById(String id);

}
