package com.hassnain.userservice.config;

import org.springframework.context.ApplicationContext;

public class SpringApplicationContext {

    private static ApplicationContext applicationContext;

    private SpringApplicationContext(){

    }

    public static void setApplicationContext(ApplicationContext applicationContext){
        SpringApplicationContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return SpringApplicationContext.applicationContext;
    }
}
