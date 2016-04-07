package com.billionfun.bms.product.mall.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billionfun.bms.product.mall.service.BusEventService;
import com.billionfun.bms.product.mall.vo.BusEventVO;

@RequestMapping("/business/event")
@Controller
public class BusEventController extends BaseController {

	@Autowired
	private BusEventService eventService;

	@RequestMapping("/query")
	public String query(ModelMap modelMap, BusEventVO vo) {
		List<BusEventVO> eventVOs = eventService.query(vo);
		modelMap.put("list", eventVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "business/event/query";
	}

	@RequestMapping("/search")
	public String search(ModelMap modelMap, BusEventVO vo) {
		vo.setUserId(getCurrentUser().getId());
		List<BusEventVO> eventVOs = eventService.search(vo);
		// modelMap.put("userdata", eventVOs);
		modelMap.put("list", eventVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "business/event/search";
	}

	@RequestMapping("/modify")
	public String modify(ModelMap modelMap, BusEventVO vo) {
//		vo.setStatus(1);
		vo.setUserId(getCurrentUser().getId());
		try {
			eventService.update(vo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "business/event/modify";
	}

	@RequestMapping("/add")
	public String add(ModelMap modelMap, BusEventVO vo) {
		vo.setStatus(1);
		vo.setUserId(getCurrentUser().getId());
		try {
			eventService.save(vo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("id", vo.getId());
		return "business/event/add";
	}

	@RequestMapping("/delete")
	public String delete(ModelMap modelMap, BusEventVO vo) {
		eventService.delete(vo);
		return "business/event/delete";
	}
}
