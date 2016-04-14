package com.billionfun.bms.product.mall.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusMallGoodsDao;
import com.billionfun.bms.product.mall.model.BusMallGoods;
import com.billionfun.bms.product.mall.vo.BusMallGoodsVO;

@Repository
public class BusMallGoodsDaoImpl extends BaseDaoImpl<BusMallGoods,String> implements BusMallGoodsDao{

	public List<BusMallGoods> getListByPage(BusMallGoodsVO vo) {
		StringBuilder hql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		hql.append(" from BusMallGoods l");
		hql.append(" where 1=1");
		if(vo.getSearch()){
			vo.getSearchHql(hql, vo.getFilters(), paramList);
		}
		if(!StringUtil.empty(vo.getSort())&&!StringUtil.empty(vo.getOrder())){
			hql.append(" order by l.").append(vo.getSort()).append(" ").append(vo.getOrder());
		}

		List<BusMallGoods> list = super.getListByPage(vo, hql.toString(), paramList);
		return list;
	}

}
