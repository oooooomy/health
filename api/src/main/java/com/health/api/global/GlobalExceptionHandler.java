package com.health.api.global;

import com.health.api.model.support.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 捕获controller异常
 * controller抛出异常执行下边的函数
 * 返回Servlet Response写入ResponseResult<T>
 */
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e) {
        logger.warn(e.getMessage());
        return new ResponseResult<>(false, e.getMessage());
    }

}
