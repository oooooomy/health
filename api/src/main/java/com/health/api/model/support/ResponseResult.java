package com.health.api.model.support;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private boolean status;

    private String msg;

    private T data;

    /**
     * 默认返回
     */
    public ResponseResult() {
        this.status = true;
        this.msg = "request ok";
        this.data = null;
    }

    /**
     * 不需要返回数据
     *
     * @param data 返回数据
     */
    public ResponseResult(T data) {
        this.status = true;
        this.msg = "request ok";
        this.data = data;
    }

    /**
     * 失败返回数据
     *
     * @param msg 返回的错误消息
     */
    public ResponseResult(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

}
