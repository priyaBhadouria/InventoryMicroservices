package com.ge.current.digitalinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import org.springframework.data.repository.query.Param;

import com.ge.current.digitalinventoryservice.totalTransaction.entity.ConsumptionSummaryEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.CustomerConsumptionEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.CustomerConsumptionOverviewEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.InventoryDrilldownEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.ReturnedEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeAggrigateDataForCustomerLevelEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeDataOnCustomerLevelEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeDataTablePlantLevelEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeIntransitToCustomerEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeIntransitToFSGAndStockAtFSGEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventorySummaryEntity;


public interface IInventoryDrilldownRepository  extends PagingAndSortingRepository<TreeDataTablePlantLevelEntity, String> {

	String GET_PLANT_TABLE_DATA_BY_PLANT = "select tdtpl from TreeDataTablePlantLevelEntity tdtpl where tdtpl.source=:plant";
	@Query(GET_PLANT_TABLE_DATA_BY_PLANT)
	public List<TreeDataTablePlantLevelEntity> getTreeTableDataPlantLevel(@Param("plant")String plant);

	String GET_INVENTORY_DRILLDOWN_BY_FSG = "select ide from InventoryDrilldownEntity ide where ide.location=:location";
	@Query(GET_INVENTORY_DRILLDOWN_BY_FSG)
	public List<InventoryDrilldownEntity> getInventoryDrilldownDetailsByFSG(@Param("location") String location);
	
	String GET_TREE_INTRANSIT_TO_CUSTOMER = "select tic from TreeIntransitToCustomerEntity tic where tic.location=:location";
	@Query(GET_TREE_INTRANSIT_TO_CUSTOMER)
	public TreeIntransitToCustomerEntity getTreeIntransitToCustomer(@Param("location") String location);
	
	String GET_TREE_INTRANSIT_TO_FSG_AND_STOCK = "select tifs from TreeIntransitToFSGAndStockAtFSGEntity tifs where tifs.location=:location";
	@Query(GET_TREE_INTRANSIT_TO_FSG_AND_STOCK)
	public TreeIntransitToFSGAndStockAtFSGEntity getTreeIntransitToFSGAndStockAtFSG(@Param("location") String location);
	
	String GET_AGGRIGATE_DATA_BY_CUSTOMER = "select tadcle from TreeAggrigateDataForCustomerLevelEntity tadcle where tadcle.customer=:customer";
	@Query(GET_AGGRIGATE_DATA_BY_CUSTOMER)
	public List<TreeAggrigateDataForCustomerLevelEntity> getInventoryAggrigateDataByCustomer(@Param("customer") String customer);
	
	String GET_CUSTOMER_TABLE_DATA_BY_CUSTOMER_ID = "select tdsle from TreeDataOnCustomerLevelEntity tdsle where tdsle.customer=:customer";
	@Query(GET_CUSTOMER_TABLE_DATA_BY_CUSTOMER_ID)
	public List<TreeDataOnCustomerLevelEntity> getCustomerTableDataByCustomerId(@Param("customer")String customer);
	
	String GET_TOTAL_INVENTORY_SUMMARY_DETAILS_QUERY = "select totalInventorySummaryEntity from TotalInventorySummaryEntity totalInventorySummaryEntity";
	@Query(GET_TOTAL_INVENTORY_SUMMARY_DETAILS_QUERY)
	public TotalInventorySummaryEntity getTotalInventorySummary();
	
	String GET_CUSTOMER_CONSUMPTION_OVERVIEW_QUERY = "select cco from CustomerConsumptionOverviewEntity cco";  //used
	@Query(GET_CUSTOMER_CONSUMPTION_OVERVIEW_QUERY)
	public CustomerConsumptionOverviewEntity getCustomerConsumptionView();
	
	String GET_RETURNED = "select re from ReturnedEntity re";
	@Query(GET_RETURNED)
	public List<ReturnedEntity> getReturned();
	
	String GET_CONSUMPTION_SUMMARY = "select cse from ConsumptionSummaryEntity cse";
	@Query(GET_CONSUMPTION_SUMMARY)
	public List<ConsumptionSummaryEntity>getConsumptionSummary();
	
	String GET_CUSTOMER_CONSUMPTION = "select cce from CustomerConsumptionEntity cce where cce.state=:state";
	@Query(GET_CUSTOMER_CONSUMPTION)
    public List<CustomerConsumptionEntity> getCustomerConsumption(@Param("state")String state);
 }
