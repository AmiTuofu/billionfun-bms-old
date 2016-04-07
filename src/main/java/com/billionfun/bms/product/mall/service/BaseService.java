package com.billionfun.bms.product.mall.service;

import java.io.Serializable;
import java.util.List;

import com.billionfun.bms.product.mall.common.utils.PageUtil;

public interface BaseService<T,V extends PageUtil<T>,P extends Serializable> {
	boolean save(T t);
	boolean update(T t);
	boolean merge(T t);
	boolean update(T t,P p);
	boolean saveOrUpdate(T t);
	boolean delete(T t);
	boolean delete(P p);
	
	List<V> query(V v);
	T get(P p);
	T get(P p,Class<T> cls);
	boolean delete(P p,Class<T> cls);
	boolean update(P p,Class<T> cls,T t);
}
