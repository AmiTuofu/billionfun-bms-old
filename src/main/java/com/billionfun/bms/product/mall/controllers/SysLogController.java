package com.billionfun.bms.product.mall.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billionfun.bms.product.mall.model.SysLog;
import com.billionfun.bms.product.mall.service.SysLogService;
import com.billionfun.bms.product.mall.vo.SysLogVO;

@RequestMapping("/system/log")
@Controller
public class SysLogController extends BaseController {
	
	@Autowired
	private SysLogService logService;
	
	
	
	@RequestMapping("/query")
	public String query(ModelMap modelMap,SysLogVO vo){
		List<SysLogVO> logVOs = logService.query(vo);
		modelMap.put("list", logVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/log/query";
	}
	
	@RequestMapping("/search")
	public String search(ModelMap modelMap,SysLogVO vo){
		List<SysLogVO> logVOs = logService.search(vo);
		modelMap.put("userdata", logVOs);
		modelMap.put("list", logService.search(vo));
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/log/search";
	}
	
	@RequestMapping("/modify")
	public String modify(ModelMap modelMap,SysLogVO vo){
		SysLog log = new SysLog();
		BeanUtils.copyProperties(vo, log);
		logService.update(log);
		return "system/log/modify";
	}
	
	@RequestMapping("/add")
	public String add(ModelMap modelMap,SysLogVO vo){
		SysLog func = new SysLog();
		BeanUtils.copyProperties(vo, func);
		logService.save(func);
		return "system/log/add";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap modelMap,SysLogVO vo){
		logService.delete(vo.getId());
		return "system/log/delete";
	}
}
