package com.billionfun.bms.product.mall.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_log database table.
 * 
 */
@Entity
@Table(name="sys_log")
@NamedQuery(name="SysLog.findAll", query="SELECT s FROM SysLog s")
public class SysLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="LOG_CLASS")
	private String logClass;

	@Column(name="LOG_DATE")
	private String logDate;

	@Column(name="LOG_DESC")
	private String logDesc;

	@Column(name="LOG_IP")
	private String logIp;

	@Column(name="LOG_LEVEL")
	private String logLevel;

	@Column(name="LOG_MESSAGE")
	private String logMessage;

	@Column(name="LOG_NAME")
	private String logName;

	public SysLog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogClass() {
		return this.logClass;
	}

	public void setLogClass(String logClass) {
		this.logClass = logClass;
	}

	public String getLogDate() {
		return this.logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getLogDesc() {
		return this.logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}

	public String getLogIp() {
		return this.logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public String getLogLevel() {
		return this.logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogMessage() {
		return this.logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public String getLogName() {
		return this.logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

}