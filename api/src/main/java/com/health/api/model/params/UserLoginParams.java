package com.health.api.model.params;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginParams {

    private String openId;

    private String sessionKey;

}
