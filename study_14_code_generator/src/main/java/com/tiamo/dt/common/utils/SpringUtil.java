package com.tiamo.dt.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
	}

	/**
	 * 获取applicationContext
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 通过name获取 Bean.
	 * @param name name
	 * @return Bean对象
	 */
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}

	/**
	 * 通过class获取Bean
	 * @param clazz class
	 * @param <T>
	 * @return Bean对象
	 */
	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * 通过name,以及Clazz返回指定的Bean
	 * @param name name
	 * @param clazz class
	 * @param <T>
	 * @return Bean对象
	 */
	public static <T> T getBean(String name,Class<T> clazz){
		return getApplicationContext().getBean(name, clazz);
	}

}
