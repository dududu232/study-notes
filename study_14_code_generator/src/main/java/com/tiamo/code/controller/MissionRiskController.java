package com.tiamo.code.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.tiamo.code.entity.MissionRisk;
import com.tiamo.code.service.IMissionRiskService;
import com.tiamo.dt.dev.common.pojo.result.Result;
import com.tiamo.dt.dev.common.pojo.param.IdParam;
import com.tiamo.dt.dev.db.utils.MPUtil;
import com.tiamo.dt.dev.db.pojo.page.PageParam;

import java.util.List;

/**
 * 风险表 前端控制器
 *
 * @author ZJJ
 * @since 2026-03-10
 */
@RestController
@AllArgsConstructor
@Slf4j
@Api(value = "风险表", tags = "风险表接口")
@RequestMapping("mission-risk")
public class MissionRiskController {

	private IMissionRiskService missionRiskService;

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "新增", notes = "传入missionRisk")
	public Result save(@RequestBody MissionRisk missionRisk) {
		if(missionRisk == null){
			return Result.error("参数异常");
		}
		return Result.status(missionRiskService.save(missionRisk));
	}

	/**
	 * 修改 风险表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "修改", notes = "传入missionRisk")
	public Result update(@RequestBody MissionRisk missionRisk) {
		if(missionRisk == null){
			return Result.error("参数异常");
		}
		return Result.status(missionRiskService.updateById(missionRisk));
	}

	/**
	 * 删除 风险表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "删除", notes = "传入ids")
	public Result remove(@RequestBody IdParam<Long> idParam) {
		if(idParam == null){
			return Result.error("参数异常！");
		}

		return Result.status(missionRiskService.removeByIds(idParam.getIds()));
	}

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "详情", notes = "传入id")
	public Result<MissionRisk> detail(Long id) {
		if(id == null){
			return Result.error("参数异常");
		}
		MissionRisk detail = missionRiskService.getById(id);
		return Result.successData(detail);
	}

	/**
	 * 查询 风险表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "查询", notes = "传入missionRisk")
	public Result<List<MissionRisk>> list(PageParam pageParam, MissionRisk missionRisk) {
		List<MissionRisk> list = missionRiskService.list(missionRiskService.getQueryWrapper(pageParam, missionRisk));
		return Result.successData(list);
	}

	/**
	 * 分页 风险表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "分页", notes = "传入missionRisk")
	public Result<IPage<MissionRisk>> page(PageParam pageParam, MissionRisk missionRisk) {
		IPage<MissionRisk> pages = missionRiskService.page(
				MPUtil.getPage(pageParam),
				missionRiskService.getQueryWrapper(null, missionRisk)
		);
		return Result.successData(pages);
	}

}
