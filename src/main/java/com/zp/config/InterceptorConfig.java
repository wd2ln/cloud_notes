package com.zp.config;

import com.zp.interceptor.MyInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor myInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
        // 指定不拦截
         registry.addInterceptor(myInterceptor)
                 .addPathPatterns("/**")
                 .excludePathPatterns("/login.jsp","/user/login","/statics/**");
    }
}
