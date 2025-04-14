package com.taylor.common.web.filter;

import cn.hutool.core.text.CharSequenceUtil;
import com.taylor.common.web.constant.WebConstant;
import com.taylor.common.web.util.MDCUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * trace-id过滤器
 *
 * @author loveCamille
 * @date 2025-04-14 10:37:19
 */
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String traceId = httpRequest.getHeader(WebConstant.TRACE_ID);

        if (CharSequenceUtil.isEmpty(traceId)) {
            traceId = MDCUtil.generateTraceId();
        }

        MDCUtil.set(traceId);
        try {
            chain.doFilter(request, response);
        } finally {
            MDCUtil.remove();
        }
    }


}
