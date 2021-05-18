package com.health.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 上传二进制图片
 */
@Data
@Table(name = "t_image")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //文件内容
    @Column(columnDefinition = "longblob")
    private byte[] bytes;

    //文件md5值
    private String md5;

    public Image(byte[] bytes, String md5) {
        this.bytes = bytes;
        this.md5 = md5;
    }

}
