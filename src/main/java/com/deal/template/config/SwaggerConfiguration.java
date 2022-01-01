package com.deal.template.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket testApi() {
        return createDocket("测试接口", "test");
    }
    @Bean
    public Docket userApi() {
        return createDocket("用户接口", "user");
    }

    public Docket createDocket(String groupName,String path) {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class).groupName(groupName)
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                .globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .globalOperationParameters(commonHeaderParameters()).forCodeGeneration(true)
                .pathMapping("/").select().paths(Predicates.or(PathSelectors.regex("/" + path + "/.*")))
                .build()
                .apiInfo(new ApiInfoBuilder().title(groupName).version("1.0").contact(new Contact("deal","",""))
                        .license("Ougaho").build());
    }


    /**
     * APP请求基本参数配置
     * @return
     */
    private List<Parameter> commonHeaderParameters()
    {
        List<Parameter> pars = Lists.newArrayList();
        pars.add(new ParameterBuilder().name("id").modelRef(new ModelRef("string"))
                .description("基本id").defaultValue("0123456789ABC").parameterType("header").required(true).build());
        return pars;
    }

    /**
     * 接口请求状态码说明
     * @return
     */
    private List<ResponseMessage> customerResponseMessage()
    {
        List<ResponseMessage> list = Lists.newArrayList();
        list.add(new ResponseMessageBuilder().code(200).message("请求成功").build());
        list.add(new ResponseMessageBuilder().code(204).message("服务器成功处理了请求，但不需要返回任何实体内容").build());
        list.add(new ResponseMessageBuilder().code(400).message("请求失败,具体查看返回业务状态码与对应消息").build());
        list.add(new ResponseMessageBuilder().code(401).message("请求失败,未经过身份认证").build());
        list.add(new ResponseMessageBuilder().code(405).message("请求方法不支持").build());
        list.add(new ResponseMessageBuilder().code(415).message("请求媒体类型不支持").build());
        list.add(new ResponseMessageBuilder().code(500).message("服务器遇到了一个未曾预料的状况,导致了它无法完成对请求的处理").build());
        list.add(new ResponseMessageBuilder().code(503).message("服务器当前无法处理请求,这个状况是临时的，并且将在一段时间以后恢复").build());
        return list;
    }
}
