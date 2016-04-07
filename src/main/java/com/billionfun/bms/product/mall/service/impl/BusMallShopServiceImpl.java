package com.billionfun.bms.product.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusMallShopDao;
import com.billionfun.bms.product.mall.model.BusMallShop;
import com.billionfun.bms.product.mall.service.BusMallShopService;
import com.billionfun.bms.product.mall.vo.BusMallShopVO;

@Service("shopService")
public class BusMallShopServiceImpl extends BaseServiceImpl<BusMallShop,BusMallShopVO, String> implements BusMallShopService{
	@Autowired
	private BusMallShopDao shopDao;

	public List<BusMallShopVO> search(BusMallShopVO vo){
		List<BusMallShop> list = shopDao.getListByPage(vo);
		List<BusMallShopVO> listVo = new ArrayList<BusMallShopVO>();
		if(!StringUtil.empty(list)){
			for (BusMallShop ref : list) {
				BusMallShopVO voRef = new BusMallShopVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
			}
		}
		return listVo;
	}
	
}
