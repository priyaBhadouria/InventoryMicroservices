package com.ge.current.div.blob.dto;

public class UploadResponseDTO {

	private String directory;
	private String fileName;
	private String status;
	private String SSO;
	

	public String getSSO() {
		return SSO;
	}
	public void setSSO(String sSO) {
		SSO = sSO;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
