package com.ge.current.digitalinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGConsumptionRateOverViewEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGCustomerDemandEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGMaterialConsumptionRateEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.TotalConsumptionRateEntity;


public interface TotalConsumptionRateRepository extends PagingAndSortingRepository<FSGMaterialConsumptionRateEntity,Long>{
	
	
	String FSG_ALL_CONSUMPTION_RATE_OVERVIEW = "select fsgConsumptionRateOverViewEntity from FSGConsumptionRateOverViewEntity fsgConsumptionRateOverViewEntity";
	
	String FSG_MATERIAL_CONSUMPTION_RATE_OVERVIEW = "select fsgMaterialConsumptionRateEntity from FSGMaterialConsumptionRateEntity fsgMaterialConsumptionRateEntity";
	
	String TOTAL_CONSUMPTION_RATE_OVERVIEW = "select totalConsumptionRateEntity from TotalConsumptionRateEntity totalConsumptionRateEntity";
		
	String FSG_CUST_DEMAND_VIEW = "select fsgCustomerDemandEntity from FSGCustomerDemandEntity fsgCustomerDemandEntity where"
			+ " fsgCustomerDemandEntity.fsgLocation=:fsgLocation and fsgCustomerDemandEntity.materialId=:materialId"
	        + " and fsgCustomerDemandEntity.fiscalWeek<=:fiscalWeek and fsgCustomerDemandEntity.year=:year";
	        
	@Query(FSG_ALL_CONSUMPTION_RATE_OVERVIEW)
	public List<FSGConsumptionRateOverViewEntity> getTotalFSGConsumptionSummary();
	
	
	@Query(FSG_MATERIAL_CONSUMPTION_RATE_OVERVIEW)
	public List<FSGMaterialConsumptionRateEntity> getFSGMaterialConsumptionSummary();
	
	
	@Query(TOTAL_CONSUMPTION_RATE_OVERVIEW)
	public TotalConsumptionRateEntity getTotalConsumptionSummary();
	
	
	@Query(FSG_CUST_DEMAND_VIEW)
	public List<FSGCustomerDemandEntity> getFSGMaterialCustomerDemand(@Param("fsgLocation")String fsgLocation, @Param("materialId")String materialId,
			@Param("fiscalWeek")Integer fiscalWeek,@Param("year")Integer year);
	
}
