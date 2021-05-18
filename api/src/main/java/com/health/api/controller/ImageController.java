package com.health.api.controller;

import com.health.api.model.entity.Image;
import com.health.api.model.support.ResponseResult;
import com.health.api.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Resource
    private ImageService imageService;

    /**
     * 上传图片
     *
     * @param file 文件
     * @return 文件在数据库的id
     * @throws Exception 异常
     */
    @PostMapping("")
    public ResponseResult<String> upload(MultipartFile file) throws Exception {
        //form缺失file参数
        if (file == null) throw new Exception("请求参数缺失");
        //文件内容为空
        if (file.isEmpty()) throw new Exception("上传失败，请选择文件");
        //返回文件id
        return new ResponseResult<>(imageService.save(file));
    }

    @GetMapping("{id}")
    public void get(@PathVariable String id, HttpServletResponse response) throws IOException {
        Image image = imageService.getById(id);
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputStream = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(image.getBytes());
        int len;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.close();
    }

}
