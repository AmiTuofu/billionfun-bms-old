package com.billionfun.bms.product.mall.vo;

import java.util.List;

public class JqGridQueryBase<T> {
	private int rows;
	private int page;
	private int total;
	private int records;
	private String order;
	private String search;
	private List<T> list;
}
