package com.health.api.model.params;

import lombok.Data;

@Data
public class AdminLoginParams {

    private String email;

    private String password;

    private String code;

    private boolean remember;

}
