package com.example.springdemo1.config;

import com.example.springdemo1.filter.RequestResponseLogingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    public FilterRegistrationBean<RequestResponseLogingFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseLogingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLogingFilter());

        registrationBean.addUrlPatterns("/users/*");
        registrationBean.setOrder(1);

        return registrationBean;

    }
}
