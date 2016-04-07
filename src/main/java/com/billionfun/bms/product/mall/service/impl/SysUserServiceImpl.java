package com.billionfun.bms.product.mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.ConfigInfo;
import com.billionfun.bms.product.mall.common.utils.EmailUtil;
import com.billionfun.bms.product.mall.common.utils.MD5Util;
import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusEventDao;
import com.billionfun.bms.product.mall.dao.SysFuncDao;
import com.billionfun.bms.product.mall.dao.SysUserDao;
import com.billionfun.bms.product.mall.model.SysFunc;
import com.billionfun.bms.product.mall.model.SysRole;
import com.billionfun.bms.product.mall.model.SysUser;
import com.billionfun.bms.product.mall.model.SysUserRole;
import com.billionfun.bms.product.mall.model.SysUserRolePK;
import com.billionfun.bms.product.mall.service.SysUserService;
import com.billionfun.bms.product.mall.vo.EventReportVO;
import com.billionfun.bms.product.mall.vo.SysUserVO;

@Service("userService")
public class SysUserServiceImpl extends
		BaseServiceImpl<SysUser, SysUserVO, String> implements SysUserService {

	@Autowired
	private SysUserDao userDao;

	@Autowired
	private SysFuncDao funcDao;

	@Autowired
	private BusEventDao eventDao;

	@Autowired
	private ConfigInfo configInfo;

	@Override
	public List<SysUserVO> query(SysUserVO vo) {
		List<SysUser> list = baseDao.getListByPage(vo);
		List<SysUserVO> listVo = new ArrayList<SysUserVO>();
		if (!StringUtil.empty(list)) {
			for (SysUser ref : list) {
				try {
					List<SysRole> roles = ref.getListRoles();
					String roleIds = "";
					String[] temp = new String[] {};
					for (int i = 0; i < roles.size(); i++) {
						SysRole role = roles.get(i);
						roleIds = roleIds + role.getId() + ",";
					}

					SysUserVO voRef = new SysUserVO();
					BeanUtils.copyProperties(ref, voRef);
					voRef.setRoleIds(roleIds);
					listVo.add(voRef);
				} catch (BeansException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listVo;
	}

	public List findAll() {
		return userDao.findAll("from SysUser");
	}

	public boolean isExsit(String username, String email, String mobile) {
		boolean sign = false;
		SysUser user = userDao.loadUser(username, email, mobile);
		if (user != null) {
			sign = true;
		}
		return sign;
	}

	public SysUser loadUser(String username) {
		SysUser user = userDao.loadUserByUsername(username);
		List<SysRole> listRoles = user.getListRoles();
		Map<String, SysFunc> userFuncMap = new HashMap<String, SysFunc>();
		for (SysRole sysRole : listRoles) {
			List<SysFunc> listFuncs = sysRole.getListFuncs();
			for (SysFunc sysFunc : listFuncs) {
				userFuncMap.put(sysFunc.getId(), sysFunc);
			}
		}
		List<SysFunc> listRef = new ArrayList<SysFunc>();
		for (Map.Entry<String, SysFunc> entry : userFuncMap.entrySet()) {
			String funcId = entry.getKey();
			SysFunc func = entry.getValue();
			listRef.add(func);

		}
		user.setListFuncs(listRef);
		List finishedEvents = eventDao.getFinishedEvents(user.getId());
		user.setFinishedEvents(finishedEvents);
		Integer unfinichedCount = 0;
		if (!StringUtil.empty(finishedEvents)) {
			for (int i = 0; i < finishedEvents.size(); i++) {
				EventReportVO reportVO = (EventReportVO) finishedEvents.get(i);
				unfinichedCount = unfinichedCount + reportVO.getSum()
						- reportVO.getFinished();
			}
		}
		user.setUnfinichedCount(unfinichedCount);
		return user;
	}

	/**
	 * 
	 * @Title: 递归获取用户权限
	 * @Description: TODO 一步一步来,从简单开始
	 * @param @param parentId
	 * @param @param userFuncMap
	 * @param @return
	 * @return List<SysFunc>
	 * @throws
	 */
	private List<SysFunc> getFuncList(String parentId,
			Map<String, SysFunc> userFuncMap) {
		List<SysFunc> list = funcDao.loadFunc(parentId);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SysFunc func = list.get(i);
				// 1.与用户权限比较，进行筛选
				// if(userFuncMap.containsKey(func.getId())){
				// listRef.add(func);
				// }
				// List listFunc = getFuncList(func.getId(),userFuncMap);
				// 得到系统所有权限
				// func.setListFuncs(getFuncList(func.getId(),userFuncMap));
				List<SysFunc> list2 = getFuncList(func.getId(), userFuncMap);
				List<SysFunc> listRef = new ArrayList<SysFunc>();
				for (int j = 0; j < list2.size(); j++) {
					SysFunc sysFunc2 = list2.get(j);
					if (userFuncMap.containsKey(sysFunc2.getId())) {
						listRef.add(sysFunc2);
					}
				}
				func.setListFuncs(listRef);
			}
		}
		return list;
	}

	public boolean save(SysUserVO vo) {
		boolean sign = false;
		SysUser user = new SysUser();
		BeanUtils.copyProperties(vo, user);
		user.setCreateDate(new Date());
		userDao.save(user);
		if (!StringUtil.empty(vo.getRoleIds())) {
			String[] roleId_arr = vo.getRoleIds().split(",");
			userDao.deleteByHql("delete from SysUserRole where id.userId ="
					+ user.getId());
			for (int i = 0; i < roleId_arr.length; i++) {
				String roleId = roleId_arr[i];
				SysUserRole userRole = new SysUserRole();
				SysUserRolePK pk = new SysUserRolePK();
				pk.setRoleId(roleId);
				pk.setUserId(user.getId());
				userRole.setId(pk);
				userDao.saveObject(userRole);
			}
		}
		sign = true;
		return sign;
	}

	public boolean update(SysUserVO vo) {
		boolean sign = false;
		SysUser user = new SysUser();
		if (!StringUtil.empty(vo.getPassword())) {
			vo.setPassword(MD5Util.encode(vo.getPassword(), vo.getUsername()));
		}
		BeanUtils.copyProperties(vo, user);
		// BeanUtils.copyProperties(userDao.get(vo.getId()),user);
		userDao.update(user);
		if (!StringUtil.empty(vo.getRoleIds())) {
			String[] roleId_arr = vo.getRoleIds().split(",");
			userDao.deleteByHql("delete from SysUserRole where id.userId="
					+ user.getId());
			for (int i = 0; i < roleId_arr.length; i++) {
				String roleId = roleId_arr[i];
				SysUserRole userRole = new SysUserRole();
				SysUserRolePK pk = new SysUserRolePK();
				pk.setRoleId(roleId);
				pk.setUserId(user.getId());
				userRole.setId(pk);
				userDao.saveObject(userRole);
			}
		}
		sign = true;
		return sign;
	}

	@Override
	public boolean delete(String ids) {
		boolean sign = false;
		if (!StringUtil.empty(ids)) {
			String[] id_arr = ids.split(",");
			for (int i = 0; i < id_arr.length; i++) {
				String id = id_arr[i];
				userDao.deleteByHql("delete from SysUser where id = " + id);
			}
		}
		sign = true;
		return sign;
	}

	public boolean resetPWD(SysUserVO vo) {
		SysUser user = userDao.loadUser(vo.getUsername(), vo.getEmail(),
				vo.getMobile());
		String newPWD = (int) (Math.random() * 1000000) + "";
		vo.setPassword(MD5Util.encode(newPWD, user.getUsername()));
		boolean sign = userDao.updatePWD(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", vo.getEmail());
		map.put("password", newPWD);
		EmailUtil.sendEmail(map, "重置密码", configInfo.getVelocityUserResetpwd(),
				new String[] { vo.getEmail() }, new String[] {});
		return sign;
	}
}
