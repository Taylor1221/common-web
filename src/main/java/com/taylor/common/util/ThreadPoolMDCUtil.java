package com.taylor.common.util;

import cn.hutool.core.text.CharSequenceUtil;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 线程池trace-id
 *
 * @author loveCamille
 * @date 2025-04-02 21:37:17
 */
public class ThreadPoolMDCUtil {

    private static void setTraceIdIfAbsent() {
        if (CharSequenceUtil.isEmpty(MDCUtil.get())) {
            MDCUtil.set(MDCUtil.generateTraceId());
        }
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDCUtil.clear();
            } else {
                MDCUtil.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDCUtil.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDCUtil.clear();
            } else {
                MDCUtil.setContextMap(context);
            }
            //设置traceId
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDCUtil.clear();
            }
        };
    }
}
