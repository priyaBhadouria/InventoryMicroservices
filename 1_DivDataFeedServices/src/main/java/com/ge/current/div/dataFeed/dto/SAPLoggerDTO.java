package com.ge.current.div.dataFeed.dto;

public class SAPLoggerDTO {

	private Long sapUploadId;
	private String errorLog;
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
	
}
