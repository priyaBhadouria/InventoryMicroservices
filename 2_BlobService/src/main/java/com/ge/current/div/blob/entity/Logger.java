/**
 * 
 */
package com.ge.current.div.blob.entity;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

/**
 * 
 *
 */

@Entity
@Table(schema="staging" , name="logger")
@NamedNativeQuery(name = "Logger.getLoggerDetails",
query = "select log.log as log, xl.file_name as fileName,xl.csv_blob_store_location as location,xl.row_passed as rowPassed,xl.row_failed as rowFailed,xl.total_rows as totalRows,xl.user_comment as userComments from \"staging\".t_xls_upload xl, \"staging\".t_logger log where log.upload_or_run_id = xl.upload_id and log.type = 'U'" )
//query = "select log.log as \"log\", xl.file_name as \"fileName\" from \"staging\".t_xls_upload xl, \"staging\".t_logger log where log.upload_or_run_id = xl.upload_id and log.type = 'U';" , resultSetMapping= "loggerMapping")//, resultSetMapping= "loggerMapping")
@SqlResultSetMapping(name="loggerMapping", 
entities={ 
    @EntityResult(entityClass=com.ge.current.div.blob.entity.ExcelUpload.class, fields={
        @FieldResult(name="fileName", column="file_name"),
        @FieldResult(name="location", column="csv_blob_store_location"), 
        @FieldResult(name="rowFailed", column="row_failed"),
        @FieldResult(name="totalRows", column="total_rows"),
        @FieldResult(name="userComments", column="user_comment"),
        @FieldResult(name="rowPassed", column="row_passed")
        
    }),
    
    @EntityResult(entityClass=com.ge.current.div.blob.entity.Logger.class, fields={
            @FieldResult(name="log", column="log")
          })
}
)
public class Logger implements Serializable
{

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public Character getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Character type) {
		this.type = type;
	}

	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7987523433670644116L;


	@Id
	@Column(name = "upload_or_run_id", nullable = false)
	private Integer id;
	
	
	@Column(name="type")
	private Character type;
	
	@Column(name="log")
	private String log;
	
}


