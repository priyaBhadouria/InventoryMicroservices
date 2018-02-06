package com.ge.current.div.dataFeed.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logger", schema = "staging")
public class SAPLoggerEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "upload_or_run_id")private Long sapUploadId;
	@Column(name = "log")private String errorLog;
	@Column(name = "create_dtm")private Date createDtm;
	public Long getSapUploadId() {
		return sapUploadId;
	}
	public void setSapUploadId(Long sapUploadId) {
		this.sapUploadId = sapUploadId;
	}
	public String getErrorLog() {
		return errorLog;
	}
	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}
	public Date getCreateDtm() {
		return createDtm;
	}
	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}
	
}
