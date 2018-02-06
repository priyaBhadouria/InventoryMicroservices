package com.ge.current.div.dataFeed.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ge.current.div.dataFeed.entity.SapUploadIdSeqEntity;

public interface ISAPUploadIdSeq extends PagingAndSortingRepository <SapUploadIdSeqEntity,String> {

	String seq = "select x.lastValue from SapUploadIdSeqEntity x";
	
	@Query(seq)
	Long getSeq();
}
