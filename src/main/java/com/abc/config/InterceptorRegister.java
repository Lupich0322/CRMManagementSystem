package com.abc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器注册
 */
@Configuration
public class InterceptorRegister implements WebMvcConfigurer {

    /**
     * 把我们定义的拦截器类注册为Bean
     */
    @Bean
    public HandlerInterceptor getInterceptor() {
        return new EmployeeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathPatterns = new ArrayList<>();
        pathPatterns.add("/**");

        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/login");
        excludePathPatterns.add("/register");

        registry.addInterceptor(getInterceptor()).addPathPatterns(pathPatterns).excludePathPatterns(excludePathPatterns);
    }


}