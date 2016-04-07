package com.billionfun.bms.product.mall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billionfun.bms.product.mall.vo.SysLogVO;

@RequestMapping("/business/file")
@Controller
public class FileManagerController extends BaseController {
	
	@RequestMapping("/manager")
	public String manager(ModelMap modelMap,SysLogVO vo){

		return "business/file/manager";
	}

}
