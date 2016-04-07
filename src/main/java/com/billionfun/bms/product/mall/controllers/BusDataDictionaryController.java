package com.billionfun.bms.product.mall.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billionfun.bms.product.mall.model.BusDataDictionary;
import com.billionfun.bms.product.mall.service.BusDataDictionaryService;
import com.billionfun.bms.product.mall.vo.BusDataDictionaryVO;

@RequestMapping("/business/dictionary")
@Controller
public class BusDataDictionaryController extends BaseController {

	@Autowired
	private BusDataDictionaryService busDictionaryService;

	@RequestMapping("/query")
	public String query(ModelMap modelMap, BusDataDictionaryVO vo) {
		vo.setUserId(getCurrentUser().getId());
		List<BusDataDictionaryVO> dictionaryVOs = busDictionaryService
				.query(vo);
		modelMap.put("list", dictionaryVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "business/dictionary/query";
	}

	@RequestMapping("/search")
	public String search(ModelMap modelMap, BusDataDictionaryVO vo) {
		vo.setUserId(getCurrentUser().getId());
		List<BusDataDictionaryVO> dictionaryVOs = busDictionaryService
				.search(vo);
		modelMap.put("userdata", dictionaryVOs);
		modelMap.put("list", busDictionaryService.search(vo));
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "business/dictionary/search";
	}

	@RequestMapping("/getall")
	public String getAll(ModelMap modelMap, BusDataDictionaryVO vo) {
		vo.setUserId(getCurrentUser().getId());
		List<BusDataDictionaryVO> dictionaryVOs = busDictionaryService
				.search(vo);
		modelMap.put("list", busDictionaryService.getAll(vo));
		return "business/dictionary/getall";
	}

	@RequestMapping("/modify")
	public String modify(ModelMap modelMap, BusDataDictionaryVO vo) {
		BusDataDictionary dictionary = new BusDataDictionary();
		BeanUtils.copyProperties(vo, dictionary);
		busDictionaryService.update(dictionary);
		return "business/dictionary/modify";
	}

	@RequestMapping("/add")
	public String add(ModelMap modelMap, BusDataDictionaryVO vo) {
		BusDataDictionary dictionary = new BusDataDictionary();
		BeanUtils.copyProperties(vo, dictionary);
		dictionary.setUserId(getCurrentUser().getId());
		dictionary.setCreateDate(new Date());
		busDictionaryService.save(dictionary);
		return "business/dictionary/add";
	}

	@RequestMapping("/delete")
	public String delete(ModelMap modelMap, BusDataDictionaryVO vo) {
		busDictionaryService.delete(vo.getId());
		return "business/dictionary/delete";
	}
}
