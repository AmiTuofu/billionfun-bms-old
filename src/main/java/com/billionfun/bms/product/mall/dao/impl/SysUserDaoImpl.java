package com.billionfun.bms.product.mall.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.SysUserDao;
import com.billionfun.bms.product.mall.model.SysUser;
import com.billionfun.bms.product.mall.vo.SysUserVO;


@Repository
public class SysUserDaoImpl extends BaseDaoImpl<SysUser,String> implements SysUserDao {

	/**
	 * 
	 * <p>Title: loadUserByUsername</p> 
	 * <p>Description: </p> 
	 * @param username
	 * @return 
	 * @see com.billionfun.bms.product.mall.dao.SysUserDao#loadUserByUsername(java.lang.String)
	 */
	public SysUser loadUserByUsername(String username) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from SysUser u where u.username = :username");
		
		Map proMap = new HashMap();
		proMap.put("username", username);
		
		SysUser u = super.find(hql.toString(), proMap);
		return u;
	}

	
	public SysUser loadUser(String username, String email, String mobile) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from SysUser u ");
		hql.append(" where u.username in('").append(username).append("','").append(email).append("','").append(mobile).append("')");
		hql.append(" or u.email in('").append(username).append("','").append(email).append("','").append(mobile).append("')");
		hql.append(" or u.mobile in('").append(username).append("','").append(email).append("','").append(mobile).append("')");

		SysUser u = super.find(hql.toString());
		return u;
	}
	
	public boolean updatePWD(SysUserVO vo){
		boolean sign = false;
		StringBuilder hql = new StringBuilder();
		Map proMap = new HashMap();
		if(!StringUtil.empty(vo.getEmail())){
			hql.append("update SysUser u set u.password = :password where u.email = :email" );
			proMap.put("password", vo.getPassword());
			proMap.put("email", vo.getEmail());
		}
		super.exec(hql.toString(),proMap);
		sign = true;
		return sign;
	}


}
