package com.tiamo.dt.common.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiamo.dt.common.jackson.JavaTimeModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Jackson配置类
 */
@AutoConfiguration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public Jackson2ObjectMapperBuilderCustomizer customizer() {
		return builder -> {
			// 设置地区为中国
			builder.locale(Locale.CHINA);
			// 设置系统默认时区
			builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
			// 设置默认日期时间格式
			builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
			// 注册自定义时间模块
			builder.modules(new JavaTimeModule());
		};
	}

}
