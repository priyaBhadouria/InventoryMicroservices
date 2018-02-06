package com.ge.current.div.blob.dao;

import java.util.List;
import java.util.Map;

import com.ge.current.div.blob.entity.ExcelUpload;

public interface BlobStoreDao {

	public Map<String,String> insertExcelData(List<String> list);
	public List<Map<String,String>> getExcelTemplateData(String templateId);
	public int insertExcelUploadData(String templateName,String fileName,String filePath,String usercomments,String SSO);
	public int updateExcelUploadData(String uploadId,String xlsId,Map<String,String> errResLogMap);
	public int insertExcelUploadLoggerData(int uploadId,String errLog);
	public int getUploadCount(String tableName); 
	public List<ExcelUpload> getExcelUploadDetails();
	
	public String getTemplateDetails();
	public void insertMasterDataFromStagingTable();
	public void insertValidDataToValidStageTable();
	public void processSAPShipmentData();
	public void processCoreShipmentData();
	public void processServiceProviderReceiptData();
	public void processServiceProviderIssuedData();
	public void processServiceProviderDemandData();
	public void processCustomerInstallDemandData();
	public void processCustomerInstallCertificateData();
	public void processRefreshAllViews();
	public void processFSGConsumptionData();
	public String getUploadDetails();
	public String getLoggerData(String uploadId);
	public void processRefreshAllViewsOnDemand();
	public void callLatLongService();
	public void callFSGLatLongService();
	public void processInstallerReceivedData();
	public void updateConsumptionData(); 
}

