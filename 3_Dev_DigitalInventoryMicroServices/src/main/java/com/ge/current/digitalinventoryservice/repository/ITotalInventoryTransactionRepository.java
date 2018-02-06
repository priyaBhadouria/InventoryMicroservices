package com.ge.current.digitalinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ge.current.digitalinventoryservice.totalTransaction.entity.TotalInventoryTransactionEntity;

/**
 * @author 842449
 *
 */
public interface ITotalInventoryTransactionRepository extends PagingAndSortingRepository<TotalInventoryTransactionEntity, String> {

	
	String GET_ALL_MATERIAL_ID ="select distinct d.material from TotalInventoryTransactionEntity d";
	@Query(GET_ALL_MATERIAL_ID)
	List<String> getAllMaterial();
	
	String GET_ALL_STO = "select distinct d.shipmentOrder from TotalInventoryTransactionEntity d";
	@Query(GET_ALL_STO)
	List<Long> getAllSto();
	
	String GET_ALL_SOURCE = "select distinct d.source from TotalInventoryTransactionEntity d ";
	@Query(GET_ALL_SOURCE)
	List<String> getAllSources();
	
	String GET_ALL_DESTINATION = "select distinct d.destination from TotalInventoryTransactionEntity d "; 
	@Query(GET_ALL_DESTINATION)
	List<String> getAllDestinations();
	
	String GET_MAX_STO = "select max(d.shipmentOrder) from TotalInventoryTransactionEntity d";
	@Query(GET_MAX_STO)
	Long getMaxSto();
	
	String SELECT_ALL = "select d from TotalInventoryTransactionEntity d where d.transactionDate = (select max(d.transactionDate) from TotalInventoryTransactionEntity d)";
	@Query(SELECT_ALL)
	List<TotalInventoryTransactionEntity> selectAllData();
	
	
}
