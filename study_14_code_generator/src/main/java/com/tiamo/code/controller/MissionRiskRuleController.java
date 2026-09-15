package com.tiamo.code.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.tiamo.code.entity.MissionRiskRule;
import com.tiamo.code.service.IMissionRiskRuleService;
import com.tiamo.dt.dev.common.pojo.result.Result;
import com.tiamo.dt.dev.common.pojo.param.IdParam;
import com.tiamo.dt.dev.db.utils.MPUtil;
import com.tiamo.dt.dev.db.pojo.page.PageParam;

import java.util.List;

/**
 *  前端控制器
 *
 * @author ZJJ
 * @since 2026-03-10
 */
@RestController
@AllArgsConstructor
@Slf4j
@Api(value = "", tags = "接口")
@RequestMapping("mission-risk-rule")
public class MissionRiskRuleController {

	private IMissionRiskRuleService missionRiskRuleService;

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "新增", notes = "传入missionRiskRule")
	public Result save(@RequestBody MissionRiskRule missionRiskRule) {
		if(missionRiskRule == null){
			return Result.error("参数异常");
		}
		return Result.status(missionRiskRuleService.save(missionRiskRule));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "修改", notes = "传入missionRiskRule")
	public Result update(@RequestBody MissionRiskRule missionRiskRule) {
		if(missionRiskRule == null){
			return Result.error("参数异常");
		}
		return Result.status(missionRiskRuleService.updateById(missionRiskRule));
	}

	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "删除", notes = "传入ids")
	public Result remove(@RequestBody IdParam<Long> idParam) {
		if(idParam == null){
			return Result.error("参数异常！");
		}

		return Result.status(missionRiskRuleService.removeByIds(idParam.getIds()));
	}

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "详情", notes = "传入id")
	public Result<MissionRiskRule> detail(Long id) {
		if(id == null){
			return Result.error("参数异常");
		}
		MissionRiskRule detail = missionRiskRuleService.getById(id);
		return Result.successData(detail);
	}

	/**
	 * 查询 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "查询", notes = "传入missionRiskRule")
	public Result<List<MissionRiskRule>> list(PageParam pageParam, MissionRiskRule missionRiskRule) {
		List<MissionRiskRule> list = missionRiskRuleService.list(missionRiskRuleService.getQueryWrapper(pageParam, missionRiskRule));
		return Result.successData(list);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "分页", notes = "传入missionRiskRule")
	public Result<IPage<MissionRiskRule>> page(PageParam pageParam, MissionRiskRule missionRiskRule) {
		IPage<MissionRiskRule> pages = missionRiskRuleService.page(
				MPUtil.getPage(pageParam),
				missionRiskRuleService.getQueryWrapper(null, missionRiskRule)
		);
		return Result.successData(pages);
	}

}
