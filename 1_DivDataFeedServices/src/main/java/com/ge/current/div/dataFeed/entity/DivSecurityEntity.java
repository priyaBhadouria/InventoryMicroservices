package com.ge.current.div.dataFeed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="div_security",schema="master" )
public class DivSecurityEntity {
	@Id
	private Long sequerity_key;
	@Column(name = "sso")
	private String sso;
	@Column(name = "group_name")
	private String groupName;
	@Column(name = "sso_desc")
	private String ssoDesc;
	public String getSso() {
		return sso;
	}
	public void setSso(String sso) {
		this.sso = sso;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSsoDesc() {
		return ssoDesc;
	}
	public void setSsoDesc(String ssoDesc) {
		this.ssoDesc = ssoDesc;
	}
	
	
}
