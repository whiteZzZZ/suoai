package com.yiban.suoai.config;

import com.yiban.suoai.interceptor.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class MyFilter  {
    @Bean
    public FilterRegistrationBean sessionExpireFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(this.LoginFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public Filter LoginFilter() {
        return new LoginFilter();
    }
}