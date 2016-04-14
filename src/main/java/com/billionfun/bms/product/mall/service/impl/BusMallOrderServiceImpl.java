package com.billionfun.bms.product.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusMallOrderDao;
import com.billionfun.bms.product.mall.model.BusMallOrder;
import com.billionfun.bms.product.mall.service.BusMallOrderService;
import com.billionfun.bms.product.mall.vo.BusMallOrderVO;

@Service("orderService")
public class BusMallOrderServiceImpl extends BaseServiceImpl<BusMallOrder,BusMallOrderVO, String> implements BusMallOrderService{
	@Autowired
	private BusMallOrderDao orderDao;

	public List<BusMallOrderVO> search(BusMallOrderVO vo){
		List<BusMallOrder> list = orderDao.getListByPage(vo);
		List<BusMallOrderVO> listVo = new ArrayList<BusMallOrderVO>();
		if(!StringUtil.empty(list)){
			for (BusMallOrder ref : list) {
				BusMallOrderVO voRef = new BusMallOrderVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
			}
		}
		return listVo;
	}
	
}
