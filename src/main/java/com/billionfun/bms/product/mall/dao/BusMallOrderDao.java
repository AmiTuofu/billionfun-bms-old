package com.billionfun.bms.product.mall.dao;

import java.util.List;

import com.billionfun.bms.product.mall.model.BusMallOrder;
import com.billionfun.bms.product.mall.vo.BusMallOrderVO;

public interface BusMallOrderDao extends BaseDao<BusMallOrder,String> {
	List<BusMallOrder> getListByPage(BusMallOrderVO vo);
}
