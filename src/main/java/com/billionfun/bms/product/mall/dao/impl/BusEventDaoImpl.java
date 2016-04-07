package com.billionfun.bms.product.mall.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.billionfun.bms.product.mall.common.Contants;
import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusEventDao;
import com.billionfun.bms.product.mall.model.BusEvent;
import com.billionfun.bms.product.mall.vo.BusEventVO;
import com.billionfun.bms.product.mall.vo.EventReportVO;

@Repository
public class BusEventDaoImpl extends BaseDaoImpl<BusEvent, String> implements
		BusEventDao {

	public List<BusEvent> getListByPage(BusEventVO vo) {
		StringBuilder hql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		hql.append(" from BusEvent e");
		hql.append(" where 1=1");
		if (vo.getSearch()) {
			vo.getSearchHql(hql, vo.getFilters(), paramList);
		}
		if (!StringUtil.empty(vo.getSort()) && !StringUtil.empty(vo.getOrder())) {
			hql.append(" order by e.").append(vo.getSort()).append(" ")
					.append(vo.getOrder());
		}

		List<BusEvent> list = super
				.getListByPage(vo, hql.toString(), paramList);
		return list;
	}

	public List<BusEvent> getList(BusEventVO vo) {
		StringBuilder hql = new StringBuilder();
		List paramList = new ArrayList();
		hql.append(" from BusEvent e");
		hql.append(" where 1=1");

		if (vo.getStartDate() != null && vo.getEndDate() != null) {
			hql.append(" and e.startDate >= ? and e.endDate<= ?");
			paramList.add(vo.getStartDate());
			paramList.add(vo.getEndDate());
		}
		if (!StringUtil.empty(vo.getUserId())) {
			hql.append(" and e.userId = ?");
			paramList.add(vo.getUserId());
		}
		if (vo.getSearch()) {
			vo.getSearchHql(hql, vo.getFilters(), paramList);
		}
		if (!StringUtil.empty(vo.getSort()) && !StringUtil.empty(vo.getOrder())) {
			hql.append(" order by e.").append(vo.getSort()).append(" ")
					.append(vo.getOrder());
		}

		List<BusEvent> list = super.getList(hql.toString(), paramList);
		return list;
	}

	public boolean delete(String repeatsId, Date startDate) {
		boolean sign = false;
		StringBuilder hql = new StringBuilder();
		List paramList = new ArrayList();
		hql.append(" delete from BusEvent e where e.repeatsId = ? and e.startDate >= ?");
		paramList.add(repeatsId);
		paramList.add(startDate);
		super.exec(hql.toString(), paramList);
		sign = true;
		return sign;
	}

	public List<BusEvent> getRemindList() {
		StringBuilder sql = new StringBuilder();
		sql.append(Contants.REMIND_LIST_SQL);
		List list = super.getListBySql(sql.toString());
		List<BusEvent> listRef = new ArrayList<BusEvent>();
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = (Object[]) list.get(i);
			BusEvent event = new BusEvent();
			event.setName(objs[0].toString());
			event.setDescription(objs[1] == null ? null : objs[1].toString());
			event.setStartDate((Date) objs[2]);
			event.setEndDate((Date) objs[3]);
			event.setRemind(objs[4].toString());
			event.setRemindWay(objs[5] == null ? null : objs[5].toString());
			event.setPlace(objs[6] == null ? null : objs[6].toString());
			event.setStatus((Integer) objs[7]);
			event.setUserId(objs[8].toString());
			event.setId(objs[9].toString());
			listRef.add(event);
		}
		return listRef;
	}

	public void updateNoticeCount(String id) {
		super.exec("update BusEvent set noticeCount = noticeCount+1 where id = '"
				+ id + "'");
	}

	public List getFinishedEvents(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append(Contants.getFinishedEvent(userId));
		List list = super.getListBySql(sql.toString());
		List listRef = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = (Object[]) list.get(i);
			EventReportVO vo = new EventReportVO();
			vo.setCategoryId(objs[0] == null ? null : Integer.parseInt(objs[0]
					.toString()));
			vo.setName(objs[1] == null ? null : objs[1].toString());
			vo.setFinished(objs[2] == null ? null : Integer.parseInt(objs[2]
					.toString()));
			vo.setSum(objs[3] == null ? null : Integer.parseInt(objs[3]
					.toString()));
			vo.setFinishedPer(vo.getFinished() * 100 / vo.getSum());
			listRef.add(vo);
		}
		return listRef;
	}
}
