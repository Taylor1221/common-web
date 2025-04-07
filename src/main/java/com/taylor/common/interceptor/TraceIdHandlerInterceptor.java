package com.taylor.common.interceptor;

import cn.hutool.core.text.CharSequenceUtil;
import com.taylor.common.util.MDCUtil;
import com.taylor.common.constant.WebConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * trace-id 拦截器
 *
 * @author loveCamille
 * @date 2025-04-02 21:29:55
 */
@Slf4j
public class TraceIdHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(WebConstant.TRACE_ID);
        if (CharSequenceUtil.isEmpty(traceId)) {
            traceId = MDCUtil.generateTraceId();
        }
        MDCUtil.set(traceId);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        MDCUtil.remove();
    }
}
