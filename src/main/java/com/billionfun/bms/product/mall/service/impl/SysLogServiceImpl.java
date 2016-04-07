package com.billionfun.bms.product.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.SysLogDao;
import com.billionfun.bms.product.mall.model.SysLog;
import com.billionfun.bms.product.mall.service.SysLogService;
import com.billionfun.bms.product.mall.vo.SysLogVO;

@Service("logService")
public class SysLogServiceImpl extends BaseServiceImpl<SysLog,SysLogVO, String> implements SysLogService{
	@Autowired
	private SysLogDao logDao;

	public List<SysLogVO> search(SysLogVO vo){
		List<SysLog> list = logDao.getListByPage(vo);
		List<SysLogVO> listVo = new ArrayList<SysLogVO>();
		if(!StringUtil.empty(list)){
			for (SysLog ref : list) {
				SysLogVO voRef = new SysLogVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
			}
		}
		return listVo;
	}
	
}
