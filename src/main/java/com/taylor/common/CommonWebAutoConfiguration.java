package com.taylor.common;

import com.taylor.common.support.RestExceptionHandler;
import com.taylor.common.support.WebMvcAppConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Bean
    public WebMvcAppConfigurer webMvcAppConfigurer() {
        return new WebMvcAppConfigurer();
    }

    @Bean
    public RestExceptionHandler restExceptionHandler() {
        return new RestExceptionHandler();
    }

}
