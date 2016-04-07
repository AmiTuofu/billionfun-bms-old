package com.billionfun.bms.product.mall.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_role_func database table.
 * 
 */
@Entity
@Table(name="sys_role_func")
@NamedQuery(name="SysRoleFunc.findAll", query="SELECT s FROM SysRoleFunc s")
public class SysRoleFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysRoleFuncPK id;

	public SysRoleFunc() {
	}

	public SysRoleFuncPK getId() {
		return this.id;
	}

	public void setId(SysRoleFuncPK id) {
		this.id = id;
	}

}