package com.tiamo.code.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tiamo.dt.dev.db.pojo.page.PageParam;
import com.tiamo.code.entity.MissionRisk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 风险表 服务类
 *
 * @author ZJJ
 * @since 2026-03-10
 */
public interface IMissionRiskService extends IService<MissionRisk> {

    /**
     * 通过实体类获取QueryWrapper
     * @param pageParam 分页参数
     * @param missionRisk 实体信息
     * @return
     */
    QueryWrapper<MissionRisk> getQueryWrapper(PageParam pageParam, MissionRisk missionRisk);

}
