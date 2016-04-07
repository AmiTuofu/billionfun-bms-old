package com.billionfun.bms.product.mall.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.SysRoleDao;
import com.billionfun.bms.product.mall.model.SysRole;
import com.billionfun.bms.product.mall.vo.SysRoleVO;
@Repository
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole,String> implements SysRoleDao{
	public List<SysRole> getListByPage(SysRoleVO vo){
		StringBuilder hql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		hql.append("select r from SysRole r,SysUserRole ur");
		hql.append(" where r.id = ur.id.roleId ");
		if(vo!=null){
			if(!StringUtil.empty(vo.getUserId())){
				hql.append(" and ur.id.userId = ?");
				paramList.add(vo.getUserId());
			}
			
			if(vo.getSearch()){
				vo.setAlias("r");
				vo.getSearchHql(hql, vo.getFilters(), paramList);
			}
			
			if(!StringUtil.empty(vo.getSort())&&!StringUtil.empty(vo.getOrder())){
				hql.append(" order by r.").append(vo.getSort()).append(" ").append(vo.getOrder());
			}
			
		}

//		List<Criterion> criterions = funcVo.generateSearchCriteriaFromFilters(funcVo.getFilters());
		List<SysRole> list = super.getListByPage(vo, hql.toString(), paramList);
		return list;
	}
}
