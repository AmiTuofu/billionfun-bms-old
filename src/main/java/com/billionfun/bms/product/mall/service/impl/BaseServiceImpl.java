package com.billionfun.bms.product.mall.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billionfun.bms.product.mall.common.utils.PageUtil;
import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.BaseDao;
import com.billionfun.bms.product.mall.service.BaseService;

@Service("baseService")
public abstract  class BaseServiceImpl<T,V extends PageUtil<T>,P extends Serializable> implements BaseService<T,V,P> {

	@Autowired
	protected BaseDao<T, P> baseDao;
	
	public boolean save(T t){
		boolean sign = false;
		baseDao.save(t);
		sign = true;
		return sign;
	}
	
	public boolean update(T t){
		boolean sign = false;
		baseDao.update(t);
		sign = true;
		return sign;
	}
	
	public boolean merge(T t){
		boolean sign = false;
		baseDao.merge(t);
		sign = true;
		return sign;
	}
	
	public boolean saveOrUpdate(T t){
		boolean sign = false;
		baseDao.saveOrUpdate(t);
		sign = true;
		return sign;
	}
	public boolean delete(T t){
		boolean sign = false;
		baseDao.delete(t);
		sign = true;
		return sign;
	}
	public boolean delete(P p){
		boolean sign = false;
		if(p != null){
			String[] ids = ((String)p).split(",");
			for(int i =0;i<ids.length;i++){
				String id = ids[i];
				baseDao.delete((P)id);
			}
		}
		sign = true;
		return sign;
	}
	
	public T get(P p){
		return baseDao.get(p);
	}
	
	public List<V> query(V v){
		List<T> list = baseDao.getListByPage(v);
		List<V> listVo = new ArrayList<V>();
		if(!StringUtil.empty(list)){
			for (T ref : list) {
				try {
					V voRef = (V) Class.forName(v.getClass().getName()).newInstance();
					BeanUtils.copyProperties(ref, voRef);
					listVo.add(voRef);
				} catch (BeansException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listVo;
	}
	
	public boolean update(T t,P p){
		boolean sign = false;
		T target = get(p);
		BeanUtils.copyProperties(t, target);
		update(target);
		sign = true;
		return sign;
	}
	
	public T get(P p,Class<T> cls){
		T t = baseDao.get(p, cls);
		return t;
	}
	public  boolean delete(P p,Class<T> cls){
		boolean sign = false;
		baseDao.delete(p, cls);
		sign = true;
		return sign;
	}
	public boolean update(P p,Class<T> cls,T t){
		boolean sign = false;
		T target = get(p,cls);
		BeanUtils.copyProperties(t, target);
		update(target);
		sign = true;
		return sign;
	}
}
