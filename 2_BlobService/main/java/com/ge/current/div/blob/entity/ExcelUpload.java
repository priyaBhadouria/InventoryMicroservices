package com.ge.current.div.blob.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 *
 */
@Entity
@Table(schema="staging" , name="xls_upload")
public class ExcelUpload implements Serializable{
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1519012374526199927L;

@Id
@Column(name = "upload_id", nullable = false)
private Integer id;

@Column(name = "xls_id")private Integer requestTypeId;
@Column(name = "file_name")private String fileName;
@Column(name = "csv_blob_store_location")private String csvBlobStoreLocation;
@Column(name = "row_passed")private Integer rowPassed;
@Column(name = "row_failed")private Integer rowFailed;
@Column(name = "total_rows")private Integer toalRows;
@Column(name = "user_comment")private String userComments;
public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}


public Integer getRequestTypeId() {
	return requestTypeId;
}


public void setRequestTypeId(Integer requestTypeId) {
	this.requestTypeId = requestTypeId;
}


public String getFileName() {
	return fileName;
}


public void setFileName(String fileName) {
	this.fileName = fileName;
}


public String getCsvBlobStoreLocation() {
	return csvBlobStoreLocation;
}


public void setCsvBlobStoreLocation(String csvBlobStoreLocation) {
	this.csvBlobStoreLocation = csvBlobStoreLocation;
}


public Integer getRowPassed() {
	return rowPassed;
}


public void setRowPassed(Integer rowPassed) {
	this.rowPassed = rowPassed;
}


public Integer getRowFailed() {
	return rowFailed;
}


public void setRowFailed(Integer rowFailed) {
	this.rowFailed = rowFailed;
}


public Integer getToalRows() {
	return toalRows;
}


public void setToalRows(Integer toalRows) {
	this.toalRows = toalRows;
}


public String getUserComments() {
	return userComments;
}


public void setUserComments(String userComments) {
	this.userComments = userComments;
}


public Character getProcessedFlag() {
	return processedFlag;
}


public void setProcessedFlag(Character processedFlag) {
	this.processedFlag = processedFlag;
}

@Column(name = "db_processed")private Character processedFlag;


	
	
	
	
	
	
	
	
	
	
	
	


	

}
