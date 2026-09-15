package com.tiamo.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 
 *
 * @author ZJJ
 * @since 2026-03-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MissionRiskRule对象", description="")
public class MissionRiskRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类型： 1制度层面 2执行层面", position = 1)
    private Integer type;

    @ApiModelProperty(value = "规则内容", position = 2)
    private String content;

    @ApiModelProperty(value = "风险id", position = 3)
    private Integer riskId;

}
