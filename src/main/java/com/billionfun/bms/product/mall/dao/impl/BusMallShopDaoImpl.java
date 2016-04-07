package com.billionfun.bms.product.mall.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusMallShopDao;
import com.billionfun.bms.product.mall.model.BusMallShop;
import com.billionfun.bms.product.mall.vo.BusMallShopVO;

@Repository
public class BusMallShopDaoImpl extends BaseDaoImpl<BusMallShop,String> implements BusMallShopDao{

	public List<BusMallShop> getListByPage(BusMallShopVO vo) {
		StringBuilder hql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		hql.append(" from BusMallShop l");
		hql.append(" where 1=1");
		if(vo.getSearch()){
			vo.getSearchHql(hql, vo.getFilters(), paramList);
		}
		if(!StringUtil.empty(vo.getSort())&&!StringUtil.empty(vo.getOrder())){
			hql.append(" order by l.").append(vo.getSort()).append(" ").append(vo.getOrder());
		}

		List<BusMallShop> list = super.getListByPage(vo, hql.toString(), paramList);
		return list;
	}

}
