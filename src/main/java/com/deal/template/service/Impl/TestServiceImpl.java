package com.deal.template.service.Impl;

import com.deal.template.service.TestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author Deal
 * Date 2021/12/29 22:04
 **/
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestServiceImpl1 testServiceImpl1;

    /**
     * 测试异步
     * @Date 2021/12/29 22:05
     * @Author Deal
     * @Param []
     * @return void
     */
    @Override
    @Async
    public void testAsync() throws InterruptedException {
        System.out.println("当前线程:"+Thread.currentThread().getName());
        testServiceImpl1.testAsync1();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
        }
        System.out.println();
    }

    @Async
    public void testAsync1() {
        System.out.println("当前线程:"+Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.print("a");
        }
        System.out.println();
    }
}
