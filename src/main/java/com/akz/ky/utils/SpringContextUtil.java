package com.akz.ky.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author lzx
 * @version 1.0
 * @date 2020.4.2 20:47
 * @Description
 */
public class SpringContextUtil  implements ApplicationContextAware {
    private SpringContextUtil() {

    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
