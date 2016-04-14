package com.billionfun.bms.product.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusMallGoodsDao;
import com.billionfun.bms.product.mall.model.BusMallGoods;
import com.billionfun.bms.product.mall.service.BusMallGoodsService;
import com.billionfun.bms.product.mall.vo.BusMallGoodsVO;

@Service("goodsService")
public class BusMallGoodsServiceImpl extends BaseServiceImpl<BusMallGoods,BusMallGoodsVO, String> implements BusMallGoodsService{
	@Autowired
	private BusMallGoodsDao goodsDao;

	public List<BusMallGoodsVO> search(BusMallGoodsVO vo){
		List<BusMallGoods> list = goodsDao.getListByPage(vo);
		List<BusMallGoodsVO> listVo = new ArrayList<BusMallGoodsVO>();
		if(!StringUtil.empty(list)){
			for (BusMallGoods ref : list) {
				BusMallGoodsVO voRef = new BusMallGoodsVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
			}
		}
		return listVo;
	}
	
}
