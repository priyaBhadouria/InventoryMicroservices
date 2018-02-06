/**
 * 
 */
package com.ge.current.digitalinventoryservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.current.digitalinventoryservice.dto.FSGMaterialDTO;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGConsumptionRateOverViewEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGCustomerDemandEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGMaterialConsumptionRateEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.TotalConsumptionRateEntity;
import com.ge.current.digitalinventoryservice.repository.IInventoryDrilldownRepository;
import com.ge.current.digitalinventoryservice.repository.TotalConsumptionRateRepository;
import com.ge.current.digitalinventoryservice.totalTransaction.dto.CustomerConsumptionDTO;
import com.ge.current.digitalinventoryservice.totalTransaction.dto.InventoryDrilldownDTO;
import com.ge.current.digitalinventoryservice.totalTransaction.dto.TreeDataOnCustomerLevelDTO;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.ConsumptionSummaryEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.CustomerConsumptionEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.CustomerConsumptionOverviewEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.InventoryDrilldownEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.ReturnedEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeAggrigateDataForCustomerLevelEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeDataAtFSGLevelEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeDataOnCustomerLevelEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeDataTablePlantLevelEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeIntransitToCustomerEntity;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TreeIntransitToFSGAndStockAtFSGEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventorySummaryEntity;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author vs
 *
 */
@RestController
@RequestMapping("/DIVServices")
public class DigitalInventoryVisibilityServices {

	@Autowired
	OauthVerification oAuth;

	@Autowired
	private TotalConsumptionRateRepository totalConsumptionRateRepo;

	@Autowired
	private IInventoryDrilldownRepository iInventoryDrilldownRepository;

	@RequestMapping(value = "/getTreeDataAtFSGLevel", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody TreeDataAtFSGLevelEntity getTreeDataAtFSGLevel(
			@RequestParam(name = "location") String location,
			HttpServletRequest request, HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		TreeDataAtFSGLevelEntity atFSGLevelEntity = new TreeDataAtFSGLevelEntity();
		TreeIntransitToCustomerEntity intransitToCustomerQuantity = iInventoryDrilldownRepository
				.getTreeIntransitToCustomer(location);
		TreeIntransitToFSGAndStockAtFSGEntity intransitfsgandstock = iInventoryDrilldownRepository
				.getTreeIntransitToFSGAndStockAtFSG(location);

		if (intransitToCustomerQuantity != null) {
			atFSGLevelEntity
					.setIntransitToCustomerQuantity(intransitToCustomerQuantity
							.getIntransitToCustomerQuantity());
		}
		if (intransitfsgandstock != null) {
			atFSGLevelEntity.setIntransitToFSGQuantity(intransitfsgandstock
					.getIntransitToFSGQuantity());
			atFSGLevelEntity.setStockAtFSGQuantity(intransitfsgandstock
					.getStockAtFSGQuantity());
		}
		return atFSGLevelEntity;
	}

	@RequestMapping(value = "/getCustomerTableDataByCustomer", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TreeDataOnCustomerLevelDTO> getCustomerTableDataByCustomer(
			@RequestParam(name = "customer") String customer,
			HttpServletRequest request, HttpServletResponse response) 
	{
		 oAuth.oAuthValidator1(request, response);
		List<TreeDataOnCustomerLevelDTO> treeDataOnCustomerLevelEntities = new ArrayList<TreeDataOnCustomerLevelDTO>();
		TreeIntransitToFSGAndStockAtFSGEntity intransitfsgandstock = iInventoryDrilldownRepository
				.getTreeIntransitToFSGAndStockAtFSG(customer);
		if (intransitfsgandstock != null) { // if the input is FSG
			String fsgName = intransitfsgandstock.getLocation();
			if (fsgName.equals(customer) || fsgName.equalsIgnoreCase(customer)) {
				List<InventoryDrilldownEntity> drilldownEntities = this.iInventoryDrilldownRepository
						.getInventoryDrilldownDetailsByFSG(customer);

				for (InventoryDrilldownEntity inventoryDrilldownEntity : drilldownEntities) {
					TreeDataOnCustomerLevelDTO onCustomerLevelEntity = new TreeDataOnCustomerLevelDTO();
					onCustomerLevelEntity.setCustomer(inventoryDrilldownEntity
							.getLocation());
					onCustomerLevelEntity
							.setCurrentQty(inventoryDrilldownEntity
									.getQuantity());
					onCustomerLevelEntity.setMaterial(inventoryDrilldownEntity
							.getMaterial());
					onCustomerLevelEntity
							.setMaterialDescription(inventoryDrilldownEntity
									.getMaterialDescription());
					onCustomerLevelEntity.setUom(inventoryDrilldownEntity
							.getUom());
					onCustomerLevelEntity.setSource(inventoryDrilldownEntity
							.getSource());
					onCustomerLevelEntity.setIsFSG("FSG");
					treeDataOnCustomerLevelEntities.add(onCustomerLevelEntity);
				}

				return treeDataOnCustomerLevelEntities;
			}
		} else { // if the input(customer) is customer
			treeDataOnCustomerLevelEntities = new ArrayList<TreeDataOnCustomerLevelDTO>();
			List<TreeDataOnCustomerLevelEntity> customerDescription = iInventoryDrilldownRepository
					.getCustomerTableDataByCustomerId(customer);
			TreeDataOnCustomerLevelDTO onCustomerLevelEntityOne = null;
			if (customerDescription != null) 
			{
				for (TreeDataOnCustomerLevelEntity treeDataOnCustomerLevelEntity : customerDescription) 
				{
					Double currentQty = treeDataOnCustomerLevelEntity
							.getCurrentQty();
					Double intransitQty = treeDataOnCustomerLevelEntity
							.getIntransitQty();
					if (UtilManager.isValidInteget(currentQty)
							|| UtilManager.isValidInteget(intransitQty)) {
						onCustomerLevelEntityOne = new TreeDataOnCustomerLevelDTO();
						onCustomerLevelEntityOne
								.setCustomer(treeDataOnCustomerLevelEntity
										.getCustomer());
						onCustomerLevelEntityOne.setCurrentQty(currentQty);
						onCustomerLevelEntityOne.setIntransitQty(intransitQty);
						onCustomerLevelEntityOne
								.setMaterial(treeDataOnCustomerLevelEntity
										.getMaterial());
						onCustomerLevelEntityOne
								.setMaterialDescription(treeDataOnCustomerLevelEntity
										.getMaterialDescription());
						onCustomerLevelEntityOne.setIsFSG("CUSTOMER");
						treeDataOnCustomerLevelEntities.add(onCustomerLevelEntityOne);
					}
				}
				return treeDataOnCustomerLevelEntities;
			}
		}
		return treeDataOnCustomerLevelEntities;
	}

	@RequestMapping("/getTotalInventorySummary")
	public @ResponseBody TotalInventorySummaryEntity getTotalInventorySummaryDetails(
			HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
		TotalInventorySummaryEntity result = new TotalInventorySummaryEntity();
		result = this.iInventoryDrilldownRepository.getTotalInventorySummary();
		return result;
	}

	@RequestMapping("/getConsumptionSummary")
	public @ResponseBody List<ConsumptionSummaryEntity> getConsumptionSummaryDetails(
			HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
		List<ConsumptionSummaryEntity> list = new ArrayList<ConsumptionSummaryEntity>();
		list = iInventoryDrilldownRepository.getConsumptionSummary();
		return list;
	}

	@RequestMapping("/getCustomerConsumptionView")
	public @ResponseBody CustomerConsumptionOverviewEntity getCustomerConsumptionView(
			HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
		CustomerConsumptionOverviewEntity result = new CustomerConsumptionOverviewEntity();
		result = this.iInventoryDrilldownRepository.getCustomerConsumptionView();
		return result;
	}

	@RequestMapping("/getTreeTableDataPlantLevel")
	public @ResponseBody List<TreeDataTablePlantLevelEntity> getTreeTableDataPlantLevel(
			@RequestParam(name = "plant") String plant,
			HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
		List<TreeDataTablePlantLevelEntity> result = new ArrayList<TreeDataTablePlantLevelEntity>();
		result = this.iInventoryDrilldownRepository.getTreeTableDataPlantLevel(plant);
		return result;
	}

	@RequestMapping("/getReturned")
	public @ResponseBody List<ReturnedEntity> getReturned(
			HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
		List<ReturnedEntity> result = new ArrayList<ReturnedEntity>();
		result = this.iInventoryDrilldownRepository.getReturned();
		return result;
	}

	@RequestMapping(value = "/getInventoryDrilldownDetailsByFSG", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<InventoryDrilldownDTO> getInventoryDrilldownDetailsByFSG(
			@RequestParam(name = "source") String source,
			HttpServletRequest request, HttpServletResponse response) 
	{
		 oAuth.oAuthValidator1(request, response);
		List<InventoryDrilldownDTO> drilldownDTOList = new ArrayList<InventoryDrilldownDTO>();
		List<InventoryDrilldownEntity> drilldownEntities = this.iInventoryDrilldownRepository.getInventoryDrilldownDetailsByFSG(source);
		for (InventoryDrilldownEntity inventoryDrilldownEntity : drilldownEntities) 
		{
			InventoryDrilldownDTO drilldownDTO = new InventoryDrilldownDTO();
			drilldownDTO.setMaterial(inventoryDrilldownEntity.getMaterial());
			drilldownDTO.setMaterialDescription(inventoryDrilldownEntity
					.getMaterialDescription());
			drilldownDTO.setLocation(inventoryDrilldownEntity.getLocation());
			drilldownDTO.setSource(inventoryDrilldownEntity.getSource());
			drilldownDTO.setQuantity(inventoryDrilldownEntity.getQuantity());
			drilldownDTO.setUom(inventoryDrilldownEntity.getUom());
			drilldownDTOList.add(drilldownDTO);
		}
		return drilldownDTOList;
	}

	@RequestMapping(value = "/getInventoryDrilldownDetailsByCustomer", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TreeAggrigateDataForCustomerLevelEntity> getInventoryDrilldownDetailsByCustomer(
			@RequestParam(name = "customer") String customer,
			HttpServletRequest request, HttpServletResponse response) 
	{
		 oAuth.oAuthValidator1(request, response);
		 List<TreeAggrigateDataForCustomerLevelEntity> aggrigateDataForCustomerLevelEntities = new ArrayList<TreeAggrigateDataForCustomerLevelEntity>();
		 aggrigateDataForCustomerLevelEntities = this.iInventoryDrilldownRepository.getInventoryAggrigateDataByCustomer(customer);
		 return aggrigateDataForCustomerLevelEntities;
	}

	@RequestMapping(value = "/getCustomerConsumptionTable", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<CustomerConsumptionDTO> getCustomerConsumption(
			@RequestParam(name = "state") String state,
			HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
		List<CustomerConsumptionDTO> consumptionDTOList = new ArrayList<CustomerConsumptionDTO>();
		List<CustomerConsumptionEntity> customerConsumption = this.iInventoryDrilldownRepository.getCustomerConsumption(state);
		for (CustomerConsumptionEntity customerConsumptionEntity : customerConsumption) 
		{
			CustomerConsumptionDTO consumptionDTO = new CustomerConsumptionDTO();

			consumptionDTO.setCurrentQty(customerConsumptionEntity
					.getCurrentQty());
			consumptionDTO.setCustomerSite(customerConsumptionEntity
					.getCustomerSite());
			consumptionDTO.setMaterial(customerConsumptionEntity.getMaterial());
			consumptionDTO.setMaterialDescription(customerConsumptionEntity
					.getMaterialDescription());
			consumptionDTO
					.setLastDate(convertDatetoString(customerConsumptionEntity
							.getLastDate()));
			consumptionDTO.setCustomerDesc(customerConsumptionEntity
					.getCustomerDesc());
			consumptionDTO.setReturnedQty(customerConsumptionEntity
					.getReturnedQty());
			consumptionDTO.setState(customerConsumptionEntity.getState());
			consumptionDTO.setFsgServedLocation(customerConsumptionEntity.getFsgServedLocation());
			consumptionDTO.setVariance(customerConsumptionEntity.getVariance());
			consumptionDTOList.add(consumptionDTO);

		}
		return consumptionDTOList;
	}

	public String convertDatetoString(Date dateInput) {
		String stringDate = null;
		if (dateInput != null) {
			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			stringDate = dateFormat.format(dateInput);
		}
		return stringDate;
	}

	@RequestMapping("/getEachFSGConsumptionRate")
	public @ResponseBody List<FSGConsumptionRateOverViewEntity> getTotalFSGConsumptionRate(
			HttpServletRequest request, HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		return totalConsumptionRateRepo.getTotalFSGConsumptionSummary();
	}

	@RequestMapping("/getFsgMaterialConsumptionRate")
	public @ResponseBody List<FSGMaterialConsumptionRateEntity> getFsgMaterialConsumptionRate(
			HttpServletRequest request, HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		return totalConsumptionRateRepo.getFSGMaterialConsumptionSummary();
	}

	@RequestMapping("/getTotalConsumptionRate")
	public @ResponseBody TotalConsumptionRateEntity getTotalConsumptionRate(
			HttpServletRequest request, HttpServletResponse response) {

		 oAuth.oAuthValidator1(request, response);
		return totalConsumptionRateRepo.getTotalConsumptionSummary();
	}

	@RequestMapping(value = "/getFSGMaterialCustomerDemand1", method = RequestMethod.POST)
	public @ResponseBody List<FSGCustomerDemandEntity> getFSGMaterialCustomerDemand(
			@RequestBody FSGMaterialDTO fsgMaterialDto,
			HttpServletRequest request, HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		String fsgLocation = fsgMaterialDto.getFsgLocation();
		String material = fsgMaterialDto.getMaterialId();
		String fiscalWeek = fsgMaterialDto.getFiscalWeek();
		String year = fsgMaterialDto.getYear();
		return totalConsumptionRateRepo.getFSGMaterialCustomerDemand(
				fsgLocation, material, Integer.valueOf(fiscalWeek),
				Integer.valueOf(year));
	}
}
