package com.billionfun.bms.product.mall.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_user_role database table.
 * 
 */
@Entity
@Table(name="sys_user_role")
@NamedQuery(name="SysUserRole.findAll", query="SELECT s FROM SysUserRole s")
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysUserRolePK id;

	public SysUserRole() {
	}

	public SysUserRolePK getId() {
		return this.id;
	}

	public void setId(SysUserRolePK id) {
		this.id = id;
	}

}