package com.ge.current.div.blob.common.utility;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.div.blob.entity.ProcessOnDemandEntity;

public interface ProcessOnDemandRepository extends PagingAndSortingRepository <ProcessOnDemandEntity,String> {
	
	
	String GET_STATUS = "select count(obj) from ProcessOnDemandEntity obj where isProcessTriggered = 'Y'";
	@Query(GET_STATUS)
	public int getIsProcessedOnDemand();
	
	String GET_ALL_RECORDS_FOR_PROCESS = "select obj from ProcessOnDemandEntity obj where isProcessTriggered = 'Y'";
	@Query(GET_ALL_RECORDS_FOR_PROCESS)
	public List<ProcessOnDemandEntity> getListProcessedOnDemand();
	
}
