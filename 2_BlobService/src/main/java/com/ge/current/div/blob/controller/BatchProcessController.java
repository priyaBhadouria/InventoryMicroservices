package com.ge.current.div.blob.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ge.current.div.blob.dto.UploadResponseDTO;
import com.ge.current.div.blob.entity.ExcelUpload;
import com.ge.current.div.blob.entity.ProcessOnDemandEntity;
import com.ge.current.div.blob.service.BlobStoreService;

@Component
public class BatchProcessController {
	@Autowired
	private Environment env;
	
	@Autowired
	BlobStoreService blobStoreService;
	
	@Autowired
	ServletContext context;

	private Logger log = LoggerFactory.getLogger(BatchProcessController.class);


	//@Scheduled(cron="*/5 * * * * *") // will running every day 3 PM IST 
	@Scheduled(cron="* 30 14 * * *", zone="Asia/Calcutta")
	 public void executeJob()
	{
		List<ExcelUpload> excelUploadDet= blobStoreService.getExcelUploadDetails();
		log.info("excelUploadDet size----->"+excelUploadDet.size());
		for(ExcelUpload xlsUpload:excelUploadDet){
			//log.info("save to postgres ::: getRequestTypeId  "+xlsUpload.getRequestTypeId()+"getId  "+xlsUpload.getId()+"getFileName  "+xlsUpload.getFileName()+"getCsvBlobStoreLocation  "+xlsUpload.getCsvBlobStoreLocation());
			UploadResponseDTO res = blobStoreService.putDocumentOnPostgres(xlsUpload.getRequestTypeId()+"",xlsUpload.getId()+"",xlsUpload.getFileName(),xlsUpload.getCsvBlobStoreLocation());
			//log.info(xlsUpload.getRequestTypeId()+"------------"+res.getStatus());
			log.info("Data inserted to Staging Tables");
		}
		
		// Process SAP Shipment
					log.info("Process SAP Shipment");
					blobStoreService.processSAPShipmentData();
					log.info("SAP Shipment is complete");
					
		// Process Core Shipment
					log.info("Process Core Shipment");
					blobStoreService.processCoreShipmentData();
					log.info("Core Shipment is complete");
					
		// Process FSG Receipt File
					log.info("Process FSG Receipt Shipment");
					blobStoreService.processServiceProviderReceiptData();
					log.info("FSG Receipt is complete");
					
		// Process FSG Issued File
				   log.info("Process FSG Issue Shipment");
				   blobStoreService.processServiceProviderIssuedData();
				   log.info("FSG Issue is complete");
					
				   
				// Process Installer Received  File
				   log.info("Process  Installer Received");
				   blobStoreService.processInstallerReceivedData();
				   log.info(" Installer Received File is complete");
			
		// Process FSG Demand File
				   log.info("Process FSG Demand Shipment");
				   blobStoreService.processServiceProviderDemandData();
				   log.info("FSG Demand is complete");
				   
		// Process Customer Install Demand File
				   log.info("Process Customer Install Demand Shipment");
				   blobStoreService.processCustomerInstallDemandData();
				   log.info("Customer Install Demand is complete");
				   
		// Process Customer Install Certificate File
				   log.info("Process Customer Install Certificate Shipment");
				   blobStoreService.processCustomerInstallCertificateData();
				   log.info("Customer Install Certificate is complete");
								
		// Process FSG consumption File
				   log.info("Process  FSG consumption Shipment");
				   blobStoreService.processFSGConsumptionData();
				   log.info(" FSG consumption File is complete");
				   
		// Refresh all views 
				   log.info("Start Refresh all views");
				   blobStoreService.processRefreshAllViews();
				   log.info("End Refresh all views");
			   
		//Inserting Latitude and Longitude
				   log.info("start inserting lat long");
				   
				   blobStoreService.callLatLongService();
				   log.info("finished inserting lat long");
				   
				 //Inserting FSG Latitude and Longitude
					  log.info("start inserting FSG lat long");
					   
					  blobStoreService.callFSGLatLongService();
					  log.info("finished FSG lat long");
	}
	 
	

	 public void executeJobOnDemand()
		{
			List<ExcelUpload> excelUploadDet= blobStoreService.getExcelUploadDetails();
			log.info("excelUploadDet size-----executeJobOnDemand >"+excelUploadDet.size());
			for(ExcelUpload xlsUpload:excelUploadDet){
				log.info("save to postgres ::: getRequestTypeId  "+xlsUpload.getRequestTypeId()+"getId  "+xlsUpload.getId()+"getFileName  "+xlsUpload.getFileName()+"getCsvBlobStoreLocation  "+xlsUpload.getCsvBlobStoreLocation());
				UploadResponseDTO res = blobStoreService.putDocumentOnPostgres(xlsUpload.getRequestTypeId()+"",xlsUpload.getId()+"",xlsUpload.getFileName(),xlsUpload.getCsvBlobStoreLocation());
				log.info(xlsUpload.getRequestTypeId()+"------------"+res.getStatus());
			}
			
			// Process SAP Shipment
			log.info("Process SAP Shipment");
			blobStoreService.processSAPShipmentData();
			log.info("SAP Shipment is complete");
			
			// Process Core Shipment
			log.info("Process Core Shipment");
			blobStoreService.processCoreShipmentData(); //error
			log.info("Core Shipment is complete");
			
			// Process FSG Receipt File
		   log.info("Process FSG Receipt Shipment");
			blobStoreService.processServiceProviderReceiptData();
		   log.info("FSG Receipt is complete");
			
			// Process FSG Issued File
		   log.info("Process FSG Issue Shipment");
		   blobStoreService.processServiceProviderIssuedData();
		   log.info("FSG Issue is complete");
			
		// Process Installer Received  File
		   log.info("Process  Installer Received");
		   blobStoreService.processInstallerReceivedData();
		   log.info(" Installer Received File is complete");
	
		   // Process FSG Demand File
		   log.info("Process FSG Demand Shipment");
		   blobStoreService.processServiceProviderDemandData();
		   log.info("FSG Demand is complete");
		   
		   
		   // Process Customer Install Demand File
		   log.info("Process Customer Install Demand Shipment");
		   blobStoreService.processCustomerInstallDemandData();
		   log.info("Customer Install Demand is complete");
		   
		   // Process Customer Install Certificate File
		   log.info("Process Customer Install Certificate Shipment");
		   blobStoreService.processCustomerInstallCertificateData();
		   log.info("Customer Install Certificate is complete");
						
		   // Process FSG consumption File
		   log.info("Process  FSG consumption Shipment");
		   blobStoreService.processFSGConsumptionData();
		   log.info(" FSG consumption File is complete");
		   
		   //Updating the quantity of Received at installer
		   //and GE 3rd party stock based on customer consumption
		   log.info("Updating Quantity based on consumption start");
		   blobStoreService.updateConsumptionData();
		   log.info(" Updating Quantity based on consumption complete");
		   //
			// Refresh all views On Demand
			log.info("Start Refresh all views on Demand");
			blobStoreService.processRefreshAllViewsOnDemand();
			log.info("End Refresh all views");
			
			//Inserting Latitude and Longitude
			  log.info("start inserting lat long");
			   
			  blobStoreService.callLatLongService();
			  log.info("finished inserting lat long");
			  
			//Inserting FSG Latitude and Longitude
			  log.info("start inserting FSG lat long");
			   
			  blobStoreService.callFSGLatLongService();
			  log.info("finished FSG lat long");
		}
	 
	 
	 @Scheduled(cron="*/15 * * * * *")
	 public void callExecuteJobOnDemand()
		{
		 blobStoreService.setListProcessOnDemand();//error
		 return;
		}
}
