package com.billionfun.bms.product.mall.service;

import java.util.List;
import java.util.Map;

import com.billionfun.bms.product.mall.model.BusDataDictionary;
import com.billionfun.bms.product.mall.vo.BusDataDictionaryVO;

public interface BusDataDictionaryService extends
		BaseService<BusDataDictionary, BusDataDictionaryVO, Long> {
	List<BusDataDictionaryVO> search(BusDataDictionaryVO vo);

	List<BusDataDictionaryVO> getAll(BusDataDictionaryVO vo);

	Map<Long, BusDataDictionaryVO> getAllMap(BusDataDictionaryVO vo);
}
