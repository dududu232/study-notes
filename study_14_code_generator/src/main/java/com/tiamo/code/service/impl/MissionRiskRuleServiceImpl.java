package com.tiamo.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tiamo.dt.dev.db.pojo.page.PageParam;
import com.tiamo.dt.dev.db.utils.MPUtil;
import com.tiamo.code.entity.MissionRiskRule;
import com.tiamo.code.mapper.MissionRiskRuleMapper;
import com.tiamo.code.service.IMissionRiskRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author ZJJ
 * @since 2026-03-10
 */
@Service
public class MissionRiskRuleServiceImpl extends ServiceImpl<MissionRiskRuleMapper, MissionRiskRule> implements IMissionRiskRuleService {

    /**
     * 通过实体类获取QueryWrapper
     * @param pageParam 分页参数
     * @param missionRiskRule 实体信息
     * @return
     */
    @Override
    public QueryWrapper<MissionRiskRule> getQueryWrapper(PageParam pageParam, MissionRiskRule missionRiskRule){
        QueryWrapper<MissionRiskRule> queryWrapper = MPUtil.getQueryWrapper(pageParam);

        if(missionRiskRule == null){
            return queryWrapper;
        }

        //主键
        if(missionRiskRule.getId() != null){
            queryWrapper.lambda()
                    .eq(MissionRiskRule::getId, missionRiskRule.getId());
            return queryWrapper;
        }

        //TODO 此处可以根据各字段查询需求修改查询条件，eq、like、ge、gt、le、lt、ne...等等等
        queryWrapper.lambda()
                .eq(missionRiskRule.getType() != null, MissionRiskRule::getType, missionRiskRule.getType())
                .eq(missionRiskRule.getContent() != null, MissionRiskRule::getContent, missionRiskRule.getContent())
                .eq(missionRiskRule.getRiskId() != null, MissionRiskRule::getRiskId, missionRiskRule.getRiskId());
        return queryWrapper;
    }
}
