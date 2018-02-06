package com.ge.current.div.blob.common.utility;

public class QueriesDiv {
	
	
	public static final String get_File_Template = "select xls_id from staging.xls_master where xls_name=?";
	public static final String get_Schema_Name = "select schema_name from staging.xls_master where xls_id=?";
	public static final String Insert_Excel_Upload_Data = "Insert into staging.xls_upload (xls_id,file_name,csv_blob_store_location,db_processed,user_comment,create_dtm,created_by,update_dtm,updated_by) values (?,?,?,'N',?,now(),?,now(),?)";
	public static final String Insert_Excel_Upload_Logger_Data = "Insert into staging.logger (upload_or_run_id,log,create_dtm) values (?,?,now())";
	//public static final String Get_Excel_Upload_Details = "SELECT upload_id,xls_id,csv_blob_store_location,file_name FROM staging.t_xls_upload where db_processed='N' order by 1 asc";
	public static final String Get_Logger_Data = "select log from staging.logger where upload_or_run_id = ";
	
	public static final String Get_Excel_Upload_Details="SELECT upload_id,xls_id,csv_blob_store_location,file_name FROM staging.xls_upload where db_processed='N' order by 1 asc";
	public static final String Get_Template_Details = "SELECT xls_mast.xls_name,xls_mast.xls_id,xls_mast.xls_desc FROM staging.xls_master xls_mast where xls_mast.active='Y'";
	public static final String Get_Excel_Template_Data = "SELECT distinct xls_mast.db_table_name,xls_mast.schema_name,xls_col_ref.* FROM staging.xls_col_ref xls_col_ref, staging.xls_master xls_mast where xls_col_ref.xls_id = xls_mast.xls_id and xls_mast.active='Y' and xls_col_ref.xls_id=";
	public static final String Get_File_Seq_Num ="select coalesce(max(FILE_SEQ_NUMBER)+1,0) as upload_count from ";
	public static final String Update_Excel_Upload_Data_Err = "update staging.xls_upload set TOTAL_ROWS=?,ROW_PASSED=?,ROW_FAILED=?, DB_PROCESSED='Y',UPDATED_BY='SYSTEM',UPDATE_DTM=now() where upload_id=? and xls_id=?";
	public static final String Update_Excel_Upload_Data = "update staging.xls_upload set DB_PROCESSED='Y',UPDATED_BY='1',UPDATE_DTM=now() where upload_id=? and xls_id=?";

	
	public static final String  Update_Processed_Demand = "update staging.PROCESS_ON_DEMAND set is_process_triggered='N' where process_id=?";
	public static final String Get_Upload_Details = "SELECT xls_upload.upload_id,xls_upload.xls_id,xls_upload.file_name,xls_upload.csv_blob_store_location,xls_upload.row_passed,xls_upload.row_failed,xls_upload.total_rows,xls_upload.user_comment,xls_upload.db_processed,xls_upload.create_dtm,xls_upload.created_by FROM staging.xls_upload xls_upload order by xls_upload.upload_id desc";

}
