package com.ge.current.div.blob.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PROCESS_ON_DEMAND",schema="staging" )
public class ProcessOnDemandEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LICENSE_SEQ")
	@SequenceGenerator(name="LICENSE_SEQ",sequenceName="staging.div_processondemand_seq",allocationSize=1)
	@Column(name = "process_id")
	private  Long process_id;  
	@Column(name = "created_by")private String createdBy;
	@Column(name = "created_dtm")private Date createdDtm;
	@Column(name = "is_process_triggered")private String isProcessTriggered;
	public Long getProcess_id() {
		return process_id;
	}
	public void setProcess_id(Long process_id) {
		this.process_id = process_id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDtm() {
		return createdDtm;
	}
	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}
	public String getIsProcessTriggered() {
		return isProcessTriggered;
	}
	public void setIsProcessTriggered(String isProcessTriggered) {
		this.isProcessTriggered = isProcessTriggered;
	}
	

}
