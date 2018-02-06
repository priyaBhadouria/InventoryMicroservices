package com.ge.current.div.dataFeed.dto;

public class SAPUploadDTO {

	private Long id;
	private Integer recordsPassed;
	private Integer recordsFailed;
	private Integer totRecords;
	private String serviceName;
	private String isProcessed;
	private String createDtm;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getRecordsPassed() {
		return recordsPassed;
	}
	public void setRecordsPassed(Integer recordsPassed) {
		this.recordsPassed = recordsPassed;
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
	public String getCreateDtm() {
		return createDtm;
	}
	public void setCreateDtm(String createDtm) {
		this.createDtm = createDtm;
	}
	
	
}
