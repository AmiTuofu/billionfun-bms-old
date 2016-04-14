package com.billionfun.bms.product.mall.dao;

import java.util.List;

import com.billionfun.bms.product.mall.model.BusMallGoods;
import com.billionfun.bms.product.mall.vo.BusMallGoodsVO;

public interface BusMallGoodsDao extends BaseDao<BusMallGoods,String> {
	List<BusMallGoods> getListByPage(BusMallGoodsVO vo);
}
