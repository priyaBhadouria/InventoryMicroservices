package com.ge.current.div.blob.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ge.current.div.blob.Messages;
import com.ge.current.div.blob.common.utility.Constants;
import com.ge.current.div.blob.common.utility.QueriesDiv;
import com.ge.current.div.blob.entity.ExcelUpload;
import com.ge.current.div.blob.dao.BlobStoreDao;




@Repository
public class BlobStoreDaoImpl implements BlobStoreDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	Connection conn = null;
	CallableStatement callableStatement = null;
	
	
	private Logger log = LoggerFactory.getLogger(BlobStoreDaoImpl.class);
	
	public Map<String,String> insertExcelData(List<String> list){
		log.info("BlobStoreDaoImpl-->insertExcelData : Start");
		Map<String,String> hmap = new HashMap<String,String>();
		StringBuffer errorRowIds=new StringBuffer();
		int succRowCount=0,rowCount=0; 
		
		int totRowCount=list.size();
		try{
			Iterator<String> itr=list.iterator();
			int row = 0;
			while(itr.hasNext()){
				rowCount++;
				try {
					String subQuery=(String)itr.next();
					if(!subQuery.equals("")){
						row = jdbcTemplate.update(subQuery);
						//jdbcTemplate.update("COMMIT;");
						if(row > 0){
							succRowCount++;
						}else{
							errorRowIds.append("["+rowCount+",0]-"+Constants.ERR_INSERT_CODE+"\n");
						}
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorRowIds.append("["+rowCount+",0]-"+Constants.ERR_INSERT_CODE+"\n");
					//log.error("BlobStoreDaoImpl-->insertExcelData : Error - "+e.getMessage());
					//e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("BlobStoreDaoImpl-->insertExcelData : Error - "+e.getMessage());
		}
		hmap.put("totalRowCount", totRowCount+"");
		hmap.put("successRowCount", succRowCount+"");
		hmap.put("failedRowCount", list.size()-succRowCount+"");
		if(succRowCount != totRowCount)
			hmap.put("errorLog", errorRowIds.toString());
		else
			hmap.put("errorLog", "File Processed Successfully");
		hmap.put("isException", Constants.RES_FLAG_N);
		log.info("BlobStoreDaoImpl-->insertExcelData : End");
		return hmap;
	}
	public int insertExcelUploadData(String templateName,String fileName,String filePath,String usercomments,String SSO){
		log.info("BlobStoreDaoImpl-->insertExcelUploadData : Start");
		int row = 0;
		int tempId = 0;
		try{
			tempId= getExcelTemplateID(templateName);
			row = jdbcTemplate.update(QueriesDiv.Insert_Excel_Upload_Data, new Object[] {tempId,fileName,filePath,usercomments,SSO,SSO});
		}catch(Exception e){
			e.printStackTrace();
			log.error("BlobStoreDaoImpl-->insertExcelUploadData : Error - "+e.getMessage());
		}	
		log.info("BlobStoreDaoImpl-->insertExcelUploadData : End");
		return row;
	}
	
	

	
	public int getExcelTemplateID(String templateName){
		log.info("BlobStoreDaoImpl-->getExcelTemplateID : Start");
		return jdbcTemplate.query(QueriesDiv.get_File_Template,new Object[] { templateName }, new ResultSetExtractor<Integer>(){

		      public Integer extractData(ResultSet rs) throws SQLException {
		    	  int tempID= 0;
		    	 while(rs.next()){  
		    		 tempID = rs.getInt("xls_id");
		    	 }  
		    	 log.info("BlobStoreDaoImpl-->getExcelTemplateID : End");
		        return tempID;
		      }
	    });
		
	};	
	
	
	public int updateExcelUploadData(String uploadId,String xlsId,Map<String,String> errResLogMap){
		log.info("BlobStoreDaoImpl-->updateExcelUploadData : Start");
		int row = 0;
		try{
			if(errResLogMap.get("isException").equals(Constants.RES_FLAG_N)){
				row = jdbcTemplate.update(QueriesDiv.Update_Excel_Upload_Data_Err, new Object[] {Integer.parseInt(errResLogMap.get("totalRowCount")),Integer.parseInt(errResLogMap.get("successRowCount")),Integer.parseInt(errResLogMap.get("failedRowCount")),Integer.parseInt(uploadId),Integer.parseInt(xlsId)});
			}else{
				row = jdbcTemplate.update(QueriesDiv.Update_Excel_Upload_Data, new Object[] {Integer.parseInt(uploadId),Integer.parseInt(xlsId)});
			}
		}catch(Exception e){
			log.error("BlobStoreDaoImpl-->updateExcelUploadData : Error - "+e.getMessage());
			e.printStackTrace();
		}	
		log.info("BlobStoreDaoImpl-->updateExcelUploadData : End");
		return row;
	}
	
	public List<Map<String,String>> getExcelTemplateData(String templateId){
		return jdbcTemplate.query(QueriesDiv.Get_Excel_Template_Data+templateId, new ResultSetExtractor<List<Map<String,String>>>(){

		      public List<Map<String,String>> extractData(ResultSet rs) throws SQLException {
		    	  Map<String,String> hmapColumn=new HashMap<String,String>();  
		    	  Map<String,String> hmapType=new HashMap<String,String>();
		    	  Map<String,String> xlsMastDet=new HashMap<String,String>();
		    	  Map<String,String> xlsMastSchema=new HashMap<String,String>();
		    	  List<Map<String,String>> li= new ArrayList<Map<String,String>>();
		    	  
		    	  
		    	 while(rs.next()){  
		    		 hmapColumn.put(rs.getString("input_header_text").trim(), rs.getString("db_column_name").trim());
		    		 hmapType.put(rs.getString("input_header_text").trim(), rs.getString("data_type_postgres").trim());
		    		 xlsMastDet.put("table_name", rs.getString("db_table_name"));
		    		 xlsMastSchema.put("schema_name", rs.getString("schema_name"));
			        }  
			        li.add(hmapColumn);
			        li.add(hmapType);
			        li.add(xlsMastDet);
			        li.add(xlsMastSchema);
			        return li;
		      }
	    });
	};
	public int insertExcelUploadLoggerData(int uploadId,String errLog){
		log.info("BlobStoreDaoImpl-->insertExcelUploadLoggerData : Start");
		int row = 0;
		try{
			String query = "Insert into staging.logger (upload_or_run_id,log,create_dtm) values (?,?,now())";
			//log.info("error ::::::"+uploadId+errLog);
			row = jdbcTemplate.update(query, new Object[] {uploadId,errLog});
		}catch(Exception e){
			log.error("BlobStoreDaoImpl-->insertExcelUploadLoggerData : Error - "+e.getMessage());
			e.printStackTrace();
		}	
		log.info("BlobStoreDaoImpl-->insertExcelUploadLoggerData : End");
		return row;
	}
	public int getUploadCount(String tableName){
		log.info("BlobStoreDaoImpl-->getUploadCount : Start");
		return jdbcTemplate.query(QueriesDiv.Get_File_Seq_Num+tableName, new ResultSetExtractor<Integer>(){

		      public Integer extractData(ResultSet rs) throws SQLException {
		    	  int uploadCount= 1;
		    	 while(rs.next()){  
		    		 uploadCount = rs.getInt("upload_count");
		    	 }  
		    	 log.info("BlobStoreDaoImpl-->getUploadCount : End");
		        return uploadCount;
		      }
	    });
		
	};	
	public List<ExcelUpload> getExcelUploadDetails(){
		log.info("BlobStoreDaoImpl-->getExcelUploadDetails : Start");
		return jdbcTemplate.query(QueriesDiv.Get_Excel_Upload_Details, new ResultSetExtractor<List<ExcelUpload>>(){

		      public List<ExcelUpload> extractData(ResultSet rs) throws SQLException {
		    	  List<ExcelUpload> li= new ArrayList<ExcelUpload>();
		    	  ExcelUpload xlsUpload= null;
		    	  
		    	 while(rs.next()){  
		    		 xlsUpload = new ExcelUpload();
		    		 xlsUpload.setFileName(rs.getString("file_name"));
		    		 xlsUpload.setCsvBlobStoreLocation(rs.getString("csv_blob_store_location"));
		    		 xlsUpload.setId(rs.getInt("upload_id"));
		    		 xlsUpload.setRequestTypeId(rs.getInt("xls_id"));
				     li.add(xlsUpload);
			        }
		    	 log.info("BlobStoreDaoImpl-->getExcelUploadDetails : End");
			        return li;
		      }
	    });
		
	};	
	
	public String getLoggerData(String uploadId){
		return jdbcTemplate.query("select logger.upload_or_run_id,logger.log from staging.logger logger where upload_or_run_id = "+uploadId, new ResultSetExtractor<String>(){

		      public String extractData(ResultSet rs) throws SQLException {
		    	  new ArrayList<Map<String,String>>();
		    	  StringBuffer sb=new StringBuffer("[");
		    	 while(rs.next()){  
		    		 sb.append("{\"upload_or_run_id\":"+rs.getString("upload_or_run_id")
		    				+",\"log\":\""+rs.getString("log").replaceAll("[\\t\\n\\r]"," ").replaceAll("\"", "\'")
		    				 +"\"},");
		    		 
			     }  
		    	  /*sb.append("{\"upload_or_run_id\":"+391
		    				+",\"log\":\""+ "The Below Columns are not available CustPO# Receiver Zip Receiver Add Addr of Issuer Receiver St Issuer Zip Receiver Nm ReceiptDt FSG# Issuer St Receiver City Date Desc Name Of Issuer Rcv Fm Country GE PRO# Mtl# QtyShipped City of Issuer QtyRcv Receiver Ctry"
		    				 +"\"},");*/
		    	  
		    	 sb.append("]");
		    	 log.info("BlobStoreDaoImpl-->getTemplateDetails : End");
			     return sb.toString().replace("},]", "}]");
		      }
	    });
		
	};	
	public String getTemplateDetails(){
		log.info("BlobStoreDaoImpl-->getTemplateDetails : Start");
		return jdbcTemplate.query(QueriesDiv.Get_Template_Details, new ResultSetExtractor<String>(){

		      public String extractData(ResultSet rs) throws SQLException {
		    	  new ArrayList<Map<String,String>>();
		    	  StringBuffer sb=new StringBuffer("[");
		    	  
		    	 while(rs.next()){  
		    		 sb.append("{\"xls_id\":"+rs.getString("xls_id")
		    				+",\"xls_name\":\""+rs.getString("xls_name")+"\""
		    				+",\"xls_desc\":\""+rs.getString("xls_desc")
		    				 +"\"},");
		    		 
		    		// sb.append("{\"xls_id\":"+rs.getString("xls_id")+",\"xls_name\":\""+rs.getString("xls_name")+"\"},");
			     }  
		    	 sb.append("]");
		    	 log.info("BlobStoreDaoImpl-->getTemplateDetails : End");
			     return sb.toString().replace("},]", "}]");
		      }
	    });
		
	};	
	
	public String getUploadDetails() {
		log.info("BlobStoreDaoImpl-->getUploadDetails : Start");
		return jdbcTemplate.query(QueriesDiv.Get_Upload_Details,
				new ResultSetExtractor<String>() {

					public String extractData(ResultSet rs) throws SQLException {
						new ArrayList<Map<String, String>>();
						StringBuffer sb = new StringBuffer("[");

						while (rs.next()) {
                            sb.append("{\"upload_id\":" + rs.getString("upload_id")
                            +",\"xls_id\":" + rs.getString("xls_id")
                            +",\"file_name\":\""+ rs.getString("file_name")+"\""
                            +",\"csv_blob_store_location\":\""+ rs.getString("csv_blob_store_location")+"\""
                            +",\"row_passed\":" + rs.getString("row_passed")
                            +",\"row_failed\":" + rs.getString("row_failed")
                            +",\"total_rows\":" + rs.getString("total_rows")
                            +",\"user_comment\":\""+ rs.getString("user_comment")+"\""
                            +",\"db_processed\":\""+ rs.getString("db_processed")+"\""
                            +",\"created_by\":\""+ rs.getString("created_by")+"\""
                            +",\"create_dtm\":\""+ rs.getString("create_dtm")
                            +"\"},");
						}
						sb.append("]");
						log.info("BlobStoreDaoImpl-->getUploadDetails : End");
						return sb.toString().replace("},]", "}]");
					}
				});

	};

	public void insertMasterDataFromStagingTable(){
		
		  try {
              conn =jdbcTemplate.getDataSource().getConnection();
              callableStatement = conn.prepareCall("{call staging.insertMasterDataFromSAP()}");
              callableStatement.execute();
              callableStatement = conn.prepareCall("{call staging.insertMasterDataFromFSGReceipt()}");
              callableStatement.execute();
              callableStatement = conn.prepareCall("{call staging.insertMasterDataFromFSGIssue()}");
              callableStatement.execute();
        } catch (SQLException e) {
              e.printStackTrace();
        }
        finally{
        try{
            if(callableStatement != null) callableStatement.close();
            if(conn != null) conn.close();
        } catch(Exception ex){}
    }
	};
	
	
	public void insertValidDataToValidStageTable(){
		   try {
               conn =jdbcTemplate.getDataSource().getConnection();
               callableStatement = conn.prepareCall("{call staging.insertValidSAPToValidStage()}");
               callableStatement.execute();
               callableStatement = conn.prepareCall("{call staging.insertValidFSGReciptToValidStage()}");
               callableStatement.execute();
               callableStatement = conn.prepareCall("{call staging.insertValidFSGIssuedToValidStage()}");
               callableStatement.execute();
         } catch (SQLException e) {
               e.printStackTrace();
         }
         finally{
         try{
             if(callableStatement != null) callableStatement.close();
             if(conn != null) conn.close();
         } catch(Exception ex){}
     }

	};
	
	
	
	public void processSAPShipmentData(){
		   try {
            conn =jdbcTemplate.getDataSource().getConnection();
            callableStatement = conn.prepareCall("{call staging.insertMasterDataPlantFromSAP()}");
            callableStatement.execute();
            callableStatement = conn.prepareCall("{call staging.insertMasterDataUnitOfMeasureFromSAP()}");
            callableStatement.execute();
            callableStatement = conn.prepareCall("{call staging.insertMasterDataMaterialFromSAP()}");
            callableStatement.execute();
            callableStatement = conn.prepareCall("{call staging.insertMasterDataStorageLocationFromSAP()}");
            
            callableStatement.execute();
            callableStatement = conn.prepareCall("{call staging.insertValidSAPToValidStage()}");
            callableStatement.execute();
            callableStatement = conn.prepareCall("{call analytics.saveupdate_analytics_sap()}");
            callableStatement.execute();
      } catch (SQLException e) {
            e.printStackTrace();
      }
      finally{
      try{
          if(callableStatement != null) callableStatement.close();
          if(conn != null) conn.close();
      } catch(Exception ex){}
  }

	};
	
	
	public void processCoreShipmentData(){
		   try {

         conn =jdbcTemplate.getDataSource().getConnection();
         callableStatement = conn.prepareCall("{call staging.masterPlantFromCoreShipment()}");
         callableStatement.execute();
         callableStatement = conn.prepareCall("{call staging.insertMasterDataUnitOfMeasureFromCore()}");
         callableStatement.execute();
         callableStatement = conn.prepareCall("{call staging.masterMaterialFromPurchasOrder()}");
         callableStatement.execute();
         callableStatement = conn.prepareCall("{call staging.insertValidCoreToValidStage()}");
         callableStatement.execute();
         callableStatement = conn.prepareCall("{call analytics.saveupdate_analytics_sap()}");
         callableStatement.execute();
   } catch (SQLException e) {
         e.printStackTrace();
   }
   finally{
   try{
       if(callableStatement != null) callableStatement.close();
       if(conn != null) conn.close();
   } catch(Exception ex){}
}

	};
	
	
	public void processServiceProviderReceiptData(){
		   try {

      conn =jdbcTemplate.getDataSource().getConnection();
      callableStatement = conn.prepareCall("{call staging.insertMasterDataFromFSGReceipt()}");
      callableStatement.execute();
      callableStatement = conn.prepareCall("{call staging.insertValidFSGReciptToValidStage()}");
      callableStatement.execute();
      callableStatement = conn.prepareCall("{call analytics.saveupdate_analytics_fsg_receipt()}");
      callableStatement.execute();
} catch (SQLException e) {
      e.printStackTrace();
}
finally{
try{
    if(callableStatement != null) callableStatement.close();
    if(conn != null) conn.close();
} catch(Exception ex){}
}

	};
	@Override
	public void processServiceProviderIssuedData() {
		   try {

			      conn =jdbcTemplate.getDataSource().getConnection();
			      callableStatement = conn.prepareCall("{call staging.insertMasterDataFromFSGIssue()}");
			      callableStatement.execute();
			      callableStatement = conn.prepareCall("{call staging.insertValidFSGIssuedToValidStage()}");
			      callableStatement.execute();
			      callableStatement = conn.prepareCall("{call analytics.saveupdate_analytics_fsg_issue()}");
			      callableStatement.execute();
			} catch (SQLException e) {
			      e.printStackTrace();
			}
			finally{
			try{
			    if(callableStatement != null) callableStatement.close();
			    if(conn != null) conn.close();
			} catch(Exception ex){}
			}
		
	};
	@Override
	public void processServiceProviderDemandData() {
		   try {

			      conn =jdbcTemplate.getDataSource().getConnection();
			      callableStatement = conn.prepareCall("{call staging.insertMasterDataFromDemand()}");
			      callableStatement.execute();
			      callableStatement = conn.prepareCall("{call staging.insertValidDemandToValidStage()}");
			      callableStatement.execute();
			} catch (SQLException e) {
			      e.printStackTrace();
			}
			finally{
			try{
			    if(callableStatement != null) callableStatement.close();
			    if(conn != null) conn.close();
			} catch(Exception ex){}
			}
		
	};
	@Override
	public void processCustomerInstallDemandData() {
		 try {

		      conn =jdbcTemplate.getDataSource().getConnection();
		      callableStatement = conn.prepareCall("{call staging.insertmasterdatacustomer()}");
		      callableStatement.execute();
		      callableStatement = conn.prepareCall("{call   staging.insertMasterDataCustomerFromCustomerDemand()}");
		      callableStatement.execute();
		      callableStatement = conn.prepareCall("{call staging.insertmasterprojectdemand()}");
		      callableStatement.execute();
		} catch (SQLException e) {
		      e.printStackTrace();
		}
		finally{
		try{
		    if(callableStatement != null) callableStatement.close();
		    if(conn != null) conn.close();
		} catch(Exception ex){}
		}
		
	};
	@Override
	public void processCustomerInstallCertificateData() {
		try {
			
		      conn =jdbcTemplate.getDataSource().getConnection();		      
		      callableStatement = conn.prepareCall("{call analytics.saveupdate_analytics_certificate()}");
		      callableStatement.execute();
		} catch (SQLException e) {
		      e.printStackTrace();
		}
		finally{
		try{
		    if(callableStatement != null) callableStatement.close();
		    if(conn != null) conn.close();
		} catch(Exception ex){}
		}
		
	};
	@Override
	public void processFSGConsumptionData() {
		try {

		      conn =jdbcTemplate.getDataSource().getConnection();
		      callableStatement = conn.prepareCall("{call analytics.save_FSG_actual_demand_stock()}");
		      callableStatement.execute();
		} catch (SQLException e) {
		      e.printStackTrace();
		}
		finally{
		try{
		    if(callableStatement != null) callableStatement.close();
		    if(conn != null) conn.close();
		} catch(Exception ex){}
		}
		
	};
	
	@Override
	public void processRefreshAllViews() {
		try {

		      conn =jdbcTemplate.getDataSource().getConnection();
		      callableStatement = conn.prepareCall("{call analytics.callAllMaterializedViews()}");
		      callableStatement.execute();
		} catch (SQLException e) {
		      e.printStackTrace();
		}
		finally{
		try{
		    if(callableStatement != null) callableStatement.close();
		    if(conn != null) conn.close();
		} catch(Exception ex){}
		}
		
		
	}
	@Override
	public void processRefreshAllViewsOnDemand() {
		try {

		      conn =jdbcTemplate.getDataSource().getConnection();
		      callableStatement = conn.prepareCall("{call analytics.callallmaterializedviewsOnDemand()}");
		      callableStatement.execute();
		} catch (SQLException e) {
		      e.printStackTrace();
		}
		finally{
		try{
		    if(callableStatement != null) callableStatement.close();
		    if(conn != null) conn.close();
		} catch(Exception ex){}
		}
		
		
	}
	  private String getRequest(String url) throws Exception {

	         URL obj = new URL(url);
	         HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	        con.setRequestMethod("GET");

	        con.connect();
	        if (con.getResponseCode() != 200) {
	            return null;
	        }

	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        
	        in.close();
	        	con.disconnect();
	        return response.toString();
	    }
	    
	@Override
	public void callLatLongService() {
		String queryResult = "";
		 try {
			 
			 queryResult=getRequest(Messages.getString("LatLongURL")); // new 
			 System.out.println("LatLongUrl---"+queryResult);

	           // queryResult = getRequest("https://qa-gecurrent-div-services.run.aws-usw02-pr.ice.predix.io/div/heatmap/insertlatlong");
	            conn =jdbcTemplate.getDataSource().getConnection();
			      callableStatement = conn.prepareCall("{call analytics.HeatMapLandingPageView()}");
			      callableStatement.execute();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		 finally{
				try{
				    if(callableStatement != null) callableStatement.close();
				    if(conn != null) conn.close();
				} catch(Exception ex){}
				}
	     
	}
	@Override
	public void callFSGLatLongService() {
		String queryResult = "";
		 try {

			 queryResult=getRequest(Messages.getString("FSGlatLongUrl")); // new 
			 System.out.println("FSGlatLongUrl---"+Messages.getString("FSGlatLongUrl"));

	            conn =jdbcTemplate.getDataSource().getConnection();
			      callableStatement = conn.prepareCall("{call analytics.HeatMapFSGAddressDetailsView()}");
			      callableStatement.execute();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		 finally{
				try{
				    if(callableStatement != null) callableStatement.close();
				    if(conn != null) conn.close();
				} catch(Exception ex){}
				}
		
	}
	
	

@Override
	public void processInstallerReceivedData() {
		
		 try {

		      conn =jdbcTemplate.getDataSource().getConnection();
		      callableStatement = conn.prepareCall("{call staging.insertinstallerReceivedtovalidstage()}");
		      callableStatement.execute();
		      callableStatement = conn.prepareCall("{call analytics.saveupdate_analytics_installer_stage()}");
		      callableStatement.execute();
		} catch (SQLException e) {
		      e.printStackTrace();
		}
		finally{
		try{
		    if(callableStatement != null) callableStatement.close();
		    if(conn != null) conn.close();
		} catch(Exception ex){}
		}	
	}
@Override
public void updateConsumptionData() {
	 try {

	      conn =jdbcTemplate.getDataSource().getConnection();
	      callableStatement = conn.prepareCall("{call staging.updateGEStockFromConsumption()}");
	      callableStatement.execute();
	} catch (SQLException e) {
	      e.printStackTrace();
	}
	finally{
	try{
	    if(callableStatement != null) callableStatement.close();
	    if(conn != null) conn.close();
	} catch(Exception ex){}
	}		
}
	
}
	 

	

