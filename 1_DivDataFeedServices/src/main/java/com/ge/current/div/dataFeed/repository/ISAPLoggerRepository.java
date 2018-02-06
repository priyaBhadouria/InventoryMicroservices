package com.ge.current.div.dataFeed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.div.dataFeed.entity.SAPLoggerEntity;

public interface ISAPLoggerRepository extends PagingAndSortingRepository<SAPLoggerEntity,String>{

	
	String GET_SAP_LOGGER_DATA = "select entity from SAPLoggerEntity entity where entity.sapUploadId = :sapUploadId";
	@Query(GET_SAP_LOGGER_DATA)
	public List<SAPLoggerEntity> getSAPLoggerData(@Param("sapUploadId") Long sapUploadId);
	
}
