package com.ge.current.div.dataFeed.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ge.current.div.dataFeed.entity.CustomerConsumptionEntity;



public interface ICustomerConsumptionRepository  extends PagingAndSortingRepository <CustomerConsumptionEntity, String> {
	

}
