package com.billionfun.bms.product.mall.common.load;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.billionfun.bms.product.mall.service.SysDataDictionaryService;
import com.billionfun.bms.product.mall.service.SysFuncService;
import com.billionfun.bms.product.mall.vo.SysDataDictionaryVO;
import com.billionfun.bms.product.mall.vo.SysFuncVO;

@Component
public class StartOnLoad implements BeanPostProcessor {
	public static Map<String, Object> dataMap = new HashMap<String, Object>();

	public Object postProcessAfterInitialization(Object obj, String arg1)
			throws BeansException {
//		System.out.println(obj.toString());
		if (obj instanceof SysFuncService) {
			List<SysFuncVO> funcList = ((SysFuncService) obj).getAll();
			dataMap.put("funcList", funcList);
		}
		if (obj instanceof SysDataDictionaryService) {
			List<SysDataDictionaryVO> dictionaryList = ((SysDataDictionaryService) obj).getAll();
			dataMap.put("dictionaryList", dictionaryList);
		}
		
		return obj;
	}

	public Object postProcessBeforeInitialization(Object obj, String arg1)
			throws BeansException {
		// TODO Auto-generated method stub
		return obj;
	}
}
