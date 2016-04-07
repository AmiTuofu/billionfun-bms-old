package com.billionfun.bms.product.mall.dao;

import java.util.Date;
import java.util.List;

import com.billionfun.bms.product.mall.model.BusEvent;
import com.billionfun.bms.product.mall.vo.BusEventVO;

public interface BusEventDao extends BaseDao<BusEvent, String> {
	List<BusEvent> getListByPage(BusEventVO vo);

	List<BusEvent> getList(BusEventVO vo);

	boolean delete(String repeatsId, Date startDate);

	List getRemindList();

	void updateNoticeCount(String id);

	List getFinishedEvents(String userId);
}
