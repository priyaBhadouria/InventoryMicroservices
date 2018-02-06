/**
 * 
 */
package com.ge.current.digitalinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ge.current.digitalinventoryservice.heatmap.entity.AddressDetailsEntity;
import com.ge.current.digitalinventoryservice.heatmap.entity.FSGAddressDetailsEntity;
import com.ge.current.digitalinventoryservice.heatmap.entity.FsgToCusomerDetailsEntity;
import com.ge.current.digitalinventoryservice.heatmap.entity.FsgToFsgDetailsEntity;
import com.ge.current.digitalinventoryservice.heatmap.entity.HeatMapFirstlevelDetailsEntity;
import java.lang.Integer;

/**
 * @author ncheredd
 *
 */
public interface IHeatMapRepository extends CrudRepository<AddressDetailsEntity, Long>
{
	String GET_HEAT_MAP_FIRST_LEVEL_DETAILS_QUERY = "select entity from HeatMapFirstlevelDetailsEntity entity";	
	@Query(GET_HEAT_MAP_FIRST_LEVEL_DETAILS_QUERY)
	public List<HeatMapFirstlevelDetailsEntity> getHeatMapFirstLevelDetails();
	
	String GET_FSG_ADDRESS_DETAILS_QUERY = "select entity from FSGAddressDetailsEntity entity";	
	@Query(GET_FSG_ADDRESS_DETAILS_QUERY)
	public List<FSGAddressDetailsEntity> getFsgAddressDetails();
	
	String GET_ALL_FSG_TO_CUSTOMER_DETAILS_QUERY = "select entity from FsgToCusomerDetailsEntity entity";	
	@Query(GET_ALL_FSG_TO_CUSTOMER_DETAILS_QUERY)
	public List<FsgToCusomerDetailsEntity> getFsgToCustomerDetails();
	
	String GET_ALL_FSG_TO_FSG_DETAILS_QUERY = "select entity from FsgToFsgDetailsEntity entity";	
	@Query(GET_ALL_FSG_TO_FSG_DETAILS_QUERY)
	public List<FsgToFsgDetailsEntity> getFsgToFsgDetails();
	
	
	
//	String GET_ALL_ADDRESS_DETAILS_QUERY = "select entity from AddressDetailsEntity entity where entity.latitude is null and entity.longitude is null and "
//										+ "entity.state is not null and entity.zipcode is not null and entity.zipcode != '92870-6731'";
//	@Query(GET_ALL_ADDRESS_DETAILS_QUERY)
//	public List<AddressDetailsEntity> getAddressDetails();
	
//	@Override
//	public AddressDetailsEntity findOne(Long id);
	
	List<AddressDetailsEntity> findByAddressId(Integer addressid);
	
	String GET_LAT_LONG_NOT_AVAILABLE_ADDRESS_DETAILS_QUERY = "select entity from HeatMapFirstlevelDetailsEntity entity where entity.latitude is null and entity.longitude is null";	
	@Query(GET_LAT_LONG_NOT_AVAILABLE_ADDRESS_DETAILS_QUERY)
	public List<HeatMapFirstlevelDetailsEntity> getLatLongNotAvailableAddressDetails();
	
	//For FSG Address Lat Long
	String GET_FSG_LAT_LONG_NOT_AVAILABLE_ADDRESS_DETAILS_QUERY = "select entity from FSGAddressDetailsEntity entity where entity.latitude is null and entity.longitude is null";	
	@Query(GET_FSG_LAT_LONG_NOT_AVAILABLE_ADDRESS_DETAILS_QUERY)
	public List<FSGAddressDetailsEntity> getFSGLatLongNotAvailableAddressDetails();
}
