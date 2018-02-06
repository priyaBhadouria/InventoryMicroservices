package com.ge.current.div.dataFeed.dto;

import java.io.Serializable;
import java.util.List;

public class ExtractExcelDTO implements Serializable {
	private static final long serialVersionUID = 1519012374526199927L;
	
	private List<String> excelData;
	private String status;
	private String errorLog;
	
	public List<String> getExcelData() {
		return excelData;
	}
	public void setExcelData(List<String> excelData) {
		this.excelData = excelData;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorLog() {
		return errorLog;
	}
	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}

}
