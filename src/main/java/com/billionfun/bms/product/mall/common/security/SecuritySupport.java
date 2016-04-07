package com.billionfun.bms.product.mall.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billionfun.bms.product.mall.dao.SysUserDao;
import com.billionfun.bms.product.mall.model.SysRole;
import com.billionfun.bms.product.mall.model.SysUser;
@Transactional
@Service("securityService")
public class SecuritySupport implements UserDetailsService {
	@Autowired
	private SysUserDao userDao;

	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = userDao.loadUserByUsername(username);
		
		if(user==null){
			throw new UsernameNotFoundException("username not found");
		} 
		List < GrantedAuthority >  authsList  =   new ArrayList<GrantedAuthority>();
		for (SysRole role : user.getListRoles()) {
			authsList.add(new SimpleGrantedAuthority(role.getCode()));
		}
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), true, true, true, true, authsList);
		
		return userDetails;
	}
	
	
}
