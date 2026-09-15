package com.tiamo.dt.db.config;

import com.alibaba.druid.support.jakarta.StatViewServlet;
import com.alibaba.druid.support.jakarta.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.addUrlMappings(new String[] { "/druid/*" });
        registrationBean.addInitParameter("loginUsername", "admin");
        registrationBean.addInitParameter("loginPassword", "123456");
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean();
        WebStatFilter filter = new WebStatFilter();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(new String[] { "/*" });
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        registrationBean.addInitParameter("sessionStatEnable", "false");
        registrationBean.addInitParameter("sessionStatMaxCount", "1000");
        registrationBean.addInitParameter("principalSessionName", "");
        registrationBean.addInitParameter("principalCookieName", "");
        registrationBean.addInitParameter("profileEnable", "true");
        return registrationBean;
    }

}
