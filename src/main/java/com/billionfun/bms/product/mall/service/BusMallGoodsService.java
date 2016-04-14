package com.billionfun.bms.product.mall.service;

import java.util.List;

import com.billionfun.bms.product.mall.model.BusMallGoods;
import com.billionfun.bms.product.mall.vo.BusMallGoodsVO;

public interface BusMallGoodsService extends BaseService<BusMallGoods,BusMallGoodsVO,String>{
	List<BusMallGoodsVO> search(BusMallGoodsVO vo);
}
