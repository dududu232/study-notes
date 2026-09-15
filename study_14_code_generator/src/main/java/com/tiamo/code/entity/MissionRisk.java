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
 * 风险表
 *
 * @author ZJJ
 * @since 2026-03-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MissionRisk对象", description="风险表")
public class MissionRisk implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", position = 0)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "固有风险编号", position = 1)
    private String riskNum;

    @ApiModelProperty(value = "风险描述", position = 2)
    private String riskDesc;

    @ApiModelProperty(value = "风险来源", position = 3)
    private String riskSource;

    @ApiModelProperty(value = "对应的二级风险领域id", position = 4)
    private Long secondRiskAreaId;

}
