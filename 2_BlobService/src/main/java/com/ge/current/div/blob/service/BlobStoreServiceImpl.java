package com.ge.current.div.blob.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
//import com.opencsv.CSVReader;
import com.ge.current.div.blob.common.utility.Constants;
import com.ge.current.div.blob.common.utility.ProcessOnDemandRepository;
import com.ge.current.div.blob.common.utility.QueriesDiv;
import com.ge.current.div.blob.common.utility.Utility;
import com.ge.current.div.blob.controller.BatchProcessController;
import com.ge.current.div.blob.dao.BlobStoreDao;
import com.ge.current.div.blob.dto.ExtractExcelDTO;
import com.ge.current.div.blob.dto.ProcessOnDemandStatusDTO;
import com.ge.current.div.blob.dto.UploadResponseDTO;
import com.ge.current.div.blob.entity.ExcelUpload;
import com.ge.current.div.blob.entity.ProcessOnDemandEntity;
import com.ge.current.div.blob.utils.CSVUtils;


@Service
public class BlobStoreServiceImpl implements BlobStoreService {


	private Logger log = LoggerFactory.getLogger(BlobStoreServiceImpl.class);

	@Autowired
	BlobStoreDao userDao;

	@Autowired
	CSVUtils csvUtils;
	
	@Autowired
	ProcessOnDemandRepository processOnDemandRepo;
	
	@Autowired
	BatchProcessController batchController;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Map<String,String> insertExcelData(List<String> list){
		return userDao.insertExcelData(list);
	}
	@Override
	public List<Map<String,String>> getExcelTemplateData(String templateId){
		return userDao.getExcelTemplateData(templateId); 
	}
	@Override
	public int insertExcelUploadData(String templateName,String fileName,String filePath,String usercomments, String SSO){
		return userDao.insertExcelUploadData(templateName,fileName,filePath,usercomments,SSO); 
	}
	@Override
	public int updateExcelUploadData(String uploadId,String xlsId,Map<String,String> errResLogMap){
		return userDao.updateExcelUploadData(uploadId,xlsId,errResLogMap);

	}
	@Override
	public int insertExcelUploadLoggerData(int uploadId,String errLog){
		return userDao.insertExcelUploadLoggerData(uploadId,errLog);

	}
	@Override
	public int getUploadCount(String tableName){
		return userDao.getUploadCount(tableName);
	}

	public List<ExcelUpload> getExcelUploadDetails(){
		return userDao.getExcelUploadDetails();
	}
	


	@Override
	public List<UploadResponseDTO> putDocumentOnblob(String templateName, String usercomments,MultipartFile[] files,String SSO)
	{
		log.info("BlobStoreServiceImpl-->putDocumentOnblob : Start");
		String directory = "div/blobstore/"+System.currentTimeMillis();
		String folderDirectory = directory + File.separator ;

		List<UploadResponseDTO> response= new ArrayList<UploadResponseDTO>();
		
		
	//log.info("In putDocumentOnblob()1 " + folderDirectory);

      JSONObject credentials = getCredentials("VCAP_SERVICES", "predix-blobstore", "credentials");
		ObjectMetadata om = new ObjectMetadata();
		AmazonS3 s3client = getS3Client(credentials);
		String s3_bucket_name = getBucketName(credentials);

		//log.info("BlobStoreServiceImpl-->putDocumentOnblob " + credentials);
		//log.info("BlobStoreServiceImpl-->putDocumentOnblob " + s3_bucket_name);
		//log.info("BlobStoreServiceImpl-->putDocumentOnblob " + files.length);

		if (files != null && files.length > 0)
		{
			for (int i = 0; i < files.length; i++)
			{
				MultipartFile uploadfile = files[i];
				InputStream stream=null;
				try
				{
					//log.info("BlobStoreServiceImpl-->putDocumentOnblob : File Name -  " + uploadfile.getOriginalFilename());

					stream = new ByteArrayInputStream(uploadfile.getBytes());

					om.setContentLength(uploadfile.getBytes().length);
					s3client.putObject(new PutObjectRequest(s3_bucket_name, folderDirectory + uploadfile.getOriginalFilename(), stream, om));

				//log.info("In saveFile : " + uploadfile.getOriginalFilename());

					UploadResponseDTO obj = new UploadResponseDTO();
					obj.setDirectory(directory);
					obj.setFileName(uploadfile.getOriginalFilename());
					obj.setStatus(Constants.SUCCESS_MSG);
					obj.setSSO(SSO);
					response.add(obj);
					int res = insertExcelUploadData(templateName, uploadfile.getOriginalFilename(), directory, usercomments,SSO);
					

				}
				catch (AmazonServiceException ase)
				{
					log.error("Caught an AmazonServiceException, which" + " means your request made it " + "to Amazon S3, but was rejected with an error response" + " for some reason.");
					//log.error("Error Message:    " + ase.getMessage());
					//log.error("HTTP Status Code: " + ase.getStatusCode());
					//log.error("AWS Error Code:   " + ase.getErrorCode());
					//log.error("Error Type:       " + ase.getErrorType());
					log.error("Request ID:       " + ase.getRequestId());
					//message += uploadfile.getOriginalFilename() + ":error:" + ase.getMessage() + "\r\n";
				}
				catch (AmazonClientException ace)
				{
					//log.error("Caught an AmazonClientException, which means" + " the client encountered " + "an internal error while trying to " + "communicate with S3, " + "such as not being able to access the network.");
					log.error("Error Message: " + ace.getMessage());
					//message += uploadfile.getOriginalFilename() + ":error:" + ace.getMessage() + "\r\n";
				}
				catch (Exception e)
				{
					log.error("BlobStoreServiceImpl-->putDocumentOnblob : Error Message: " + e.getMessage());
					//message += uploadfile.getOriginalFilename() + ":error:" + e.getMessage() + "\r\n";
				}finally{
					try {
						stream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		log.info("BlobStoreServiceImpl-->putDocumentOnblob : End");
		return response;

	}	

	@Override
	public UploadResponseDTO putDocumentOnPostgres(String templateId, String uploadId,String fileName, String filePath){
	//	log.info("BlobStoreServiceImpl-->putDocumentOnPostgres : Start");
		UploadResponseDTO response= new UploadResponseDTO();

		//log.info("In putDocumentOnblob()1 " + folderDirectory);
	//	log.info("templateId---"+templateId);
		InputStream stream=null;
		try {
			//Get Excel Template
			List<Map<String,String>> li = getExcelTemplateData(templateId);
  
			//log.info("list--- get excel template :::"+li.toString());
	// stream = new FileInputStream("C:\\Users\\711048\\Downloads\\jpmc-deliveries-20170803.csv");//getFile(filePath,fileName);//Extract Excel Data as String into list

        stream = getFile(filePath,fileName);//Extract Excel Data as String into list
			
			ExtractExcelDTO extractExcelDTO = extractCsvData(stream,li,uploadId);
			
			//log.info("extractExcelDTO :::"+extractExcelDTO);

			List<String> queryList = extractExcelDTO.getExcelData();
			
			//log.info("queryList :::"+queryList.toString());

			//Insert extracted data into t_vm_vuln_kb table
			Map<String,String> resMap=null;
			if(queryList != null && queryList.size() > 0){
				resMap = insertExcelData(queryList);
			}else{
				resMap = new HashMap<String,String>();
				resMap.put("isException", Constants.RES_FLAG_Y);
				resMap.put("errorLog","");
			}

			resMap.put("errorLog", extractExcelDTO.getErrorLog()+resMap.get("errorLog"));
			//log.info("insertExcelData Response-->"+resMap);

			//update xls_upload table
			int updateRes = updateExcelUploadData(uploadId,templateId,resMap);
			//log.info("updateExcelUploadData Response-->"+updateRes);

			//update xls_upload table
			int updateRes1 = insertExcelUploadLoggerData(Integer.parseInt(uploadId),resMap.get("errorLog"));
			//log.info("insertExcelUploadLoggerData Response-->"+updateRes1);


			response.setStatus("File upload Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setStatus(e.getMessage());
			log.error("BlobStoreServiceImpl-->putDocumentOnPostgres : Error - "+e.getMessage());
			//e.printStackTrace();
		}finally{
			try {
				if(stream != null)
					stream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.error("BlobStoreServiceImpl-->putDocumentOnPostgres : finally : Error - "+e.getMessage());
			}
		}
		log.info("BlobStoreServiceImpl-->putDocumentOnPostgres : End");
		return response;
	}


	public ExtractExcelDTO extractCsvData(InputStream stream, List<Map<String,String>> templateDet,String uploadID){
	
		//log.info("BlobStoreServiceImpl-->extractCsvData : Start :::"+templateDet.toString());
		//List<HashMap> list=new ArrayList<HashMap>();
		List<String> list=new ArrayList<String>();
		ExtractExcelDTO extractExcelDTO= new ExtractExcelDTO();
		try {

			//Read all rows at once
			List<String[]> allRows= csvUtils.readCSV(stream);
			
			
			//int totRows =allRows.size()-1; 
			
			//log.info("NUMBER of Rows"+totRows);
			//NumberOfRows numberOfRows = new NumberOfRows();
			//numberOfRows.setNumberOfRows(totRows);
			//Read CSV line by line and use the string array as you want
			Iterator<String[]> itrRow=allRows.iterator();
			String[] row=null;
			String[] headerRow=null;

			StringBuffer query1=null;
			StringBuffer query2=null;
			Map<String,String> tempCol=templateDet.get(0); 
			Map<String,String> tempColTyp=templateDet.get(1);
			Map<String,String> xlsMastDet=templateDet.get(2);
			Map<String,String> xlsMastSchema=templateDet.get(3);
			
			//log.info("tempCol   "+tempCol.toString());

			//log.info("tempColTyp  "+tempColTyp.toString());

			//log.info("xlsMastDet  "+xlsMastDet.toString());

			String tableName=xlsMastDet.get("table_name");
			
			String tableSchema=xlsMastSchema.get("schema_name");
			
			//log.info("tableName :::"+tableName);

			//Writing a method to get the Upload count


		//	uploadCount= 1;//getUploadCount(tableName);
			
		//	log.info("uploadCount"+uploadCount);

			//Excel Header Row Validation
			//log.info("Map Values ----------->" + tempColTyp );
			String headerColValRes=validateCsvHeaderColumns(tempColTyp, (String[])allRows.get(0));
			
		//	log.info("headerColValRes  "+headerColValRes);
			StringBuffer sb=new StringBuffer();
			sb.append("===============================\n");
			sb.append("Error Code Details:\n100 - Date field is having wrong data\n101 - Integer field is having wrong data\n102 - Field is having wrong data\n103 - Row insertion error (SQL Error) \n104 - System Error\n");
			sb.append("===============================\n");
			int i=0,rowCount=1,colCount=0;
			Boolean numberExcp = false;
			Boolean dateExcp = false;
			Boolean otherExcp = false;
			int headerCount = 0;
			if(headerColValRes.equals(Constants.RES_FLAG_Y)){

				while(itrRow.hasNext()){
					if(i==0)
					{
						headerRow = (String[])itrRow.next();
						headerCount = headerRow.length;
						
					}
					try {
						i=1;
						if(numberExcp || dateExcp || otherExcp ==true){
						   // if any exception occur then same row
						}
						else{
							
						row = (String[])itrRow.next();
						for(int index=0;index<row.length;index++)
						{
							row[index]=row[index].replaceAll("\"", "");
						}
						
						} 
						
						//hmap= new HashMap<String,String>();
						query1=new StringBuffer();
						query2=new StringBuffer();
						query1.append("(");
						query2.append("(");
						//query1.append("(STAGE_SEQ_KEY,");
					//	query2.append("(nextval('staging.STAGE_SEQ_KEY_SEQ'),");
						//log.info("------------>"+Arrays.deepToString(row));
						
						
						// if any exception occur then we will continue the col count of same row
						if(numberExcp || dateExcp || otherExcp ==true){
							colCount++;
						}
						else
							{
							colCount=0;
							}
						numberExcp = false;
						dateExcp = false;
						otherExcp = false;
						for(int counter = colCount; counter<row.length ; counter++){
							
							String colVal = row[counter];
							if(colCount<headerCount){
							if(!colVal.trim().equals("") && tempColTyp.get(headerRow[colCount].trim()) != null){
								
							
								query1.append(tempCol.get(headerRow[colCount].trim())+",");
							
								if(tempColTyp.get(headerRow[colCount].trim()).equalsIgnoreCase("integer")){
									query2.append(Integer.parseInt(colVal.trim())+",");
								}else if(tempColTyp.get(headerRow[colCount].trim()).equalsIgnoreCase("date")){
									Utility.convertToDate(colVal.trim()).toString();
									query2.append("'"+colVal.replaceAll("'", "''")+"',");
								}else{
									query2.append("'"+(colVal.trim()).replaceAll("'", "''")+"',");
								}
								}
								
							}
							colCount++;
						}
						
						
						query1.append("IS_PROCESSED,FILE_SEQ_NUMBER,creation_date)");
						query2.append("'N',"+uploadID+",now())");
						//list.add(query1.toString().replace(",)", ")")+" values "+query2.toString().replace(",)", ")"));
						//log.info("query1  ::"+query1);
						//log.info("query2  ::"+query2);
						//log.info("Insert into ......"+Constants.DB_SCHEMA+tableName+query1.toString()+" values "+query2.toString());
						if(list.size()<rowCount){
						list.add("Insert into "+tableSchema+"."+tableName+query1.toString()+" values "+query2.toString());
						}
					}catch (NumberFormatException nfe){
						numberExcp = true;
						//list.add("");
						if(list.size()<rowCount){
							list.add("");
						}
						
						sb.append("["+(rowCount+1)+","+(colCount+1)+"]-"+Constants.ERR_INTG_CODE+"\n");
						
					}catch (NullPointerException ne){
						dateExcp = true;
						//list.add("");
						if(list.size()<rowCount){
							list.add("");
						}
						
						sb.append("["+(rowCount+1)+","+(colCount+1)+"]-"+Constants.ERR_DATE_CODE+"\n");
						
					} catch (Exception e) {
						otherExcp = true;
						//list.add("");
						if(list.size()<rowCount){
							list.add("");
						}
						
						sb.append("["+(rowCount+1)+","+(colCount+1)+"]-"+Constants.ERR_OTHR_CODE+"\n");
						//log.error("In extractCsvData(.) : Exception : " + e.getMessage());
						e.printStackTrace();
						
					}finally{

					}
					
					if(numberExcp || dateExcp == true){
						// do nothing row count will be same
					}
					else
					{
					rowCount++;
					}
					
				}
				extractExcelDTO.setErrorLog(sb.toString());
			}else{
				list=null;
				extractExcelDTO.setErrorLog(headerColValRes);
				extractExcelDTO.setStatus(Constants.RES_FLAG_N);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->extractCsvData : IOException : " + e.getMessage());
			list=null;
			extractExcelDTO.setStatus("N");
			//e.printStackTrace();			
		}		
		extractExcelDTO.setExcelData(list);
		log.info("BlobStoreServiceImpl-->extractCsvData : End");
		return extractExcelDTO;
	}	
	/*
	public String validateExcelHeaderColumns(Map<String,String> tempColTyp, XSSFRow row){
		log.info("BlobStoreServiceImpl-->validateExcelHeaderColumns : Start");	
		String valRes="Y";
		try {

			XSSFCell cell=null;
			Iterator itrCol=null;
			int succCount=0, temp=0, totCount=tempColTyp.keySet().size();
			StringBuffer sb=new StringBuffer();
			sb.append("The Below Columns are not available\n");
			for(String dbColType: tempColTyp.keySet()){
				itrCol=row.iterator();
				temp=0;
				while(itrCol.hasNext()){
					cell = (XSSFCell)itrCol.next();
					if(dbColType.equalsIgnoreCase(cell.toString().trim())){
						succCount++;
						temp=1;
					}
				}
				if(temp==0){
					sb.append(dbColType+"\n");
				}
			}
			if(succCount!=totCount){
				log.info("In validateExcelHeaderColumns(..)------->succCount"+succCount+", totCount--->"+totCount);
				valRes=sb.toString();
			}else if(totCount==0){
				log.info("In validateExcelHeaderColumns(..)------->totCount--->"+totCount);
				valRes="Template configuration is missing";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->validateExcelHeaderColumns : Exception : " + e.getMessage());
			valRes="Invalid Header Columns";
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->validateExcelHeaderColumns : End");
		return valRes;

	}	
	 */
	public String validateCsvHeaderColumns(Map<String,String> tempColTyp, String[] row){
		log.info("BlobStoreServiceImpl-->validateCsvHeaderColumns : Start");
		String valRes="Y";
		try {

			int succCount=0, temp=0, totCount=tempColTyp.keySet().size();
			StringBuffer sb=new StringBuffer();
			sb.append("The Below Columns are not available\n");
			for(String dbColType: tempColTyp.keySet()){
				temp=0;
				for(String colVal : row){
					if(dbColType.equalsIgnoreCase(colVal.trim())){
						//log.info("column :::"+colVal);
						//log.info("In validateExcelHeaderColumns(..)------->"+cell.toString());
						succCount++;
						temp=1;
					}
				}
				if(temp==0){
					sb.append(dbColType+"\n");
				}
			}
			if(succCount!=totCount){
				//log.info("In validateExcelHeaderColumns(..)------->succCount"+succCount+", totCount--->"+totCount);
				valRes=sb.toString();
			}else if(totCount==0){
				//log.info("In validateExcelHeaderColumns(..)------->totCount--->"+totCount);
				valRes="Template configuration is missing";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->validateCsvHeaderColumns : Exception : " + e.getMessage());
			valRes="Invalid Header Columns";
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->validateCsvHeaderColumns : End");		
		return valRes;

	}	
	public InputStream getFile(String directory, String filename) throws Exception
	{

		log.info("BlobStoreServiceImpl-->getFile : Start");
		String folderDirectory = directory + File.separator ;
		S3ObjectInputStream objectContent = null;
		try
		{
			JSONObject credentials = getCredentials("VCAP_SERVICES", "predix-blobstore", "credentials");
			AmazonS3 s3client = getS3Client(credentials);
			log.info("In getFile1 : >>>"+credentials);

			String s3_bucket_name = getBucketName(credentials);

			S3Object object = s3client.getObject(new GetObjectRequest(s3_bucket_name, folderDirectory + filename));
			log.info("s3client.getObject");
			// Set the content type and attachment header.
			//log.info("In getFile1 : >>>"+s3_bucket_name);

			objectContent = object.getObjectContent();

			//log.info("In getFile1.1 : >>>"+objectContent);
			//log.info("In getFile1.1 : >>>"+objectContent.available());



		}
		catch (AmazonServiceException ase)
		{
			log.error("BlobStoreServiceImpl -->getFile Exception : " + ase.getMessage());
			//ase.printStackTrace();
		}
		catch (AmazonClientException ace)
		{
			log.error("BlobStoreServiceImpl-->getFile Exception : " + ace.getMessage());
			ace.printStackTrace();
		}
		catch (Exception e)
		{
			log.error("BlobStoreServiceImpl-->getFile Exception : " + e.getMessage());
			e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->getFile : End");
		return objectContent;
	}
	private JSONObject getCredentials(String envVarName, String envObj, String subObj)
	{
		//log.info("BlobStoreServiceImpl-->getCredentials : Start");

		JSONObject vcap_services = null;
		JSONObject credentials = null;

		try
		{
			String vcap_services_string = System.getenv(envVarName);
			//String vcap_services_string =" {'predix-blobstore': [{'credentials': {'access_key_id':'420eca10-2433-4ecf-871b-bfacbbc01b49-1','bucket_name':'bucket-420eca10-2433-4ecf-871b-bfacbbc01b49','host': 'store.gecis.io','secret_access_key': '59809290-a8b4-4708-9510-cf14fc66e010','url': 'https://bucket-420eca10-2433-4ecf-871b-bfacbbc01b49.store.gecis.io'},'label': 'predix-blobstore','name':'myBlob','plan':'Tiered','provider':null,'syslog_drain_url':null,'tags':['blobstore','blob-store','blob store']}]}";
			//log.info("In getCredentials()1 : "+vcap_services_string);
			if (vcap_services_string != null)
			{
				//log.info("In getCredentials()2 : ");
				vcap_services = new JSONObject(vcap_services_string);

				JSONArray predix_blobstore = vcap_services.getJSONArray(envObj);
				//log.info("In getCredentials()3 : "+predix_blobstore);
				if (predix_blobstore != null)
				{
					//log.info("In getCredentials()4 : ");

					credentials = predix_blobstore.getJSONObject(0).getJSONObject(subObj);

					//log.info("In getCredentials()5 : "+credentials);

				}
				else
				{
				//	log.error(envObj + " not bound to this application.  No environment variable found.");
				}
			}
			else
			{
				//log.error("No " + envVarName + " environment variable.");
			}

		}
		catch (AmazonServiceException ase)
		{
			log.error("BlobStoreServiceImpl-->getCredentials : Exception : " + ase.getMessage());
			//ase.printStackTrace();
		}
		catch (AmazonClientException ace)
		{
			log.error("BlobStoreServiceImpl-->getCredentials : Exception : " + ace.getMessage());
			//ace.printStackTrace();
		}
		catch (Exception e)
		{
			log.error("BlobStoreServiceImpl-->getCredentials : Exception : " + e.getMessage());
			e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->getCredentials : End");
		return (credentials);

	}

	private AmazonS3 getS3Client(JSONObject credentials)
	{
		log.info("BlobStoreServiceImpl-->getS3Client : Start");
		AmazonS3 s3client = null;

		try
		{
			//log.info("In getS3Client()1 : ");
			ClientConfiguration config = new ClientConfiguration();
			config.setProtocol(Protocol.HTTPS);
			@SuppressWarnings("deprecation")
			S3ClientOptions options = new S3ClientOptions();
			config.setSignerOverride("S3SignerType"); //$NON-NLS-1$
			BasicAWSCredentials creds = new BasicAWSCredentials(credentials.getString("access_key_id"), credentials.getString("secret_access_key"));
			//log.info("BasicAWSCredentials awsCredentials"+creds);
			s3client = new AmazonS3Client(creds, config);
			//log.info("new AmazonS3Client(creds)");
			String endpoint = credentials.getString("url");
			endpoint = endpoint.substring(endpoint.indexOf(".") + 1);
			//System.out.println("endpoint = " + endpoint);
			s3client.setEndpoint(endpoint);
			s3client.setS3ClientOptions(options);

			//log.info("In getS3Client()2 : "+s3client);
			//log.info("In getS3Client() Sig Type : "+config.getSignerOverride());
		}
		catch (AmazonServiceException ase)
		{
			log.error("BlobStoreServiceImpl-->getS3Client : Exception : " + ase.getMessage());
			//ase.printStackTrace();
		}
		catch (AmazonClientException ace)
		{
			log.error("BlobStoreServiceImpl-->getS3Client : Exception : " + ace.getMessage());
			//ace.printStackTrace();
		}
		catch (Exception e)
		{
			log.error("BlobStoreServiceImpl-->getS3Client : Exception : " + e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->getS3Client : End");
		return (s3client);

	}

	private String getBucketName(JSONObject credentials)
	{
		log.info("BlobStoreServiceImpl-->getBucketName : Start");
		String rest_endpoint = "";
		String s3_bucket_name = "";
		try
		{
			//log.info("In getBucketName()1 : ");
			rest_endpoint = credentials.getString("url");
			String s3_bucket_name_partial = rest_endpoint.substring(rest_endpoint.indexOf("//") + 2);
			s3_bucket_name = s3_bucket_name_partial.substring(0, s3_bucket_name_partial.indexOf("."));
			//log.info("In getBucketName()2 : "+rest_endpoint);
			//log.info("In getBucketName()3 : "+s3_bucket_name_partial);
			//log.info("In getBucketName()4 : "+s3_bucket_name);
		}
		catch (JSONException e)
		{
			log.error("BlobStoreServiceImpl-->getBucketName : Exception : " + e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->getBucketName : End");
		return (s3_bucket_name);
	}
	
	@Override
	public String getUploadDetails() {
		return userDao.getUploadDetails();
	}
	@Override
	public String getLoggerData(String uploadId){
		//log.info("GetLoggerData"+userDao.getLoggerData(uploadId));
		return userDao.getLoggerData(uploadId);
	}
	@Override
	public String getTemplateDetails(){
		return userDao.getTemplateDetails();
	}
	
	
	@Override
	public void insertMasterDataFromStagingTable() {
		log.info("BlobStoreServiceImpl-->insertMasterDataFromStagingTable : Start");

		try {
			userDao.insertMasterDataFromStagingTable();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->insertMasterDataFromStagingTable : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->insertMasterDataFromStagingTable : End");
		
	}
	
	@Override
	public void insertValidDataToValidStageTable() {
		log.info("BlobStoreServiceImpl-->insertValidDataToValidStageTable : Start");

		try {
			userDao.insertValidDataToValidStageTable();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->insertValidDataToValidStageTable : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->insertValidDataToValidStageTable : End");
	}	
	
	
	@Override
	public void processSAPShipmentData() {
		log.info("BlobStoreServiceImpl-->processSAPShipmentData : Start");

		try {
			userDao.processSAPShipmentData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processSAPShipmentData : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processSAPShipmentData : End");
		
	}
	
	@Override
	public void processCoreShipmentData() {
		log.info("BlobStoreServiceImpl-->processCoreShipmentData : Start");

		try {
			userDao.processCoreShipmentData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processCoreShipmentData : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processCoreShipmentData : End");
		
	}
	
	
	@Override
	public void processServiceProviderReceiptData() {
		log.info("BlobStoreServiceImpl-->processServiceProviderReceiptData : Start");

		try {
			userDao.processServiceProviderReceiptData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processServiceProviderReceiptData : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processServiceProviderReceiptData : End");
		
	}
	@Override
	public void processServiceProviderIssuedData() {
		log.info("BlobStoreServiceImpl-->processServiceProviderReceiptData : Start");

		try {
			userDao.processServiceProviderIssuedData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processServiceProviderIssuedData : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processServiceProviderIssuedData : End");
		
	}
	@Override
	public void processServiceProviderDemandData() {
		log.info("BlobStoreServiceImpl-->processServiceProviderDemandData : Start");

		try {
			userDao.processServiceProviderDemandData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processServiceProviderDemandData : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processServiceProviderDemandData : End");
		
		
	}
	@Override
	public void processCustomerInstallDemandData() {
		log.info("BlobStoreServiceImpl-->processCustomerInstallDemandData : Start");

		try {
			userDao.processCustomerInstallDemandData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processCustomerInstallDemandData : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processCustomerInstallDemandData : End");
		
		
	}
	@Override
	public void processCustomerInstallCertificateData() {
		log.info("BlobStoreServiceImpl-->processCustomerInstallCertificateData : Start");

		try {
			userDao.processCustomerInstallCertificateData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processCustomerInstallCertificateData : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processCustomerInstallCertificateData : End");
		
	}
	@Override
	public void processRefreshAllViews() {
		log.info("BlobStoreServiceImpl-->processRefreshAllViews : Start");

		try {
			userDao.processRefreshAllViews();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processRefreshAllViews : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processRefreshAllViews : End");
		
		
	}
	@Override
	public void processFSGConsumptionData() {
		log.info("BlobStoreServiceImpl-->processFSGConsumptionData : Start");

		try {
			userDao.processFSGConsumptionData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processFSGConsumptionData : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processFSGConsumptionData : End");
		
		
	}
	@Override
	public void processRefreshAllViewsOnDemand() {
		log.info("BlobStoreServiceImpl-->processRefreshAllViewsOnDemand : Start");

		try {
			userDao.processRefreshAllViewsOnDemand();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->processRefreshAllViewsOnDemand : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->processRefreshAllViews : End");
		
		
	}
	@Override
	public void initiateProcessOnDemand(String sso) {
		java.util.Date today = new java.util.Date();
		ProcessOnDemandEntity demandEntity = new ProcessOnDemandEntity();
		demandEntity.setCreatedBy(sso);
		demandEntity.setCreatedDtm(today);
		demandEntity.setIsProcessTriggered("Y");
		processOnDemandRepo.save(demandEntity);
	}
	
	@Override
	public int getProcessOnDemandRequest() {
		int count = 0;
		
		count = processOnDemandRepo.getIsProcessedOnDemand();
		return count;
	}
	@Override
	public List<ProcessOnDemandEntity> getListProcessedOnDemand() {
		
		List<ProcessOnDemandEntity> demandEntities = new ArrayList<ProcessOnDemandEntity>();
		demandEntities = processOnDemandRepo.getListProcessedOnDemand();
		return demandEntities;
	}
	
	
	@Override
	public void setListProcessOnDemand() {
		log.info("setListProcessOnDemand start");
		List<ProcessOnDemandEntity> entities =  new ArrayList<ProcessOnDemandEntity>();
		
		 int count = 0;
		count = getProcessOnDemandRequest();
		log.info("No of records  "+count);
		
		if(count>0)
		{
			entities =getListProcessedOnDemand();
			log.info("Get Entities  "+entities.size());
			for (ProcessOnDemandEntity entity : entities) {
				java.util.Date today = new java.util.Date();
				ProcessOnDemandEntity demandEntity = new ProcessOnDemandEntity();
				log.info("executeJobOnDemand Start");
				batchController.executeJobOnDemand();//error
				log.info("executeJobOnDemand End");
			demandEntity.setIsProcessTriggered("N");
			demandEntity.setCreatedBy(entity.getCreatedBy());
			demandEntity.setProcess_id(entity.getProcess_id());
			demandEntity.setCreatedDtm(today);
			log.info("Save to N");
			int row = 0;
			/*try{
				jdbcTemplate.update(QueriesDiv.Update_Processed_Demand, new Object[] {entity.getProcess_id()});
				
			}catch(Exception e){
				log.error("BlobStoreDaoImpl-->updateExcelUploadData : Error - "+e.getMessage());
				e.printStackTrace();
			}*/
			processOnDemandRepo. save(demandEntity);
			log.info("Save to N is complete");
			}
		}
	}
	@Override
	public ProcessOnDemandStatusDTO enableProcessOnDemand() {
		ProcessOnDemandStatusDTO demandStatusDTO = new ProcessOnDemandStatusDTO();
		int count = 0;
		
		count = processOnDemandRepo.getIsProcessedOnDemand();
		if(count>0){
			demandStatusDTO.setStatus("Y");
		}
		else{
			demandStatusDTO.setStatus("N");
		}
		return demandStatusDTO;
	}
	@Override
	public void callLatLongService() {
		log.info("BlobStoreServiceImpl-->callLatLongService : Start");

		try {
			userDao.callLatLongService();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->callLatLongService : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->callLatLongService : End");
		
	}
	@Override
	public void callFSGLatLongService() {
		log.info("BlobStoreServiceImpl-->callFSGLatLongService : Start");

		try {
			userDao.callFSGLatLongService();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->callFSGLatLongService : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->callFSGLatLongService : End");
		
		
	}
	
	   @Override
public void processInstallerReceivedData() {
	log.info("BlobStoreServiceImpl-->processInstallerReceivedData : Start");

	try {
		userDao.processInstallerReceivedData();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		log.error("BlobStoreServiceImpl-->processInstallerReceivedData : Error - "+e.getMessage());
		//e.printStackTrace();
	}
	log.info("BlobStoreServiceImpl-->processInstallerReceivedData : End");
	
	   }
	@Override
	public void updateConsumptionData() {
		log.info("BlobStoreServiceImpl-->updateConsumptionData : Start");

		try {
			userDao.updateConsumptionData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("BlobStoreServiceImpl-->updateConsumptionData : Error - "+e.getMessage());
			//e.printStackTrace();
		}
		log.info("BlobStoreServiceImpl-->updateConsumptionData : End");
				
	}
	
}
