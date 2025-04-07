package com.taylor.common.support;

import com.taylor.common.interceptor.TraceIdHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置器
 *
 * @author loveCamille
 * @date 2025-04-02 22:03:13
 */
public class WebMvcAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdHandlerInterceptor()).addPathPatterns("/**");
    }
}
