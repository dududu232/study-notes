package com.tiamo.code.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tiamo.dt.dev.db.pojo.page.PageParam;
import com.tiamo.code.entity.MissionRiskRule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  服务类
 *
 * @author ZJJ
 * @since 2026-03-10
 */
public interface IMissionRiskRuleService extends IService<MissionRiskRule> {

    /**
     * 通过实体类获取QueryWrapper
     * @param pageParam 分页参数
     * @param missionRiskRule 实体信息
     * @return
     */
    QueryWrapper<MissionRiskRule> getQueryWrapper(PageParam pageParam, MissionRiskRule missionRiskRule);

}
