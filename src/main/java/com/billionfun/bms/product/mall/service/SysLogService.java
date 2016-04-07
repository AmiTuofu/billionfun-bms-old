package com.billionfun.bms.product.mall.service;

import java.util.List;

import com.billionfun.bms.product.mall.model.SysLog;
import com.billionfun.bms.product.mall.vo.SysLogVO;

public interface SysLogService extends BaseService<SysLog,SysLogVO,String>{
	List<SysLogVO> search(SysLogVO vo);
	
}
