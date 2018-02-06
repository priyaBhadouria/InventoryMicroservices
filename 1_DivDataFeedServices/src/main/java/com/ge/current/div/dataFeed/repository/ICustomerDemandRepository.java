package com.ge.current.div.dataFeed.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ge.current.div.dataFeed.entity.CustomerDemandEntity;

public interface ICustomerDemandRepository  extends PagingAndSortingRepository<CustomerDemandEntity,String> {

}
