package com.billionfun.bms.product.mall.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "bus_event")
@NamedQuery(name = "BusEvent.findAll", query = "SELECT s FROM BusEvent s")
public class BusEvent implements Serializable{
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String name;
	private String description;
	// @Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	// @Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	private String repeats;
	private String remind;
	@Column(name = "remind_way")
	private String remindWay;
	@Column(name = "user_id")
	private String userId;
	private String place;
	@Column(name = "style_class")
	private String styleClass;
	@Column(name = "repeats_end_date")
	private Date repeatsEndDate;
	@Column(name = "repeats_id")
	private String repeatsId;
	private Integer status;
	private boolean allDay;
	@Column(name = "bg_color")
	private String backgroundColor;
	@Column(name = "notice_count", columnDefinition = "INT default 0")
	private Integer noticeCount = 0;
	@Column(name = "category_id")
	private Long categoryId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemind() {
		return remind;
	}

	public void setRemind(String remind) {
		this.remind = remind;
	}

	public String getRemindWay() {
		return remindWay;
	}

	public void setRemindWay(String remindWay) {
		this.remindWay = remindWay;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getRepeats() {
		return repeats;
	}

	public void setRepeats(String repeats) {
		this.repeats = repeats;
	}

	public Date getRepeatsEndDate() {
		return repeatsEndDate;
	}

	public void setRepeatsEndDate(Date repeatsEndDate) {
		this.repeatsEndDate = repeatsEndDate;
	}

	public String getRepeatsId() {
		return repeatsId;
	}

	public void setRepeatsId(String repeatsId) {
		this.repeatsId = repeatsId;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Integer getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(Integer noticeCount) {
		this.noticeCount = noticeCount;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
