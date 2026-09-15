package com.tiamo.dt.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * web工具类
 */
@UtilityClass
public class WebUtil {

    private final String UNKNOWN = "unknown";

    /**
     * 获取 HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public HttpServletRequest getRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        } catch (IllegalStateException e) {
            return null;
        }
    }

    /**
     * 获取 HttpServletResponse
     *
     * @return HttpServletResponse
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

//    /**
//     * 获取ip
//     * @return
//     */
//    public String getIP() {
//        return getIP(getRequest());
//    }
//
//    /**
//     * 获取ip
//     * @param request
//     * @return
//     */
//    public String getIP(HttpServletRequest request) {
//        Assert.notNull(request, "HttpServletRequest is null");
//        String ip = request.getHeader("X-Requested-For");
//        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader("X-Forwarded-For");
//        }
//        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return StrUtil.isBlank(ip) ? null : ip.split(",")[0];
//    }
}
