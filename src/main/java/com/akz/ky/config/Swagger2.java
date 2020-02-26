package com.akz.ky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/9/27 15:20
 * @Description Swagger2配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * 初始化创建Swagger Api
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                // 详细信息定制
                .apiInfo(apiInfo())
                .select()
                // 指定当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.akz.ky.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo(){
        Contact contact = new Contact("lzx", "www.zhangweishihundan.com", "972619248@qq.com");
        return new ApiInfoBuilder()
                .title("考研交流平台系统后台服务接口测试界面")
                .description("后台接口测试")
                .contact(contact)   // 联系方式
                .version("1.1.2")  // 版本
                .build();
    }
}
