package com.hassnain.userservice.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanProvider implements ApplicationContextAware {


    public static <T extends Object> T getBean(Class<T> beans){
        return SpringApplicationContext.getApplicationContext().getBean(beans);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationContext.setApplicationContext(applicationContext);
    }
}
