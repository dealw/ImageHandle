package com.deal.template.service;

import org.springframework.scheduling.annotation.Async;


public interface TestService {

    /**
     * 测试异步
     * @Date 2021/12/29 22:04
     * @Author deal
     * @Param []
     * @return void
     */
    void testAsync() throws InterruptedException;
}
