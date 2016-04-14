package com.billionfun.bms.product.mall.service;

import java.util.List;

import com.billionfun.bms.product.mall.model.BusMallOrder;
import com.billionfun.bms.product.mall.vo.BusMallOrderVO;

public interface BusMallOrderService extends BaseService<BusMallOrder,BusMallOrderVO,String>{
	List<BusMallOrderVO> search(BusMallOrderVO vo);
}
