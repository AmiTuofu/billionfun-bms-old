package com.billionfun.bms.product.mall.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusDataDictionaryDao;
import com.billionfun.bms.product.mall.model.BusDataDictionary;
import com.billionfun.bms.product.mall.vo.BusDataDictionaryVO;

@Repository
public class BusDataDictionaryDaoImpl extends
		BaseDaoImpl<BusDataDictionary, Long> implements BusDataDictionaryDao {

	public List<BusDataDictionary> getListByPage(BusDataDictionaryVO vo) {
		StringBuilder hql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		hql.append(" from BusDataDictionary d");
		hql.append(" where 1=1");
		if (!StringUtil.empty(vo.getUserId())) {
			hql.append(" and d.userId = ?");
			paramList.add(vo.getUserId());
		}
		if (vo.getSearch()) {
			vo.getSearchHql(hql, vo.getFilters(), paramList);
		}
		if (!StringUtil.empty(vo.getSort()) && !StringUtil.empty(vo.getOrder())) {
			hql.append(" order by d.").append(vo.getSort()).append(" ")
					.append(vo.getOrder());
		}

		List<BusDataDictionary> list = super.getListByPage(vo, hql.toString(),
				paramList);
		return list;
	}

	public List<BusDataDictionary> getList(BusDataDictionaryVO vo) {
		StringBuilder hql = new StringBuilder();
		List paramList = new ArrayList();
		hql.append(" from BusDataDictionary d");
		hql.append(" where 1=1");
		if (!StringUtil.empty(vo.getUserId())) {
			hql.append(" and d.userId = ?");
			paramList.add(vo.getUserId());
		}
		if (!StringUtil.empty(vo.getTypeId())) {
			hql.append(" and d.typeId = ?");
			paramList.add(vo.getTypeId());
		}
		if (vo.getSearch()) {
			vo.getSearchHql(hql, vo.getFilters(), paramList);
		}
		if (!StringUtil.empty(vo.getSort()) && !StringUtil.empty(vo.getOrder())) {
			hql.append(" order by d.").append(vo.getSort()).append(" ")
					.append(vo.getOrder());
		}

		List<BusDataDictionary> list = super.getList(hql.toString(), paramList);
		return list;
	}

}
