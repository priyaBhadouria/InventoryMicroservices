/**
 * 
 */
package com.ge.current.digitalinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.digitalinventoryservice.totalTransaction.entity.TotalFSGSummaryEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.CustomerConsumedInventoryDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.CustomerDemandInventoryDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.CustomerMaterialDemandDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.CustomerMaterialDemandSummaryEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.InventoryTransitToCustomerDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.InventoryTransitToFSGDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TIShipmentDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventoryAtCustomerDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventoryAtFSGDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventoryDollarValueSummaryEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventorySummaryEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalNetInventoryAtFSGDetailsEntity;


/**
 * @author ncheredd
 *
 */
public interface ITotalInventoryRepository extends PagingAndSortingRepository<TotalInventorySummaryEntity, Long>
{
	String GET_TOTAL_INVENTORY_SUMMARY_DETAILS_QUERY = "select totalInventorySummaryEntity from TotalInventorySummaryEntity totalInventorySummaryEntity";
	@Query(GET_TOTAL_INVENTORY_SUMMARY_DETAILS_QUERY)
	public TotalInventorySummaryEntity getTotalInventorySummary();
	
	String GET_SHIPMENT_DRILL_DOWN_DETAILS_QUERY = "select shipmentDetails from TIShipmentDetailsEntity shipmentDetails";
	@Query(GET_SHIPMENT_DRILL_DOWN_DETAILS_QUERY)
	public List<TIShipmentDetailsEntity> getTotalInventoryShipmentDetails();	
	
	String GET_INVENTORY_TRANSIT_TO_FSG_QUERY = "select transitDetailsEntity from InventoryTransitToFSGDetailsEntity transitDetailsEntity";	
	@Query(GET_INVENTORY_TRANSIT_TO_FSG_QUERY)
	public List<InventoryTransitToFSGDetailsEntity> getInventoryTransitToFSGDetails();
	
	String GET_INVENTORY_AT_FSG_DETAILS_QUERY = "select atFSGEntity from TotalInventoryAtFSGDetailsEntity atFSGEntity";
	@Query(GET_INVENTORY_AT_FSG_DETAILS_QUERY)
	public List<TotalInventoryAtFSGDetailsEntity> getInventoryAtFSGDetails();

	String GET_INVENTORY_TRANSIT_TO_CUSTOMER_QUERY = "select transitDetailsEntity from InventoryTransitToCustomerDetailsEntity transitDetailsEntity";
	@Query(GET_INVENTORY_TRANSIT_TO_CUSTOMER_QUERY)
	public List<InventoryTransitToCustomerDetailsEntity> getInventoryTransitToCustomerDetails();

	String GET_INVENTORY_AT_CUSTOMER_DETAILS_QUERY = "select atCustomerEntity from TotalInventoryAtCustomerDetailsEntity atCustomerEntity";
	@Query(GET_INVENTORY_AT_CUSTOMER_DETAILS_QUERY)
	public List<TotalInventoryAtCustomerDetailsEntity> getInventoryAtCustomerDetails();

	String GET_CUSTOMER_CONSUMED_INVENTORY_DETAILS_QUERY = "select customerConsumedEntity from CustomerConsumedInventoryDetailsEntity customerConsumedEntity";
	@Query(GET_CUSTOMER_CONSUMED_INVENTORY_DETAILS_QUERY)
	public List<CustomerConsumedInventoryDetailsEntity> getCustomerConsumedInventoryDetails();
	
	String GET_TOTAL_INVENTORY_DOLLAR_VALUE_SUMMARY_DETAILS_QUERY = "select entity from TotalInventoryDollarValueSummaryEntity entity";
	@Query(GET_TOTAL_INVENTORY_DOLLAR_VALUE_SUMMARY_DETAILS_QUERY)
	public TotalInventoryDollarValueSummaryEntity getTotalInventoryDollarValueSummary();
	
	String GET_CUSTOMER_DEMAND_INVENTORY_DETAILS_QUERY = "select customerDemandEntity from CustomerDemandInventoryDetailsEntity customerDemandEntity";
	@Query(GET_CUSTOMER_DEMAND_INVENTORY_DETAILS_QUERY)
	public List<CustomerDemandInventoryDetailsEntity> getCustomerDemandInventoryDetails();
	
	String GET_CUSTOMER_MATERIAL_DEMAND_DETAILS_QUERY = "select customerMaterialDemandEntity from CustomerMaterialDemandDetailsEntity customerMaterialDemandEntity "
			+ "where customerMaterialDemandEntity.customerId = :customerId and customerMaterialDemandEntity.fiscalWeek = :fiscalWeek and "
			+ "customerMaterialDemandEntity.year = :year";
	@Query(GET_CUSTOMER_MATERIAL_DEMAND_DETAILS_QUERY)
	public List<CustomerMaterialDemandDetailsEntity> getCustomerMaterialDemandInventoryDetails(@Param("customerId") String customerId, @Param("fiscalWeek") Integer fiscalWeek, @Param("year") Integer year);


	String GET_TOTAL_NET_INVENTORY_DETAILS_QUERY = "select entity from TotalNetInventoryAtFSGDetailsEntity entity";
	@Query(GET_TOTAL_NET_INVENTORY_DETAILS_QUERY)
	public List<TotalNetInventoryAtFSGDetailsEntity> getTotalNetInventoryDetails();

	String GET_TOTAL_FSG_SUMMARY_DETAILS_QUERY = "select entity from TotalFSGSummaryEntity entity";
    @Query(GET_TOTAL_FSG_SUMMARY_DETAILS_QUERY)
    public List<TotalFSGSummaryEntity>getTotalFSGSummary();

	
    String GET_CUSTOMER_MATERIAL_DEMAND_SUMMERY_QUERY = "select entity from CustomerMaterialDemandSummaryEntity entity order by entity.year,entity.fiscalWeek asc";
    @Query(GET_CUSTOMER_MATERIAL_DEMAND_SUMMERY_QUERY)
    public List<CustomerMaterialDemandSummaryEntity> getCustomerMaterialDemandSummary();
	
}
