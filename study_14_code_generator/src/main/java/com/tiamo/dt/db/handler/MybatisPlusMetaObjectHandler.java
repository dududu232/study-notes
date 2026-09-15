package com.tiamo.dt.db.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tiamo.dt.common.utils.JwtUtil;
import com.tiamo.dt.common.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * MybatisPlus 实体字段自动填充
 */
@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

	/**
	 * 新增时自动填充相关字段
	 * @param metaObject
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		Long userId = getUserId();
		LocalDateTime now = LocalDateTime.now();
		fillValIfNullByName("createUserId", userId, metaObject, true);
		fillValIfNullByName("createTime", now, metaObject, true);
		fillValIfNullByName("updateUserId", userId, metaObject, true);
		fillValIfNullByName("updateTime", now, metaObject, true);
	}

	/**
	 * 修改时自动填充相关字段
	 * @param metaObject
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		Long userId = getUserId();
		LocalDateTime now = LocalDateTime.now();
		fillValIfNullByName("updateUserId", userId, metaObject, true);
		fillValIfNullByName("updateTime", now, metaObject, true);
	}

	/**
	 * 填充值，先判断是否有手动设置，优先手动设置的值，例如：job必须手动设置
	 * @param fieldName 属性名
	 * @param fieldVal 属性值
	 * @param metaObject MetaObject
	 * @param isCover 是否覆盖原有值,避免更新操作手动入参
	 */
	private static void fillValIfNullByName(String fieldName, Object fieldVal, MetaObject metaObject, boolean isCover) {
		// 0. 如果填充值为空
		if (fieldVal == null) {
			return;
		}

		// 1. 没有 set 方法
		if (!metaObject.hasSetter(fieldName)) {
			return;
		}
		// 2. 如果用户有手动设置的值
		Object userSetValue = metaObject.getValue(fieldName);
		String setValueStr = StrUtil.str(userSetValue, Charset.defaultCharset());
		if (StrUtil.isNotBlank(setValueStr) && !isCover) {
			return;
		}
		// 3. field 类型相同时设置
		Class<?> getterType = metaObject.getGetterType(fieldName);
		if (ClassUtils.isAssignableValue(getterType, fieldVal)) {
			metaObject.setValue(fieldName, fieldVal);
		}
	}

	/**
	 * 获取当前登录用户ID
	 * @return
	 */
	private Long getUserId() {
		//获取request
		HttpServletRequest request = WebUtil.getRequest();
		if (request == null){
			return  null;
		}

		//获取token
		String token = WebUtil.getRequest().getHeader(JwtUtil.AUTH_TOKEN_KEY);
		if (StringUtils.isEmpty(token)) {
			return null;
		}

		//解码token
		String userInfoJson = JwtUtil.getUserName(token);
		if (StringUtils.isEmpty(userInfoJson)) {
			return null;
		}

		JSONObject userObject = JSON.parseObject(userInfoJson);
		return userObject == null ? null : userObject.getLong("userId");
	}

}
