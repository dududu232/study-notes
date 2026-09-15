package com.tiamo.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tiamo.dt.dev.db.pojo.page.PageParam;
import com.tiamo.dt.dev.db.utils.MPUtil;
import com.tiamo.code.entity.MissionRisk;
import com.tiamo.code.mapper.MissionRiskMapper;
import com.tiamo.code.service.IMissionRiskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 风险表 服务实现类
 *
 * @author ZJJ
 * @since 2026-03-10
 */
@Service
public class MissionRiskServiceImpl extends ServiceImpl<MissionRiskMapper, MissionRisk> implements IMissionRiskService {

    /**
     * 通过实体类获取QueryWrapper
     * @param pageParam 分页参数
     * @param missionRisk 实体信息
     * @return
     */
    @Override
    public QueryWrapper<MissionRisk> getQueryWrapper(PageParam pageParam, MissionRisk missionRisk){
        QueryWrapper<MissionRisk> queryWrapper = MPUtil.getQueryWrapper(pageParam);

        if(missionRisk == null){
            return queryWrapper;
        }

        //主键
        if(missionRisk.getId() != null){
            queryWrapper.lambda()
                    .eq(MissionRisk::getId, missionRisk.getId());
            return queryWrapper;
        }

        //TODO 此处可以根据各字段查询需求修改查询条件，eq、like、ge、gt、le、lt、ne...等等等
        queryWrapper.lambda()
                .eq(missionRisk.getRiskNum() != null, MissionRisk::getRiskNum, missionRisk.getRiskNum())
                .eq(missionRisk.getRiskDesc() != null, MissionRisk::getRiskDesc, missionRisk.getRiskDesc())
                .eq(missionRisk.getRiskSource() != null, MissionRisk::getRiskSource, missionRisk.getRiskSource())
                .eq(missionRisk.getSecondRiskAreaId() != null, MissionRisk::getSecondRiskAreaId, missionRisk.getSecondRiskAreaId());
        return queryWrapper;
    }
}
