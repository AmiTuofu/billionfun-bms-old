package com.billionfun.bms.product.mall.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billionfun.bms.product.mall.model.BusMallGoods;
import com.billionfun.bms.product.mall.service.BusMallGoodsService;
import com.billionfun.bms.product.mall.vo.BusMallGoodsVO;

@RequestMapping("/bussiness/mall/goods")
@Controller
public class BusMallGoodsController extends BaseController {
	
	@Autowired
	private BusMallGoodsService goodsService;
	
	
	
	@RequestMapping("/query")
	public String query(ModelMap modelMap,BusMallGoodsVO vo){
		List<BusMallGoodsVO> goodsVOs = goodsService.query(vo);
		modelMap.put("list", goodsVOs);
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/goods/query";
	}
	
	@RequestMapping("/search")
	public String search(ModelMap modelMap,BusMallGoodsVO vo){
		List<BusMallGoodsVO> goodsVOs = goodsService.search(vo);
		modelMap.put("userdata", goodsVOs);
		modelMap.put("list", goodsService.search(vo));
		modelMap.put("page", vo.getPage());
		modelMap.put("total", vo.getTotal());
		modelMap.put("records", vo.getRecords());
		return "system/goods/search";
	}
	
	@RequestMapping("/modify")
	public String modify(ModelMap modelMap,BusMallGoodsVO vo){
		BusMallGoods goods = new BusMallGoods();
		BeanUtils.copyProperties(vo, goods);
		goodsService.update(goods);
		return "system/goods/modify";
	}
	
	@RequestMapping("/add")
	public String add(ModelMap modelMap,BusMallGoodsVO vo){
		BusMallGoods goods = new BusMallGoods();
		BeanUtils.copyProperties(vo, goods);
		goodsService.save(goods);
		return "system/goods/add";
	}
	
	@RequestMapping("/delete")
	public String delete(ModelMap modelMap,BusMallGoodsVO vo){
		goodsService.delete(vo.getId());
		return "system/goods/delete";
	}
}
