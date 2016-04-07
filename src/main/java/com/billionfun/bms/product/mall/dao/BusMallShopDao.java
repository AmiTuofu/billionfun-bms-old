package com.billionfun.bms.product.mall.dao;

import java.util.List;

import com.billionfun.bms.product.mall.model.BusMallShop;
import com.billionfun.bms.product.mall.vo.BusMallShopVO;

public interface BusMallShopDao extends BaseDao<BusMallShop,String> {
	List<BusMallShop> getListByPage(BusMallShopVO vo);
}
