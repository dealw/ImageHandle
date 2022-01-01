package com.deal.template.config;

import cn.hutool.core.util.StrUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobeExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handlerException(Exception e) {
        System.out.println(StrUtil.format("全局异常处理器捕获了一个异常:{}", e.getMessage()));
    }
}
