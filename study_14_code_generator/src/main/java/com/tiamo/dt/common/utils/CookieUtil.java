package com.tiamo.dt.common.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;



/**
 * cookie工具类
 */
@UtilityClass
public class CookieUtil {

    /**
     * 读取cookie
     *
     * @param name cookie 键
     * @return cookie 值
     */
    public String getCookieValue(String name) {
        HttpServletRequest request = WebUtil.getRequest();
        Assert.notNull(request, "request from RequestContextHolder is null");
        return getCookieValue(request, name);
    }

    /**
     * 读取cookie
     *
     * @param request HttpServletRequest
     * @param name    cookie 键
     * @return cookie 值
     */
    public String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * 清除 指定的cookie
     *
     * @param response HttpServletResponse
     * @param key      cookie 键
     */
    public void removeCookie(HttpServletResponse response, String key) {
        setCookie(response, key, null, 0);
    }

    /**
     * 设置cookie
     *
     * @param response        HttpServletResponse
     * @param name            cookie 键
     * @param value           cookie 值
     * @param maxAgeInSeconds 生命周期 秒
     */
    public void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public static void main(String[] args) {
        HttpServletResponse response = WebUtil.getResponse();
        CookieUtil.setCookie(response, "testKey", "123", 30);
        CookieUtil.removeCookie(response, "testKey");
    }
}
