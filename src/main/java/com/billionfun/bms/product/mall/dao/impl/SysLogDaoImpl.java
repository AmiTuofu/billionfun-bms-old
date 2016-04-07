package com.billionfun.bms.product.mall.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.SysLogDao;
import com.billionfun.bms.product.mall.model.SysLog;
import com.billionfun.bms.product.mall.vo.SysLogVO;

@Repository
public class SysLogDaoImpl extends BaseDaoImpl<SysLog,String> implements SysLogDao{


	public List<SysLog> getListByPage(SysLogVO vo) {
		StringBuilder hql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		hql.append(" from SysLog l");
		hql.append(" where 1=1");
		if(vo.getSearch()){
			vo.getSearchHql(hql, vo.getFilters(), paramList);
		}
		if(!StringUtil.empty(vo.getSort())&&!StringUtil.empty(vo.getOrder())){
			hql.append(" order by l.").append(vo.getSort()).append(" ").append(vo.getOrder());
		}

//		List<Criterion> criterions = funcVo.generateSearchCriteriaFromFilters(funcVo.getFilters());
		List<SysLog> list = super.getListByPage(vo, hql.toString(), paramList);
		return list;
	}

}
