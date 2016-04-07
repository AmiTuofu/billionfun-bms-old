package com.billionfun.bms.product.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.SysDataDictionaryDao;
import com.billionfun.bms.product.mall.model.SysDataDictionary;
import com.billionfun.bms.product.mall.service.SysDataDictionaryService;
import com.billionfun.bms.product.mall.vo.SysDataDictionaryVO;

@Service("sysDictionaryService")
public class SysDataDictionaryServiceImpl extends BaseServiceImpl<SysDataDictionary,SysDataDictionaryVO, String> implements SysDataDictionaryService{
	@Autowired
	private SysDataDictionaryDao dictionaryDao;

	public List<SysDataDictionaryVO> search(SysDataDictionaryVO vo){
		List<SysDataDictionary> list = dictionaryDao.getListByPage(vo);
		List<SysDataDictionaryVO> listVo = new ArrayList<SysDataDictionaryVO>();
		if(!StringUtil.empty(list)){
			for (SysDataDictionary ref : list) {
				SysDataDictionaryVO voRef = new SysDataDictionaryVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
			}
		}
		return listVo;
	}
	
	public List<SysDataDictionaryVO> getAll(){
		List<SysDataDictionary> list = dictionaryDao.findAll("from SysDataDictionary");
		List<SysDataDictionaryVO> listVo = new ArrayList<SysDataDictionaryVO>();
		if(!StringUtil.empty(list)){
			for (SysDataDictionary ref : list) {
				SysDataDictionaryVO voRef = new SysDataDictionaryVO();
				BeanUtils.copyProperties(ref, voRef);
				listVo.add(voRef);
				
			}
		}
		return listVo;
	}
}
