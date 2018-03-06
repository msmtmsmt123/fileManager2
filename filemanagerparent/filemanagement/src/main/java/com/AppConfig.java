package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = "com")
@ImportResource("classpath:spring.xml")

public class AppConfig {
    @Bean
    Point point(){
        Point point=new Point();
        point.setPointX(101);
        point.setPointY(200);
        return point;
    }
    @Bean
    LoginServlet loginServlet(){
        return new LoginServlet();
    }

}
