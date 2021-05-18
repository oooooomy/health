package com.health.api.model.entity;

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

    private String idCard;

    private Integer age;

    private String name;

    private String gender;

    private String mobile;

    private String address;

    private String healthCode;

    private Integer statusCode;

    private long createAt;

    public User(String id) {
        this.id = id;
        this.createAt = System.currentTimeMillis();
    }

}
