package com.billionfun.bms.product.mall.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.billionfun.bms.product.mall.common.Criterion;
import com.billionfun.bms.product.mall.common.Criterion.CompareType;
/**
 * 
 * @ClassName: PageUtil 
 * @Description: TODO
 * @author  AmiTuofu
 * @date  2015年12月20日 下午3:14:29 
 *
 * @param <T>
 */
@Component
public class PageUtil<T> {
	private int rows = 10;			//每页数据
	private int page = 1;			//当前页面
	private int total = 0;			//总页数
	private int records = 0;		//总条数
	private String sort;			//排序字段
	private String order;			//排序
	private boolean search;			//搜索
	private String oper;			//操作
	private String alias; //表的别名
	private List<T> list;
	private String filters;			//搜索参数
	private SearchFilter searchFilter;
	
	public StringBuilder getSearchHql(StringBuilder hql,String filters,List<String> paramList){
		SearchFilter searchFilter = null;
		if(!StringUtil.empty(filters)){
			searchFilter = JSON.parseObject(filters,SearchFilter.class);
		}
		if(searchFilter!=null){
			for(Rule rule : searchFilter.getRules()){
				if(rule.getOp().equals("eq")){
					hql.append(" and ").append(StringUtil.empty(alias)?"":alias+".").append(rule.getField()).append(" = ?");
					paramList.add(rule.getData());
				}
				if(rule.getOp().equals("cn")){
					hql.append(" and ").append(rule.getField()).append(" like '%'||?||'%'");
					paramList.add(rule.getData());
				}
			}
		}
		return hql;
	}
	
    public List<Criterion> generateSearchCriteriaFromFilters(String filters) {  
        List<Criterion> criteria = new ArrayList<Criterion>();  
          
        JSONObject jsonObject = JSON.parseObject(filters);
          
        JSONArray rules = jsonObject.getJSONArray("rules");  
          
        for(Object obj : rules) {  
            JSONObject rule = (JSONObject) obj;  
              
            String field = rule.getString("field");  
            String op = rule.getString("op");  
            String data = rule.getString("data");  
              
            Criterion criterion = this.generateSearchCriterion(field, data, op);  
              
            if(criterion != null) {  
                criteria.add(criterion);  
            }  
        }  
          
        return criteria;  
    }  
	
	public Criterion generateSearchCriterion(String searchField,  
            String searchString, String searchOper) {  
        Criterion criterion = null;  
          
        // (7)如果searchField、searchString、searchOper均不为null，且searchString不为空字符串时，则创建Criterion  
        if (searchField != null && searchString != null  
                & searchString.length() > 0 && searchOper != null) {  
            if ("eq".equals(searchOper)) {  
                criterion = Criterion.getEqualCriterion(searchField,  
                        searchString, null);  
            } else if ("ne".equals(searchOper)) {  
                criterion = Criterion.getCompareCriterion(CompareType.NE,  
                        searchField, searchString, null);  
            } else if ("lt".equals(searchOper)) {  
                criterion = Criterion.getCompareCriterion(CompareType.LT,  
                        searchField, searchString, null);  
            } else if ("le".equals(searchOper)) {  
                criterion = Criterion.getCompareCriterion(CompareType.LTE,  
                        searchField, searchString, null);  
            } else if ("gt".equals(searchOper)) {  
                criterion = Criterion.getCompareCriterion(CompareType.GT,  
                        searchField, searchString, null);  
            } else if ("ge".equals(searchOper)) {  
                criterion = Criterion.getCompareCriterion(CompareType.GTE,  
                        searchField, searchString, null);  
            } else if ("bw".equals(searchOper)) {  
                criterion = Criterion.getLikeCriterion(searchField,  
                        searchString + "%", null);  
            } else if ("bn".equals(searchOper)) {  
                criterion = Criterion.getNotLikeCriterion(searchField,  
                        searchString + "%", null);  
            } else if ("ew".equals(searchOper)) {  
                criterion = Criterion.getLikeCriterion(searchField, "%"  
                        + searchString, null);  
            } else if ("en".equals(searchOper)) {  
                criterion = Criterion.getNotLikeCriterion(searchField, "%"  
                        + searchString, null);  
            } else if ("cn".equals(searchOper)) {  
                criterion = Criterion.getLikeCriterion(searchField, "%"  
                        + searchString + "%", null);  
            } else if ("nc".equals(searchOper)) {  
                criterion = Criterion.getNotLikeCriterion(searchField, "%"  
                        + searchString + "%", null);  
            }  
        }  
        return criterion;  
    }  
	
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		if( this.records % this.rows == 0){
			return this.records / this.rows;
		}else{
			return this.records / this.rows + 1;
		}
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean getSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getRows() {
		return rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}
	
	public SearchFilter getSearchFilter() {
		SearchFilter searchFilter = null;
		if(!StringUtil.empty(filters)){
			searchFilter = JSON.parseObject(filters,SearchFilter.class);
		}
	
		return searchFilter;
	}


	public void setSearchFilter(SearchFilter searchFilter) {
		this.searchFilter = searchFilter;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
