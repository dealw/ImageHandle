package com.deal.template.controller;

import com.deal.template.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户类")
@RequestMapping("/user")
public class UserController {
    @Resource
    private TestService testService;

    @GetMapping("/exception")
    @ApiOperation("测试全局异常")
    public void textException(){
        int i = 1 / 0;
    }

    @GetMapping("/testAsync")
    @ApiOperation("测试异步执行")
    public void testAsync() throws InterruptedException {
        System.out.println("当前线程:"+Thread.currentThread().getName());
        testService.testAsync();
        for (int i = 10; i < 20; i++) {
            System.out.print(i);
        }
        System.out.println();
    }

}
