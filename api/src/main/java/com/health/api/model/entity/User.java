package com.health.api.model.entity;

import com.health.api.utils.DataTimeUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {

    @Id
    private String id;

    //真实姓名
    private String name;

    //性别
    private String gender;

    //联系方式
    private String mobile;

    //所在地区
    private String address;

    //头像
    private String avatar;

    //身份证号
    private String number;

    //身份证正面照
    private String cardImageId;

    //返乡时间
    private String returnAt;

    //注册时间
    @Column(updatable = false)
    private String createAt;

    //更新时间
    private String updateAt;

    public User(String id) {
        this.id = id;
        this.createAt = DataTimeUtil.getNowTimeString();
    }

}
