/**
 * 
 */
package com.ge.current.digitalinventoryservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.current.digitalinventoryservice.inventorydrilldown.entity.IInventoryDrillDownEntity;
import com.ge.current.digitalinventoryservice.inventorydrilldown.entity.PlantDetailsEntity;
import com.ge.current.digitalinventoryservice.repository.IPlantInventoryDrillDownRepository;
import com.ge.current.digitalinventoryservice.util.DigitalInventoryConstants;
import com.ge.current.digitalinventoryservice.util.PlantNode;
import com.ge.current.digitalinventoryservice.util.PlantTree;

/**
 * @author ncheredd
 *
 */
@RestController
@RequestMapping("/div/plantinventorydrilldown")
public class PlantInventoryDrillDownServices {
	@Autowired
	OauthVerification oAuth;

	@Autowired
	IPlantInventoryDrillDownRepository iPlantInventoryDrillDownRepository;

	@RequestMapping("/getPlants")
	public @ResponseBody List<String> getPlantNames(HttpServletRequest request,
			HttpServletResponse response) {
		// oAuth.oAuthValidator1(request, response);
		List<String> plantNamesList = new ArrayList<>();
		List<PlantDetailsEntity> plantsEntity = iPlantInventoryDrillDownRepository
				.getPlantDetails();
		for (PlantDetailsEntity entity : plantsEntity)
			plantNamesList.add(entity.getPlantName());
		return plantNamesList;
	}

	@RequestMapping("/getFSGNames")
	public @ResponseBody PlantTree<String> getFSGNamesBasedPlant(
			@Param("plantName") String plantName, HttpServletRequest request,
			HttpServletResponse response) {
	//	 oAuth.oAuthValidator1(request, response);
		List<PlantNode<String>> childrenList = new ArrayList<PlantNode<String>>();
		List<IInventoryDrillDownEntity> plantsEntity = iPlantInventoryDrillDownRepository
				.getPlantToFSGNames(plantName);
		PlantTree<String> plantTree = new PlantTree<String>();
		PlantNode<String> rootElement = new PlantNode<String>(plantName,
				DigitalInventoryConstants.LOCATION_TYPE_PLANT);
		for (IInventoryDrillDownEntity entity : plantsEntity) {
			String fsgName = entity.getLocation();
			PlantNode<String> node = new PlantNode<String>(fsgName,
					DigitalInventoryConstants.LOCATION_TYPE_FSG);
			getRecursive(node, fsgName);
			setCustomerNodesToFSGNode(node, fsgName);
			childrenList.add(node);
		}
		rootElement.setChildren(childrenList);
		plantTree.setRootElement(rootElement);
		return plantTree;
	}

	private void getRecursive(PlantNode<String> node, String fsgName) {
		List<IInventoryDrillDownEntity> fsgToFSGEntityList = iPlantInventoryDrillDownRepository
				.getFSGToFSGNames(fsgName);
		List<PlantNode<String>> childElements = new ArrayList<PlantNode<String>>();
		for (IInventoryDrillDownEntity entity : fsgToFSGEntityList) {
			PlantNode<String> childElement = new PlantNode<String>(
					entity.getLocation(),
					DigitalInventoryConstants.LOCATION_TYPE_FSG);
			childElements.add(childElement);
		}
		node.setChildren(childElements);
	}

	private void setCustomerNodesToFSGNode(PlantNode<String> node,
			String fsgName) {
		List<IInventoryDrillDownEntity> fsgCustomerEntityList = iPlantInventoryDrillDownRepository
				.getFSGToCustomerNames1(fsgName);
		for (IInventoryDrillDownEntity entity : fsgCustomerEntityList)
			node.addChild(new PlantNode<String>(entity.getLocation(),
					DigitalInventoryConstants.LOCATION_TYPE_CUSTOMER));
	}

}
