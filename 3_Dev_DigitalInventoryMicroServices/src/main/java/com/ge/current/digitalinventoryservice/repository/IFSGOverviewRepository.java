/**
 * 
 */
package com.ge.current.digitalinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGAggregationSummaryEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGInventoryTransitDemandShortageDrillDownEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGOverviewDollarValueSummaryEntity;

/**
 * @author ncheredd
 *
 */
public interface IFSGOverviewRepository extends PagingAndSortingRepository<FSGAggregationSummaryEntity, Long> 
{
	String GET_FSG_AGGREGATION_SUMMARY_DETAILS_QUERY = "select entity from FSGAggregationSummaryEntity entity where entity.fsg = :fsg";
	@Query(GET_FSG_AGGREGATION_SUMMARY_DETAILS_QUERY)
	public List<FSGAggregationSummaryEntity> getFSGAggregationSummary(@Param("fsg") String fsg);
	
	String GET_FSG_AGGREGATION_DRILL_DOWN_DETAILS_QUERY = "select entity from FSGInventoryTransitDemandShortageDrillDownEntity entity where entity.fsgLocation = :fsg";
	@Query(GET_FSG_AGGREGATION_DRILL_DOWN_DETAILS_QUERY)
	public List<FSGInventoryTransitDemandShortageDrillDownEntity> getFSGDrillDownDetails(@Param("fsg") String fsg);
	
	String GET_FSG_OVERVIEW_DOLLAR_VALUE_SUMMARY_DETAILS_QUERY = "select entity from FSGOverviewDollarValueSummaryEntity entity";
	@Query(GET_FSG_OVERVIEW_DOLLAR_VALUE_SUMMARY_DETAILS_QUERY)
	public FSGOverviewDollarValueSummaryEntity getFSGOverviewDollarValueSummary();
}
