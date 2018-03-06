package com;

import com.AppConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


//@Component
public class Main {

    public static AnnotationConfigWebApplicationContext context;

    public static void initContext(){
        context  = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
    }
}
