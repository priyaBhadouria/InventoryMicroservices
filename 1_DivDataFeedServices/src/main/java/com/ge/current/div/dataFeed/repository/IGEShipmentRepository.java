package com.ge.current.div.dataFeed.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ge.current.div.dataFeed.entity.GEShipmentEntity;


public interface IGEShipmentRepository  extends PagingAndSortingRepository<GEShipmentEntity, String>{
}
