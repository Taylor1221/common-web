package com.taylor.common.util;

import cn.hutool.core.util.IdUtil;
import com.taylor.common.constant.WebConstant;
import org.slf4j.MDC;

import java.util.Map;

/**
 * MDC工具类
 *
 * @author loveCamille
 * @date 2025-04-02 21:33:29
 */
@SuppressWarnings("unused")
public class MDCUtil {

    private MDCUtil() {}

    public static String generateTraceId() {
        return IdUtil.fastSimpleUUID();
    }

    public static void set(String traceId) {
        MDC.put(WebConstant.TRACE_ID, traceId);
    }

    public static void setContextMap(Map<String, String> contextMap) {
        MDC.setContextMap(contextMap);
    }

    public static String get() {
        return MDC.get(WebConstant.TRACE_ID);
    }

    public static void remove() {
        MDC.remove(WebConstant.TRACE_ID);
    }

    public static void clear() {
        MDC.clear();
    }

}
