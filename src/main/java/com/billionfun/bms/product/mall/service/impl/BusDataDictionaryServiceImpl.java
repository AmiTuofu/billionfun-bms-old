package com.billionfun.bms.product.mall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BusDataDictionaryDao;
import com.billionfun.bms.product.mall.model.BusDataDictionary;
import com.billionfun.bms.product.mall.service.BusDataDictionaryService;
import com.billionfun.bms.product.mall.vo.BusDataDictionaryVO;

@Service("busDictionaryService")
public class BusDataDictionaryServiceImpl extends
		BaseServiceImpl<BusDataDictionary, BusDataDictionaryVO, Long> implements
		BusDataDictionaryService {
	@Autowired
	private BusDataDictionaryDao dictionaryDao;

	public List<BusDataDictionaryVO> search(BusDataDictionaryVO vo) {
		List<BusDataDictionary> list = dictionaryDao.getListByPage(vo);
		List<BusDataDictionaryVO> listVo = new ArrayList<BusDataDictionaryVO>();
		if (!StringUtil.empty(list)) {
			for (BusDataDictionary ref : list) {
				BusDataDictionaryVO voRef = new BusDataDictionaryVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
			}
		}
		return listVo;
	}

	public List<BusDataDictionaryVO> getAll(BusDataDictionaryVO vo) {
		List<BusDataDictionary> list = dictionaryDao.getList(vo);
		List<BusDataDictionaryVO> listVo = new ArrayList<BusDataDictionaryVO>();
		if (!StringUtil.empty(list)) {
			for (BusDataDictionary ref : list) {
				BusDataDictionaryVO voRef = new BusDataDictionaryVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
			}
		}
		return listVo;
	}

	public Map<Long, BusDataDictionaryVO> getAllMap(BusDataDictionaryVO vo) {
		List<BusDataDictionaryVO> listVo = getAll(vo);
		Map<Long, BusDataDictionaryVO> mapVo = new HashMap<Long, BusDataDictionaryVO>();
		for (BusDataDictionaryVO dicVo : listVo) {
			mapVo.put(dicVo.getId(), dicVo);
		}
		return mapVo;
	}
}
