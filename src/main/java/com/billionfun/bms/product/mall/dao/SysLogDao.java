package com.billionfun.bms.product.mall.dao;

import java.util.List;

import com.billionfun.bms.product.mall.model.SysLog;
import com.billionfun.bms.product.mall.vo.SysLogVO;

public interface SysLogDao extends BaseDao<SysLog,String> {

	List<SysLog> getListByPage(SysLogVO vo);
}
