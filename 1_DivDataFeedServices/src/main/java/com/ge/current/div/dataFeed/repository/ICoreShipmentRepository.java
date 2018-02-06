package com.ge.current.div.dataFeed.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ge.current.div.dataFeed.entity.CoreShipmentEntity;



public interface ICoreShipmentRepository extends PagingAndSortingRepository <CoreShipmentEntity,String>{

// String seq_number = "select nextval('staging.XLS_UPLOAD_ID_SEQ')";
	
	//@Query(seq_number)
	//Long getSeqNumber();
	
	
}
