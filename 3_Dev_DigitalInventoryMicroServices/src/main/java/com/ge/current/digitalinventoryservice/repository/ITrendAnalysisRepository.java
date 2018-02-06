/**
 * 
 */
package com.ge.current.digitalinventoryservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.digitalinventoryservice.trendanalysis.entity.DSDemandShortageDrillDownDetailsEntity;
import com.ge.current.digitalinventoryservice.trendanalysis.entity.DSInTransitDrillDownDetailsEntity;
import com.ge.current.digitalinventoryservice.trendanalysis.entity.DSInventoryDrillDownDetailsEntity;
import com.ge.current.digitalinventoryservice.trendanalysis.entity.DemandShortageHistoryDetailsEntity;
import com.ge.current.digitalinventoryservice.trendanalysis.entity.DemandSupplyDemandGraphDetailsEntity;
import com.ge.current.digitalinventoryservice.trendanalysis.entity.DemandSupplyInTransitGraphDetailsEntity;
import com.ge.current.digitalinventoryservice.trendanalysis.entity.DemandSupplyInventoryGraphDetailsEntity;
import com.ge.current.digitalinventoryservice.trendanalysis.entity.DemandSupplyShortageGraphDetailsEntity;
import com.ge.current.digitalinventoryservice.trendanalysis.entity.InTransitInventoryHistoryDetailsEntity;
import com.ge.current.digitalinventoryservice.trendanalysis.entity.InventoryStockHistoryDetailsEntity;

/**
 * @author ncheredd
 *
 */
public interface ITrendAnalysisRepository extends PagingAndSortingRepository<DemandSupplyInTransitGraphDetailsEntity, Long>
{
	String GET_DEMAND_SUPPLY_INVENTORY_GRAPH_DETAILS_QUERY = "select inventoryGraphEntity from DemandSupplyInventoryGraphDetailsEntity inventoryGraphEntity";
	
	String GET_DEMAND_SUPPLY_IN_TRANSIT_GRAPH_DETAILS_QUERY = "select inTransitGraphEntity from DemandSupplyInTransitGraphDetailsEntity inTransitGraphEntity";
	
	String GET_DEMAND_SUPPLY_DEMAND_GRAPH_DETAILS_QUERY = "select demandGraphEntity from DemandSupplyDemandGraphDetailsEntity demandGraphEntity";
	
	String GET_DEMAND_SUPPLY_SHORTAGE_GRAPH_DETAILS_QUERY = "select shortageGraphEntity from DemandSupplyShortageGraphDetailsEntity shortageGraphEntity";
	
	String GET_DS_INVENTORY_DRILL_DOWN_DETAILS_QUERY = "select inventoryDetailsEntity from DSInventoryDrillDownDetailsEntity inventoryDetailsEntity where inventoryDetailsEntity.date = :date";
	
	String GET_DS_IN_TRANSIT_DRILL_DOWN_DETAILS_QUERY = "select inTransitDetailsEntity from DSInTransitDrillDownDetailsEntity inTransitDetailsEntity where inTransitDetailsEntity.date = :date";
	
	String GET_DS_DEMAND_SHORTAGE_DRILL_DOWN_DETAILS_QUERY = "select demandShortageDetailsEntity from DSDemandShortageDrillDownDetailsEntity demandShortageDetailsEntity where demandShortageDetailsEntity.date = :date";
	
	String GET_DS_INVENTORY_POP_UP_DETAILS_QUERY = "select inventoryPopUpDetailsEntity from InventoryStockHistoryDetailsEntity inventoryPopUpDetailsEntity where inventoryPopUpDetailsEntity.material = :material and inventoryPopUpDetailsEntity.date = :date";
	
	String GET_DS_IN_TRANSIT_POP_UP_DETAILS_QUERY = "select inTransitPopUpDetailsEntity from InTransitInventoryHistoryDetailsEntity inTransitPopUpDetailsEntity where inTransitPopUpDetailsEntity.material = :material and inTransitPopUpDetailsEntity.date = :date";
	
	String GET_DS_DEMAND_SHORTAGE_POP_UP_DETAILS_QUERY = "select demandShortagePopUpDetailsEntity from DemandShortageHistoryDetailsEntity demandShortagePopUpDetailsEntity where demandShortagePopUpDetailsEntity.material = :material and demandShortagePopUpDetailsEntity.date = :date";
	
	
	
	@Query(GET_DEMAND_SUPPLY_INVENTORY_GRAPH_DETAILS_QUERY)
	public List<DemandSupplyInventoryGraphDetailsEntity> getInventoryGraphDetails();
	
	@Query(GET_DEMAND_SUPPLY_IN_TRANSIT_GRAPH_DETAILS_QUERY)
	public List<DemandSupplyInTransitGraphDetailsEntity> getInTransitGraphDetails();
	
	@Query(GET_DEMAND_SUPPLY_DEMAND_GRAPH_DETAILS_QUERY)
	public List<DemandSupplyDemandGraphDetailsEntity> getDemandGraphDetails();
	
	@Query(GET_DEMAND_SUPPLY_SHORTAGE_GRAPH_DETAILS_QUERY)
	public List<DemandSupplyShortageGraphDetailsEntity> getShortageGraphDetails();
	
	@Query(GET_DS_INVENTORY_DRILL_DOWN_DETAILS_QUERY)
	public List<DSInventoryDrillDownDetailsEntity> getInventoryDrillDownDetails(@Param("date") Date date);
	
	@Query(GET_DS_IN_TRANSIT_DRILL_DOWN_DETAILS_QUERY)
	public List<DSInTransitDrillDownDetailsEntity> getInTransitDrillDownDetails(@Param("date") Date date);
	
	@Query(GET_DS_DEMAND_SHORTAGE_DRILL_DOWN_DETAILS_QUERY)
	public List<DSDemandShortageDrillDownDetailsEntity> getDemandShortageDrillDownDetails(@Param("date") Date date);
	
	
	@Query(GET_DS_INVENTORY_POP_UP_DETAILS_QUERY)
	public List<InventoryStockHistoryDetailsEntity> getInventoryPopUpDetails(@Param("material") String material, @Param("date") Date date);
	
	@Query(GET_DS_IN_TRANSIT_POP_UP_DETAILS_QUERY)
	public List<InTransitInventoryHistoryDetailsEntity> getInTransitPopUpDetails(@Param("material") String material, @Param("date") Date date);
	
	@Query(GET_DS_DEMAND_SHORTAGE_POP_UP_DETAILS_QUERY)
	public List<DemandShortageHistoryDetailsEntity> getDemandShortagePopUpDetails(@Param("material") String material, @Param("date") Date date);
}
