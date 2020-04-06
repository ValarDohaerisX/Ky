package com.akz.ky;

import com.akz.ky.utils.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author lizhixin
 * @Date 2019.12.9
 * @Describetion 启动类
 *
 */

@SpringBootApplication
@EnableCaching //使用注解形式进行缓存操作
public class KyApplication {
    static {
        PortUtil.checkPort(6379,"Redis 服务端",true); //检查端口6379是否启动,如未启动，则退出SpringBoot
    }
    public static void main(String[] args) {
        SpringApplication.run(KyApplication.class, args);
    }

}
