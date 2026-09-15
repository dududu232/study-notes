package com.tiamo.dt.db.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据库实体基类，包含通用实体字段
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

	@Schema(description  = "创建人")
	@TableField(fill = FieldFill.INSERT)
	private Long createUserId;

	@Schema(description  = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	@Schema(description  = "更新人")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updateUserId;

	@Schema(description  = "更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}
