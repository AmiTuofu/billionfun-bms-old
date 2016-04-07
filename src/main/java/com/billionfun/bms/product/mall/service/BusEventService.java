package com.billionfun.bms.product.mall.service;

import java.text.ParseException;
import java.util.List;

import com.billionfun.bms.product.mall.model.BusEvent;
import com.billionfun.bms.product.mall.vo.BusEventVO;

public interface BusEventService extends
		BaseService<BusEvent, BusEventVO, String> {
	List<BusEventVO> search(BusEventVO vo);

	boolean save(BusEventVO vo)throws ParseException;
	
	boolean update(BusEventVO vo)throws ParseException;
	
	boolean delete(BusEventVO vo);
	
	List<BusEventVO> getRemindList();
	
	void updateNoticeCount(String id);
}
