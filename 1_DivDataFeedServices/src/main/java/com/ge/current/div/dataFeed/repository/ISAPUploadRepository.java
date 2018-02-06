package com.ge.current.div.dataFeed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ge.current.div.dataFeed.entity.SAPUploadEntity;

public interface ISAPUploadRepository extends PagingAndSortingRepository<SAPUploadEntity,String>{
	
	String FIND_ALL_BY_DESC = "select entity from SAPUploadEntity entity order by entity.id desc";

	@Query(FIND_ALL_BY_DESC)
	List<SAPUploadEntity> findAllDescOrder();
	
	

}
