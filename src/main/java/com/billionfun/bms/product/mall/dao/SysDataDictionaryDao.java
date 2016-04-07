package com.billionfun.bms.product.mall.dao;

import java.util.List;

import com.billionfun.bms.product.mall.model.SysDataDictionary;
import com.billionfun.bms.product.mall.vo.SysDataDictionaryVO;

public interface SysDataDictionaryDao extends BaseDao<SysDataDictionary,String> {
	List<SysDataDictionary> getListByPage(SysDataDictionaryVO vo);
}
