/**
 * 
 */
package com.ge.current.digitalinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.digitalinventoryservice.inactivestocks.entity.InactiveInventoryStockDetailsEntity;
import com.ge.current.digitalinventoryservice.inactivestocks.entity.InactiveInventoryStockRatioEntity;

/**
 * @author ncheredd
 *
 */
public interface IInactiveInventoryStockRepository extends PagingAndSortingRepository<InactiveInventoryStockRatioEntity, Long>
{
	String GET_INACTIVE_STOCK_RATIO_DETAILS_QUERY = "select inactiveStockRatioEntity from InactiveInventoryStockRatioEntity inactiveStockRatioEntity";
	@Query(GET_INACTIVE_STOCK_RATIO_DETAILS_QUERY)
	public InactiveInventoryStockRatioEntity getInactiveStockRatioDetails();
	
	String GET_INACTIVE_INVENTORY_STOCK_DETAILS_QUERY = "select entity from InactiveInventoryStockDetailsEntity entity where entity.inactiveDays > :fromDays and entity.inactiveDays <= :toDays";
	@Query(GET_INACTIVE_INVENTORY_STOCK_DETAILS_QUERY)
	public List<InactiveInventoryStockDetailsEntity> getInactiveInventoryStockDetails(@Param("fromDays") int fromDays, @Param("toDays") int toDays);

	String GET_MOST_INACTIVE_DAYS_STOCK_DETAILS_QUERY = "select entity from InactiveInventoryStockDetailsEntity entity where entity.inactiveDays > :fromDays";
	@Query(GET_MOST_INACTIVE_DAYS_STOCK_DETAILS_QUERY)
	public List<InactiveInventoryStockDetailsEntity> getMostInactiveDaysInventoryStockDetails(@Param("fromDays") int fromDays);
}
