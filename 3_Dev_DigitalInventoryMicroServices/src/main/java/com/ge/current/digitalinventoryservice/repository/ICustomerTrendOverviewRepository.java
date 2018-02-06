package com.ge.current.digitalinventoryservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustConsDrilldownSiteSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustConsDrilldownSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustTrendFSGDrilldownEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustomerConsumptionSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustomerReturnSummaryEntity;

public interface ICustomerTrendOverviewRepository
		extends PagingAndSortingRepository<CustomerConsumptionSummaryEntity, Long> {

	String GET_CUSTOMER_CONSUMPTION_AGGREGATION_DETAILS_QUERY = "select entity from CustomerConsumptionSummaryEntity entity order by entity.installationDate asc";
	@Query(GET_CUSTOMER_CONSUMPTION_AGGREGATION_DETAILS_QUERY)
	public List<CustomerConsumptionSummaryEntity> getCustomerConsumptionSummary();

	String GET_CUSTOMER_RETURN_AGGREGATION_DETAILS_QUERY = "select entity from CustomerReturnSummaryEntity entity order by entity.returnDate asc";
	@Query(GET_CUSTOMER_RETURN_AGGREGATION_DETAILS_QUERY)
	public List<CustomerReturnSummaryEntity> getCustomerReturnSummary();

	String GET_CUSTOMER_CONSUMPTION_SITE_ID_AGGREGATION_DETAILS_QUERY = "select entity from CustomerConsumptionSummaryEntity entity where entity.customerSiteId = :customerSiteId order by entity.installationDate asc";
	@Query(GET_CUSTOMER_CONSUMPTION_SITE_ID_AGGREGATION_DETAILS_QUERY)
	public List<CustomerConsumptionSummaryEntity> getCustomerConsumptionSiteIdSummary(
			@Param("customerSiteId") String customerSiteId);

	String GET_CUSTOMER_RETURN_AGGREGATION_SITE_ID_DETAILS_QUERY = "select entity from CustomerReturnSummaryEntity entity where entity.customerSiteId = :customerSiteId order by entity.returnDate asc";
	@Query(GET_CUSTOMER_RETURN_AGGREGATION_SITE_ID_DETAILS_QUERY)
	public List<CustomerReturnSummaryEntity> getCustomerReturnSiteIdSummary(
			@Param("customerSiteId") String customerSiteId);

	String GET_CUSTOMER_CONSUMPTION_DRILLDOWN_SUMMARY_DETAILS_QUERY = "select entity from CustConsDrilldownSummaryEntity entity where entity.date = :date ";
	@Query(GET_CUSTOMER_CONSUMPTION_DRILLDOWN_SUMMARY_DETAILS_QUERY)
	public List<CustConsDrilldownSummaryEntity> getCustConsDrilldownSummary(@Param("date") LocalDate date);

	String GET_ALL_CUSTOMERS_TREND_FSG_SUMMARY_DETAILS_QUERY = "select entity from CustTrendFSGDrilldownEntity entity where entity.material = :materialId and entity.date = :date ";
	@Query(GET_ALL_CUSTOMERS_TREND_FSG_SUMMARY_DETAILS_QUERY)
	public List<CustTrendFSGDrilldownEntity> getCustomerTrendFsgSummary(@Param("materialId") String materialId,
			@Param("date") LocalDate date);
	
	String GET_CUSTOMER_TREND_FSG_SUMMARY_DETAILS_QUERY = "select entity from CustTrendFSGDrilldownEntity entity where entity.material = :materialId and entity.date = :date and entity.customerId = :customerId";
	@Query(GET_CUSTOMER_TREND_FSG_SUMMARY_DETAILS_QUERY)
	public List<CustTrendFSGDrilldownEntity> getCustomerTrendFsgSummary(@Param("materialId") String materialId,
			@Param("date") LocalDate date, @Param("customerId") String customerId);

	String GET_CUSTOMER_DETAILS_QUERY = "select entity.customerId from CustTrendFSGDrilldownEntity entity";
	@Query(GET_CUSTOMER_DETAILS_QUERY)
	public List<String> getCustomerSummary();

	String GET_CUSTOMER_CONSUMPTION_SITE_DRILLDOWN_SUMMARY_DETAILS_QUERY = "select entity from CustConsDrilldownSiteSummaryEntity entity where entity.date = :date and entity.customerSiteId = :customerSiteId  ";
	@Query(GET_CUSTOMER_CONSUMPTION_SITE_DRILLDOWN_SUMMARY_DETAILS_QUERY)
	public List<CustConsDrilldownSiteSummaryEntity> getCustConsSiteDrilldownSummary(@Param("date") LocalDate date,
			@Param("customerSiteId") String customerSiteId);

}
