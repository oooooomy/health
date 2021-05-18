package com.health.api.service.impl;

import com.health.api.model.entity.Image;
import com.health.api.repository.ImageRepository;
import com.health.api.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageRepository imageRepository;

    @Override
    public String save(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        byte[] data = new byte[(int) file.getSize()];
        int i = is.read(data);
        System.out.println("上传的文件大小=" + i);
        //计算文件的md5值
        String md5 = DigestUtils.md5DigestAsHex(data);
        //判断是否有相同md5值的文件
        Image one = imageRepository.getImageByMd5(md5);
        //如果存在则直接返回已存在的文件id
        if (one != null) return one.getId();
        //不存在则存入数据库
        return imageRepository.save(new Image(data, md5)).getId();
    }

    @Override
    public Image getById(String id) {
        return imageRepository.getImageById(id);
    }


}
