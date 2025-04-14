package com.taylor.common.web;

import com.taylor.common.web.filter.TraceIdFilter;
import com.taylor.common.web.support.RestExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Common模块自动配置
 *
 * @author loveCamille
 * @date 2025-04-02 21:59:47
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(DispatcherServlet.class)
public class CommonWebAutoConfiguration {

//    @Bean
//    public WebMvcAppConfigurer webMvcAppConfigurer() {
//        return new WebMvcAppConfigurer();
//    }

    @Bean
    public FilterRegistrationBean<TraceIdFilter> traceIdFilter() {
        FilterRegistrationBean<TraceIdFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TraceIdFilter());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE); // 确保在 Spring Security Filter 之前
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public RestExceptionHandler restExceptionHandler() {
        return new RestExceptionHandler();
    }

}
