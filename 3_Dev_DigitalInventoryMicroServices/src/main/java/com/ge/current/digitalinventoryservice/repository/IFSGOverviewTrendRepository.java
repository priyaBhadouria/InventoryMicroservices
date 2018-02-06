package com.ge.current.digitalinventoryservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGIssuedAggregationSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGReceivedAggregationSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGTrendDrilldownLocSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGTrendDrilldownSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGTrendFsgSummaryEntity;

public interface IFSGOverviewTrendRepository extends PagingAndSortingRepository<FSGIssuedAggregationSummaryEntity, Long> {
	
	String GET_FSG_ISSUED_AGGREGATION_SUMMARY_DETAILS_QUERY = "select entity from FSGIssuedAggregationSummaryEntity entity order by entity.issuedDate asc";
	@Query(GET_FSG_ISSUED_AGGREGATION_SUMMARY_DETAILS_QUERY)
	public List<FSGIssuedAggregationSummaryEntity> getFSGIssuedAggregationSummary();

	String GET_FSG_RECEIVED_AGGREGATION_SUMMARY_DETAILS_QUERY = "select entity from FSGReceivedAggregationSummaryEntity entity order by entity.receivedDate asc";
	@Query(GET_FSG_RECEIVED_AGGREGATION_SUMMARY_DETAILS_QUERY)
	public List<FSGReceivedAggregationSummaryEntity> getFSGReceivedAggregationSummary();

	String GET_FSG_ISSUED_LOCATION_AGGREGATION_SUMMARY_DETAILS_QUERY = "select entity from FSGIssuedAggregationSummaryEntity entity where entity.fsg = :fsg order by entity.issuedDate asc";
	@Query(GET_FSG_ISSUED_LOCATION_AGGREGATION_SUMMARY_DETAILS_QUERY)
	public List<FSGIssuedAggregationSummaryEntity> getFSGIssuedLocationAggregationSummary(@Param("fsg") String fsg);

	String GET_FSG_RECEIVED_LOCATION_AGGREGATION_SUMMARY_DETAILS_QUERY = "select entity from FSGReceivedAggregationSummaryEntity entity where entity.fsg = :fsg order by entity.receivedDate asc";
	@Query(GET_FSG_RECEIVED_LOCATION_AGGREGATION_SUMMARY_DETAILS_QUERY)
	public List<FSGReceivedAggregationSummaryEntity> getFSGReceivedLocationAggregationSummary(@Param("fsg") String fsg);

	String GET_FSG_DRILLDOWN_SUMMARY_DETAILS_QUERY = "select entity from FSGTrendDrilldownSummaryEntity entity where entity.date = :date ";
	@Query(GET_FSG_DRILLDOWN_SUMMARY_DETAILS_QUERY)
	public List<FSGTrendDrilldownSummaryEntity> getFSGTrendDrilldownSummary(@Param("date") LocalDate date);

	String GET_FSG_DRILLDOWN_LOC_SUMMARY_QUERY = "select entity from FSGTrendDrilldownLocSummaryEntity entity where entity.fsg = :fsg and entity.date = :date ";
	@Query(GET_FSG_DRILLDOWN_LOC_SUMMARY_QUERY)
	public List<FSGTrendDrilldownLocSummaryEntity> getFSGTrendDrilldownLocSummary(@Param("fsg") String fsg,
			@Param("date") LocalDate date);

	String GET_ALL_FSG_TREND_FSG_SUMMARY_DETAILS_QUERY = "select entity from FSGTrendFsgSummaryEntity entity where entity.material = :materialId and entity.date = :date ";
	@Query(GET_ALL_FSG_TREND_FSG_SUMMARY_DETAILS_QUERY)
	public List<FSGTrendFsgSummaryEntity> getFSGTrendFsgSummary(@Param("materialId") String materialId,
			@Param("date") LocalDate date);
	
	String GET_FSG_TREND_FSG_ISSUED_SUMMARY_DETAILS_QUERY = "select entity from FSGTrendFsgSummaryEntity entity where entity.material = :materialId and entity.date = :date and entity.issuedlocation = :fsgName";
	@Query(GET_FSG_TREND_FSG_ISSUED_SUMMARY_DETAILS_QUERY)
	public List<FSGTrendFsgSummaryEntity> getFSGTrendFsgIssuedSummary(@Param("materialId") String materialId,
			@Param("date") LocalDate date, @Param("fsgName") String fsgName);
	
	String GET_FSG_TREND_FSG_RECEIVED_SUMMARY_DETAILS_QUERY = "select entity from FSGTrendFsgSummaryEntity entity where entity.material = :materialId and entity.date = :date and entity.receivedlocation = :fsgName";
	@Query(GET_FSG_TREND_FSG_RECEIVED_SUMMARY_DETAILS_QUERY)
	public List<FSGTrendFsgSummaryEntity> getFSGTrendFsgReceivedSummary(@Param("materialId") String materialId,
			@Param("date") LocalDate date, @Param("fsgName") String fsgName);

	String GET_FSG_DETAILS_QUERY = "select entity.issuedlocation from FSGTrendFsgSummaryEntity entity";
	@Query(GET_FSG_DETAILS_QUERY)
	public List<String> getFSGSummary();

}
