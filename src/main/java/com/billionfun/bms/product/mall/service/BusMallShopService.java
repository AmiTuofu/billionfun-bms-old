package com.billionfun.bms.product.mall.service;

import java.util.List;

import com.billionfun.bms.product.mall.model.BusMallShop;
import com.billionfun.bms.product.mall.vo.BusMallShopVO;

public interface BusMallShopService extends BaseService<BusMallShop,BusMallShopVO,String>{
	List<BusMallShopVO> search(BusMallShopVO vo);
}
