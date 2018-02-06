package com.ge.current.digitalinventoryservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ge.current.digitalinventoryservice.totalinventory.entity.InstallerReceivedEntity;

public interface IInstallerReceivedRepository  extends PagingAndSortingRepository<InstallerReceivedEntity,Long>  {

}
