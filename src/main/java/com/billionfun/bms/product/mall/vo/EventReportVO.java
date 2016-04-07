package com.billionfun.bms.product.mall.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventReportVO {
	private Integer categoryId;
	private String name;
	private Integer finished;
	private Integer sum;
	private Integer finishedPer;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getFinished() {
		return finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Integer getFinishedPer() {
		return finishedPer;
	}

	public void setFinishedPer(Integer finishedPer) {
		this.finishedPer = finishedPer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
