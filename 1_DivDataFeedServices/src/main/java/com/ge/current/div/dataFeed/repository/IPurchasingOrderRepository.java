package com.ge.current.div.dataFeed.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ge.current.div.dataFeed.entity.PurchasingOrderEntity;

public interface IPurchasingOrderRepository extends PagingAndSortingRepository<PurchasingOrderEntity,String> {

}
