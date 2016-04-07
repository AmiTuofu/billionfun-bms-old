package com.billionfun.bms.product.mall.service;

import java.util.List;

import com.billionfun.bms.product.mall.model.SysFunc;
import com.billionfun.bms.product.mall.vo.SysFuncVO;

public interface SysFuncService extends BaseService<SysFunc,SysFuncVO,String>{
	List<SysFuncVO> search(SysFuncVO vo);
	
	List<SysFuncVO> getAll();
}
