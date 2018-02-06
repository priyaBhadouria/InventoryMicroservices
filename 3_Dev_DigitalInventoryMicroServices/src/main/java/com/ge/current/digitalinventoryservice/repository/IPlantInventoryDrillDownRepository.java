/**
 * 
 */
package com.ge.current.digitalinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.digitalinventoryservice.inventorydrilldown.entity.IInventoryDrillDownEntity;
import com.ge.current.digitalinventoryservice.inventorydrilldown.entity.PlantDetailsEntity;

/**
 * @author ncheredd
 *
 */
public interface IPlantInventoryDrillDownRepository extends PagingAndSortingRepository<PlantDetailsEntity, Long>
{
	String GET_PLANT_DETAILS_QUERY = "select plantEntity from PlantDetailsEntity plantEntity";
	@Query(GET_PLANT_DETAILS_QUERY)
	public List<PlantDetailsEntity> getPlantDetails();
	
	String GET_PLANT_TO_FSG_QUERY = "select entity from PlantToFSGNamesEntity entity where entity.source = :plant";
	@Query(GET_PLANT_TO_FSG_QUERY)
	public List<IInventoryDrillDownEntity> getPlantToFSGNames(@Param("plant") String plant);
	
	String GET_FSG_TO_FSG_NAMES_QUERY = "select entity from FSGToFSGNamesEntity entity where entity.source = :fsg and entity.locationType = 'FSG'";
	@Query(GET_FSG_TO_FSG_NAMES_QUERY)
	public List<IInventoryDrillDownEntity> getFSGToFSGNames(@Param("fsg") String fsg);
	
	String GET_FSG_TO_CUSTOMER_NAMES_QUERY = "select entity from FSGToCustomerNamesEntity entity where entity.source = :fsg and entity.locationType = 'CUSTOMER'";
	@Query(GET_FSG_TO_CUSTOMER_NAMES_QUERY)
	public List<IInventoryDrillDownEntity> getFSGToCustomerNames1(@Param("fsg") String fsg);		
}
