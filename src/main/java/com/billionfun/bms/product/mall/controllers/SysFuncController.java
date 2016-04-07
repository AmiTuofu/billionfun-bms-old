package com.billionfun.bms.product.mall.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billionfun.bms.product.mall.model.SysFunc;
import com.billionfun.bms.product.mall.service.SysFuncService;
import com.billionfun.bms.product.mall.vo.SysFuncVO;

@RequestMapping("/system/func")
@Controller
public class SysFuncController extends BaseController {
	
	@Autowired
	private SysFuncService funcService;
	
	
	
	@RequestMapping("/query")
	public String query(ModelMap modelMap,SysFuncVO vo){
		List<SysFuncVO> funcVOs = funcService.query(vo);
		modelMap.put("list", funcVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/func/query";
	}
	
	@RequestMapping("/search")
	public String search(ModelMap modelMap,SysFuncVO vo){
		List<SysFuncVO> funcVOs = funcService.search(vo);
		modelMap.put("userdata", funcVOs);
		modelMap.put("list", funcService.getAll());
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/role/query";
	}
	
	@RequestMapping("/modify")
	public String modify(ModelMap modelMap,SysFuncVO vo){
		SysFunc func = new SysFunc();
		BeanUtils.copyProperties(vo, func);
		funcService.update(func);
		return "system/func/modify";
	}
	
	@RequestMapping("/add")
	public String add(ModelMap modelMap,SysFuncVO vo){
		SysFunc func = new SysFunc();
		BeanUtils.copyProperties(vo, func);
		funcService.save(func);
		return "system/func/add";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap modelMap,SysFuncVO vo){
		funcService.delete(vo.getId());
		return "system/func/delete";
	}
}
