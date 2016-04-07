package com.billionfun.bms.product.mall.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.DateUtil;
import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusEventDao;
import com.billionfun.bms.product.mall.dao.SysUserDao;
import com.billionfun.bms.product.mall.model.BusEvent;
import com.billionfun.bms.product.mall.model.SysUser;
import com.billionfun.bms.product.mall.service.BusDataDictionaryService;
import com.billionfun.bms.product.mall.service.BusEventService;
import com.billionfun.bms.product.mall.vo.BusDataDictionaryVO;
import com.billionfun.bms.product.mall.vo.BusEventVO;

@Service("eventService")
public class BusEventServiceImpl extends
		BaseServiceImpl<BusEvent, BusEventVO, String> implements
		BusEventService {
	@Autowired
	private BusEventDao eventDao;

	@Autowired
	private SysUserDao userDao;

	@Autowired
	private BusDataDictionaryService busDictionaryService;

	/**
	 * 
	 * <p>
	 * Title: search
	 * </p>
	 * <p>
	 * Description: 需要初始化配置数据1:用户事件分类
	 * </p>
	 * 
	 * @param vo
	 * @return
	 * @see com.billionfun.bms.product.mall.service.BusEventService#search(com.billionfun.bms.product.mall.vo.BusEventVO)
	 */
	public List<BusEventVO> search(BusEventVO vo) {
		BusDataDictionaryVO dicVo = new BusDataDictionaryVO();
		dicVo.setUserId(vo.getUserId());
		dicVo.setTypeId(1);
		Map<Long, BusDataDictionaryVO> dicMap = busDictionaryService
				.getAllMap(dicVo);
		List<BusEvent> list = eventDao.getList(vo);
		List<BusEventVO> listVo = new ArrayList<BusEventVO>();
		if (!StringUtil.empty(list)) {
			for (BusEvent ref : list) {
				BusEventVO voRef = new BusEventVO();
				BeanUtils.copyProperties(ref, voRef);
				if (voRef.getCategoryId() != null
						&& dicMap.get(voRef.getCategoryId()) != null) {
					voRef.setCategoryName(dicMap.get(voRef.getCategoryId())
							.getName());
				}

				listVo.add(voRef);
			}
		}
		return listVo;
	}

	public boolean save(BusEventVO vo) throws ParseException {
		if (!StringUtil.empty(vo.getRepeats()) && !vo.getRepeats().equals("0")
				&& vo.getRepeatsEndDate() != null) {
			int dateCount = DateUtil.getDateSpace(vo.getStartDate(),
					vo.getEndDate());
			List<Date> listStartDate = DateUtil.getDates(vo.getStartDate(),
					vo.getRepeatsEndDate(), vo.getRepeats(), 1);
			String repeatsId = vo.getUserId() + "-"
					+ (int) (Math.random() * 100000000);
			for (int i = 0; i < listStartDate.size(); i++) {
				Date startDate = listStartDate.get(i);
				Date endDate = DateUtil.addDate(startDate,
						dateCount * 1000 * 3600 * 24);
				BusEvent event = new BusEvent();
				BeanUtils.copyProperties(vo, event);
				event.setStartDate(startDate);
				event.setEndDate(endDate);
				event.setRepeatsId(repeatsId);
				super.save(event);
			}
		} else {
			BusEvent event = new BusEvent();
			BeanUtils.copyProperties(vo, event);
			super.save(event);
		}
		return true;
	}

	public boolean update(BusEventVO vo) throws ParseException {
		if (!StringUtil.empty(vo.getRepeatsId())) {
			BusEvent event = eventDao.get(vo.getId());
			eventDao.delete(vo.getRepeatsId(), event.getStartDate());
			vo.setId(null);
			save(vo);
		} else {
			BusEvent event = new BusEvent();
			BeanUtils.copyProperties(vo, event);
			super.update(event);
		}
		return true;
	}

	public boolean delete(BusEventVO vo) {
		if (!StringUtil.empty(vo.getRepeatsId())) {
			eventDao.delete(vo.getRepeatsId(), vo.getStartDate());
		} else {
			super.delete(vo.getId());
		}
		return true;
	}

	public List<BusEventVO> getRemindList() {
		List<BusEvent> list = eventDao.getRemindList();
		List<BusEventVO> listRef = new ArrayList<BusEventVO>();
		for (int i = 0; i < list.size(); i++) {
			BusEvent event = list.get(i);
			BusEventVO vo = new BusEventVO();
			BeanUtils.copyProperties(event, vo);
			SysUser user = userDao.get(event.getUserId());
			vo.setFullName(user.getFullName());
			vo.setEmail(user.getEmail());
			listRef.add(vo);
		}
		return listRef;
	}

	public void updateNoticeCount(String id) {
		eventDao.updateNoticeCount(id);
	}
}
