package com.billionfun.bms.product.mall.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billionfun.bms.product.mall.model.BusMallOrder;
import com.billionfun.bms.product.mall.service.BusMallOrderService;
import com.billionfun.bms.product.mall.vo.BusMallOrderVO;

@RequestMapping("/bussiness/mall/order")
@Controller
public class BusMallOrderController extends BaseController {
	
	@Autowired
	private BusMallOrderService orderService;
	
	
	
	@RequestMapping("/query")
	public String query(ModelMap modelMap,BusMallOrderVO vo){
		List<BusMallOrderVO> orderVOs = orderService.query(vo);
		modelMap.put("list", orderVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/order/query";
	}
	
	@RequestMapping("/search")
	public String search(ModelMap modelMap,BusMallOrderVO vo){
		List<BusMallOrderVO> orderVOs = orderService.search(vo);
		modelMap.put("userdata", orderVOs);
		modelMap.put("list", orderService.search(vo));
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/order/search";
	}
	
	@RequestMapping("/modify")
	public String modify(ModelMap modelMap,BusMallOrderVO vo){
		BusMallOrder order = new BusMallOrder();
		BeanUtils.copyProperties(vo, order);
		orderService.update(order);
		return "system/order/modify";
	}
	
	@RequestMapping("/add")
	public String add(ModelMap modelMap,BusMallOrderVO vo){
		BusMallOrder order = new BusMallOrder();
		BeanUtils.copyProperties(vo, order);
		orderService.save(order);
		return "system/order/add";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap modelMap,BusMallOrderVO vo){
		orderService.delete(vo.getId());
		return "system/order/delete";
	}
}
