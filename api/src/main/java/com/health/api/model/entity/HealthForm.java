package com.health.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 健康表
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_health_form")
public class HealthForm {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //上传用户的id
    private String uid;

    //是否发烧
    private boolean fever;

    //是否咳嗽
    private boolean cough;

    //是否乏力
    private boolean weak;

    //接触患者
    private boolean contact;

    //是否去过重灾区
    private boolean area;

    //是否曾被隔离
    private boolean quarantine;

    //定位信息
    private String location;

    //上报时间
    private String createAt;

}
