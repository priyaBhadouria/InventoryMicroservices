package com.ge.current.div.blob.common.utility;

public class Constants {
	  public static final boolean PASSES = true;
	  public static final boolean FAILS = false;
	  public static final boolean SUCCESS = true;
	  public static final boolean FAILURE = false;
	  /** System property - <tt>line.separator</tt>*/
	  public static final String NEW_LINE = System.getProperty("line.separator");
	  /** System property - <tt>file.separator</tt>*/
	  public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	  /** System property - <tt>path.separator</tt>*/
	  public static final String PATH_SEPARATOR = System.getProperty("path.separator");
	  public static final String EMPTY_STRING = "";
	  public static final String SPACE = " ";
	  public static final String TAB = "\t";
	  public static final String SINGLE_QUOTE = "'";
	  public static final String PERIOD = ".";
	  public static final String DOUBLE_QUOTE = "\"";
	  
	  public static final String RES_FLAG_Y = "Y";
	  public static final String RES_FLAG_N = "N";
	  
	  public static final String ERR_DATE_CODE = "100";
	  public static final String ERR_INTG_CODE = "101";
	  public static final String ERR_OTHR_CODE = "102";
	  public static final String ERR_INSERT_CODE = "103";
	  
	  
	  public static final String LOG_TYPE_CODE = "U";
	  public static final String DB_SCHEMA = "staging.";
	  public static final String SUCCESS_MSG = "SUCCESS";
	  public static final String BLOB_STORE_LOCATION = "vm/blobstore/";


	  private Constants(){
	    //this prevents even the native class from 
	    //calling this ctor as well :
	    throw new AssertionError();
	  }
}
