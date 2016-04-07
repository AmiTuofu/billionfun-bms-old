package com.billionfun.bms.product.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.SysRoleDao;
import com.billionfun.bms.product.mall.model.SysRole;
import com.billionfun.bms.product.mall.model.SysRoleFunc;
import com.billionfun.bms.product.mall.model.SysRoleFuncPK;
import com.billionfun.bms.product.mall.model.SysUser;
import com.billionfun.bms.product.mall.model.SysUserRole;
import com.billionfun.bms.product.mall.model.SysUserRolePK;
import com.billionfun.bms.product.mall.service.SysRoleService;
import com.billionfun.bms.product.mall.vo.SysFuncVO;
import com.billionfun.bms.product.mall.vo.SysRoleVO;

@Service("roleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole,SysRoleVO, String> implements SysRoleService{
	@Autowired
	private SysRoleDao roleDao;
	
	public List<SysRoleVO> search(SysRoleVO vo) {
		List<SysRole> list = roleDao.getListByPage(vo);
		List<SysRoleVO> listVo = new ArrayList<SysRoleVO>();
		if(!StringUtil.empty(list)){
			for (SysRole ref : list) {
				SysRoleVO voRef = new SysRoleVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
				
			}
		}
		return listVo;
	}

	public List<SysRoleVO> getAll(){
		List<SysRole> list = roleDao.findAll("from SysRole");
		List<SysRoleVO> listVo = new ArrayList<SysRoleVO>();
		if(!StringUtil.empty(list)){
			for (SysRole ref : list) {
				SysRoleVO voRef = new SysRoleVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
				
			}
		}
		return listVo;
	}
	
	public boolean update(SysRoleVO vo){
		boolean sign = false;
		if(!StringUtil.empty(vo.getFuncIds())){
			String[] funcId_arr = vo.getFuncIds().split(",");
			roleDao.deleteByHql("delete from SysRoleFunc where id.roleId="+vo.getId());
			for (int i = 0; i < funcId_arr.length; i++) {
				String funcId = funcId_arr[i];
				SysRoleFunc roleFunc = new SysRoleFunc();
				SysRoleFuncPK pk = new SysRoleFuncPK();
				pk.setFuncId(funcId);
				pk.setRoleId(vo.getId());
				roleFunc.setId(pk);
				roleDao.saveObject(roleFunc);
			}
		}
		sign = true;
		return sign;
	}
	
	public boolean delete(String ids){
		boolean sign = false;
		if(!StringUtil.empty(ids)){
			String[] id_arr = ids.split(",");
			for(int i =0;i<id_arr.length;i++){
				String id = id_arr[i];
				roleDao.deleteByHql("delete from SysRole where id = "+id);
			}
		}
		sign = true;
		return sign;
	}
}
