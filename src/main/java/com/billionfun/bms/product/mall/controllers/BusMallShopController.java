package com.billionfun.bms.product.mall.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billionfun.bms.product.mall.model.BusMallShop;
import com.billionfun.bms.product.mall.service.BusMallShopService;
import com.billionfun.bms.product.mall.vo.BusMallShopVO;

@RequestMapping("/business/mall/shop")
@Controller
public class BusMallShopController extends BaseController {
	
	@Autowired
	private BusMallShopService shopService;
	
	
	
	@RequestMapping("/query")
	public String query(ModelMap modelMap,BusMallShopVO vo){
		List<BusMallShopVO> shopVOs = shopService.query(vo);
		modelMap.put("list", shopVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "business/mall/shop/query";
	}
	
	@RequestMapping("/search")
	public String search(ModelMap modelMap,BusMallShopVO vo){
		List<BusMallShopVO> shopVOs = shopService.search(vo);
		modelMap.put("userdata", shopVOs);
		modelMap.put("list", shopVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "business/mall/shop/search";
	}
	
	@RequestMapping("/modify")
	public String modify(ModelMap modelMap,BusMallShopVO vo){
		BusMallShop shop = new BusMallShop();
		BeanUtils.copyProperties(vo, shop);
		shopService.update(shop);
		return "business/mall/shop/modify";
	}
	
	@RequestMapping("/add")
	public String add(ModelMap modelMap,BusMallShopVO vo){
		BusMallShop shop = new BusMallShop();
		BeanUtils.copyProperties(vo, shop);
		shopService.save(shop);
		return "business/mall/shop/add";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap modelMap,BusMallShopVO vo){
		shopService.delete(vo.getId());
		return "business/mall/shop/delete";
	}
	
	@RequestMapping("/review")
	public String review(ModelMap modelMap,BusMallShopVO vo){
		BusMallShop shop = shopService.get(vo.getId());
		shop.setStatus("P");
		shopService.update(shop);
		return "business/mall/shop/review";
	}
}
