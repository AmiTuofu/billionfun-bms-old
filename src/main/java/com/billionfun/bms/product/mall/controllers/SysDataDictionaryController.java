package com.billionfun.bms.product.mall.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billionfun.bms.product.mall.common.load.StartOnLoad;
import com.billionfun.bms.product.mall.model.SysDataDictionary;
import com.billionfun.bms.product.mall.service.SysDataDictionaryService;
import com.billionfun.bms.product.mall.vo.SysDataDictionaryVO;

@RequestMapping("/system/dictionary")
@Controller
public class SysDataDictionaryController extends BaseController {
	
	@Autowired
	private SysDataDictionaryService sysDictionaryService;
	
	
	
	@RequestMapping("/query")
	public String query(ModelMap modelMap,SysDataDictionaryVO vo){
		List<SysDataDictionaryVO> dictionaryVOs = sysDictionaryService.query(vo);
		modelMap.put("list", dictionaryVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/dictionary/query";
	}
	
	@RequestMapping("/search")
	public String search(ModelMap modelMap,SysDataDictionaryVO vo){
		List<SysDataDictionaryVO> dictionaryVOs = sysDictionaryService.search(vo);
		modelMap.put("userdata", dictionaryVOs);
		modelMap.put("list", dictionaryVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/dictionary/search";
	}
	
	@RequestMapping("/getall")
	public String getAll(ModelMap modelMap,SysDataDictionaryVO vo){
		List<SysDataDictionaryVO> dictionaryVOs = (List<SysDataDictionaryVO>)StartOnLoad.dataMap.get("dictionaryList");
		modelMap.put("list", dictionaryVOs);
		return "system/dictionary/getall";
	}
	
	@RequestMapping("/modify")
	public String modify(ModelMap modelMap,SysDataDictionaryVO vo){
		SysDataDictionary dictionary = new SysDataDictionary();
		BeanUtils.copyProperties(vo, dictionary);
		sysDictionaryService.update(dictionary);
		return "system/dictionary/modify";
	}
	
	@RequestMapping("/add")
	public String add(ModelMap modelMap,SysDataDictionaryVO vo){
		SysDataDictionary dictionary = new SysDataDictionary();
		BeanUtils.copyProperties(vo, dictionary);
		dictionary.setCreateDate(new Date());
		sysDictionaryService.save(dictionary);
		return "system/dictionary/add";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap modelMap,SysDataDictionaryVO vo){
		sysDictionaryService.delete(vo.getId());
		return "system/dictionary/delete";
	}
}
