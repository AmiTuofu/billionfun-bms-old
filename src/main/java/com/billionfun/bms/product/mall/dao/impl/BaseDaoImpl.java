package com.billionfun.bms.product.mall.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.billionfun.bms.product.mall.common.Criterion;
import com.billionfun.bms.product.mall.common.Criterion.EqualCriterion;
import com.billionfun.bms.product.mall.common.Criterion.LikeCriterion;
import com.billionfun.bms.product.mall.common.utils.PageUtil;
import com.billionfun.bms.product.mall.common.utils.StringUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public abstract class BaseDaoImpl<T, P extends Serializable> {

	@Autowired
	protected SessionFactory sessionFactory;
	protected Class<T> entityClass;

	public BaseDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			entityClass = (Class<T>) p[0];
		}
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T t) {
		getCurrentSession().save(t);
	}

	public void exec(String hql) {
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);

		query.executeUpdate();
	}

	public void exec(String hql, final List paramList) {
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		if (paramList != null && paramList.size() > 0) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
			}
		}
		query.executeUpdate();
	}

	public void exec(String hql, Map<String, String> proMap) {
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		query.setProperties(proMap);
		query.executeUpdate();
	}

	public void saveObject(Object o) {
		getCurrentSession().save(o);
	}

	public void saveOrUpdate(T t) {
		getCurrentSession().saveOrUpdate(t);
	}

	public void update(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.update(t);
	}

	public void merge(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(t);
	}

	public void delete(T t) {
		Session session = sessionFactory.getCurrentSession();

		session.delete(t);
	}

	public void delete(P id) {
		delete(get(id));
	}

	public void delete(P id, Class<T> cls) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(get(id, cls));
	}

	public void deleteByHql(String hql) {
		Session session = getCurrentSession();
		session.createQuery(hql).executeUpdate();
	}

	public T findById(P id) {
		Session session = sessionFactory.getCurrentSession();

		T t = (T) session.get(entityClass, id);

		return t;
	}

	public T find(String hql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return (T) query.uniqueResult();
	}

	public T find(String hql, Map<String, String> proMap) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setProperties(proMap);
		return (T) query.uniqueResult();
	}

	public List<T> findAll(String hql) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(hql);
		return query.list();
	}

	public List<T> findAll(String hql, Map<String, String> proMap) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(hql);
		query.setProperties(proMap);
		return query.list();
	}

	public T findBySql(String sql) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createSQLQuery(sql);
		return (T) query.uniqueResult();
	}

	public List<T> findAllBySql(String sql) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createSQLQuery(sql);
		return query.list();
	}

	public T get(P id) {
		Session session = getCurrentSession();
		return (T) session.get(entityClass, id);
	}

	public T get(P id, Class cls) {
		Session session = getCurrentSession();
		return (T) session.get(cls, id);
	}

	public List<T> getList(final String hql, final List paramList) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (paramList != null && paramList.size() > 0) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
			}
		}
		List list = query.list();
		return list;
	}
	
	public List<T> getListBySql(final String sql, final List paramList) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		if (paramList != null && paramList.size() > 0) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
			}
		}
		List list = query.list();
		return list;
	}

	public List<T> getListByPage(final int begin, final int pageSize,
			final String hql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List list = query.list();
		return list;
	}

	public List<T> getListByPage(final int begin, final int pageSize,
			final String hql, List paramList) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (paramList != null && paramList.size() > 0) {
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
			}
		}
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List list = query.list();
		return list;
	}

	public List<T> getList(final String hql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List list = query.list();
		return list;
	}
	
	public List<T> getListBySql(final String sql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		return list;
	}

	public List<T> getListByPage(PageUtil<T> pl) {
		StringBuilder hql = new StringBuilder();
		List paramList = new ArrayList();
		hql.append(" from ").append(entityClass.getName());
		hql.append(" where 1=1 ");
		if (pl.getSearch()) {
			pl.getSearchHql(hql, pl.getFilters(), paramList);
		}
		if (!StringUtil.empty(pl.getSort()) && !StringUtil.empty(pl.getOrder())) {
			hql.append(" order by ").append(pl.getSort()).append(" ")
					.append(pl.getOrder());
		}

		List<T> list = getListByPage(pl, hql.toString(), paramList);
		return list;
	}

	public List getListByPage(PageUtil<T> pl, String hql, List params) {
		if (pl == null) {
			pl = new PageUtil();
		}
		pl.setRecords(getTotalCount(hql, params));
		return getListByPage((pl.getPage() - 1) * pl.getRows(), pl.getRows(),
				hql, params);
	}

	public List<T> getListByPage2(PageUtil<T> pl, String hql,
			List<Criterion> criterionList, T t) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(t.getClass());
		for (Criterion criterion : criterionList) {
			if (criterion instanceof EqualCriterion) {
				crit.add(Restrictions.eq(criterion.getField(),
						criterion.getValue()));
			}
			if (criterion instanceof LikeCriterion) {
				crit.add(Restrictions.like(criterion.getField(),
						criterion.getValue()));
			}
		}

		return null;

	}

	public int getTotalCount(String hql, List params) {
		String countStr = getCountHql(hql);
		List list = getList(countStr, params);
		if (StringUtil.empty(list)) {
			return 0;
		} else {
			return ((Long) list.get(0)).intValue();
		}
	}

	private String getCountHql(String hql) {
		int sql_from = hql.indexOf(" from");
		int sql_orderby = hql.indexOf("order by");
		int sql_distinct = hql.indexOf("distinct");
		int sql_groupby = hql.indexOf("group by");
		String countStr = "";
		if (sql_orderby > 0) {
			countStr = "select count(*) "
					+ hql.substring(sql_from, sql_orderby);
		} else {
			countStr = "select count(*) " + hql.substring(sql_from);
		}
		if (sql_distinct > 0 || sql_groupby > 0) {
			countStr = "select count(*) from ( " + hql + ")";
		}
		return countStr;
	}

	public List<T> findByProperty(final String propertyName, final Object value) {
		Session session = sessionFactory.getCurrentSession();

		Assert.hasText(propertyName, "propertyName must specified.");

		Criteria criteria = session.createCriteria(entityClass).add(
				Restrictions.eq(propertyName, value));
		return criteria.list();
	}

	public List<T> findByProperties(final Map<String, Object> map) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(entityClass);
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			criteria.add(Restrictions.eq(key, map.get(key)));
		}
		return criteria.list();
	}
}
