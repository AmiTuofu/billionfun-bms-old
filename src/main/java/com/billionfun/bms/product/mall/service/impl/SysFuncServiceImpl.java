package com.billionfun.bms.product.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.SysFuncDao;
import com.billionfun.bms.product.mall.model.SysFunc;
import com.billionfun.bms.product.mall.service.SysFuncService;
import com.billionfun.bms.product.mall.vo.SysFuncVO;

@Service("funcService")
public class SysFuncServiceImpl extends BaseServiceImpl<SysFunc,SysFuncVO, String> implements SysFuncService{
	@Autowired
	private SysFuncDao funcDao;
	
	public List<SysFuncVO> getAll(){
		List<SysFunc> list = funcDao.findAll("from SysFunc");
		List<SysFuncVO> listVo = new ArrayList<SysFuncVO>();
		if(!StringUtil.empty(list)){
			for (SysFunc ref : list) {
				SysFuncVO voRef = new SysFuncVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
				
			}
		}
		return listVo;
	}
	public List<SysFuncVO> search(SysFuncVO vo){
		List<SysFunc> list = funcDao.getListByRoleId(vo.getRoleId());
		List<SysFuncVO> listVo = new ArrayList<SysFuncVO>();
		if(!StringUtil.empty(list)){
			for (SysFunc ref : list) {
				SysFuncVO voRef = new SysFuncVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
			}
		}
		return listVo;
	}
	
}
