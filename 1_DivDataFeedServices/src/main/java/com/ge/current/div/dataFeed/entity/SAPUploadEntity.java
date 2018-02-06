package com.ge.current.div.dataFeed.entity;

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
@Table(name = "sap_upload", schema = "staging")
public class SAPUploadEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LICENSE_SEQ")
	@SequenceGenerator(name="LICENSE_SEQ",sequenceName="staging.xls_upload_id_seq",allocationSize=1)
	@Column(name = "id")private Long id;
	@Column(name = "records_passed")private Integer recordsPassed;
	@Column(name = "records_failed")private Integer recordsFailed;
	@Column(name = "total_records")private Integer totRecords;
	@Column(name = "service_name")private String serviceName;
	@Column(name = "is_processed")private String isProcessed;
	@Column(name = "create_dtm")private Date createDtm;
	public Integer getRecordsPassed() {
		return recordsPassed;
	}
	public void setRecordsPassed(Integer recordsPassed) {
		this.recordsPassed = recordsPassed;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getRecordsFailed() {
		return recordsFailed;
	}
	public void setRecordsFailed(Integer recordsFailed) {
		this.recordsFailed = recordsFailed;
	}
	public Integer getTotRecords() {
		return totRecords;
	}
	public void setTotRecords(Integer totRecords) {
		this.totRecords = totRecords;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIsProcessed() {
		return isProcessed;
	}
	public void setIsProcessed(String isProcessed) {
		this.isProcessed = isProcessed;
	}
	public Date getCreateDtm() {
		return createDtm;
	}
	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}
	
}
