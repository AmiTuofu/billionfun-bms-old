package com.billionfun.bms.product.mall.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sys_role_func database table.
 * 
 */
@Embeddable
public class SysRoleFuncPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="role_id")
	private String roleId;

	@Column(name="func_id")
	private String funcId;

	public SysRoleFuncPK() {
	}
	public String getRoleId() {
		return this.roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getFuncId() {
		return this.funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SysRoleFuncPK)) {
			return false;
		}
		SysRoleFuncPK castOther = (SysRoleFuncPK)other;
		return 
			this.roleId.equals(castOther.roleId)
			&& this.funcId.equals(castOther.funcId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId.hashCode();
		hash = hash * prime + this.funcId.hashCode();
		
		return hash;
	}
}