package com.billionfun.bms.product.mall.service;

import java.util.List;

import com.billionfun.bms.product.mall.model.SysDataDictionary;
import com.billionfun.bms.product.mall.vo.SysDataDictionaryVO;

public interface SysDataDictionaryService extends BaseService<SysDataDictionary,SysDataDictionaryVO,String>{
	List<SysDataDictionaryVO> search(SysDataDictionaryVO vo);
	
	List<SysDataDictionaryVO> getAll();
}
