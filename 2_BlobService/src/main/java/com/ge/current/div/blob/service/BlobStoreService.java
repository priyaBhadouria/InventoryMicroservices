package com.ge.current.div.blob.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ge.current.div.blob.dto.ProcessOnDemandStatusDTO;
import com.ge.current.div.blob.dto.UploadResponseDTO;
import com.ge.current.div.blob.entity.ExcelUpload;
import com.ge.current.div.blob.entity.ProcessOnDemandEntity;

public interface BlobStoreService {
	public Map<String,String> insertExcelData(List<String> list);
	public List<Map<String,String>> getExcelTemplateData(String templateId);
	public int insertExcelUploadData(String xlsId,String fileName,String filePath,String usercomments, String SSO);
	public int updateExcelUploadData(String uploadId,String xlsId,Map<String,String> errResLogMap);
	public int insertExcelUploadLoggerData(int uploadId,String errLog);
	public int getUploadCount(String tableName);
	public UploadResponseDTO putDocumentOnPostgres(String templateId, String uploadId,String fileName, String filePath);
	
	public InputStream getFile(String directory, String filename) throws Exception;
	public List<ExcelUpload> getExcelUploadDetails();
	public String getLoggerData(String uploadId);
	public String getTemplateDetails();
	public List<UploadResponseDTO> putDocumentOnblob(String templateId, String usercomments,MultipartFile[] files,String SSO);
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
	public void processRefreshAllViewsOnDemand();
	public void initiateProcessOnDemand(String sso);
	public int getProcessOnDemandRequest();
	public List<ProcessOnDemandEntity> getListProcessedOnDemand();
	public void setListProcessOnDemand();
	public ProcessOnDemandStatusDTO enableProcessOnDemand();
	public void callLatLongService();
	public void callFSGLatLongService();
	public void processInstallerReceivedData();
	public void updateConsumptionData();
	
}

