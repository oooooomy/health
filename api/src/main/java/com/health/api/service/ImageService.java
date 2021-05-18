package com.health.api.service;

import com.health.api.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String save(MultipartFile file) throws IOException;

    Image getById(String id);

}
