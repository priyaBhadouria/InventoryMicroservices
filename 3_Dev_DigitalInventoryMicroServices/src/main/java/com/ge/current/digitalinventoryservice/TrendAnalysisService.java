/**
 * 
 */
package com.ge.current.digitalinventoryservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.current.digitalinventoryservice.dto.TIShipmentTransitConsumedDetailsDTO;
import com.ge.current.digitalinventoryservice.dto.TrendAnalysisDetailsDTO;
import com.ge.current.digitalinventoryservice.repository.ITrendAnalysisRepository;
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
import com.ge.current.digitalinventoryservice.util.DigitalInventoryConstants;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author ncheredd
 *
 */
@RestController
@RequestMapping("/div/trendanalysis")
public class TrendAnalysisService 
{
	@Autowired
	OauthVerification oAuth;
	
	@Autowired
	private ITrendAnalysisRepository iTrendAnalysisRepository;
	
	@RequestMapping("/demandsupply/graphsummary")
	public @ResponseBody TrendAnalysisDetailsDTO getDemandSupplyGraphSummaryDetails(HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
			TrendAnalysisDetailsDTO trendAnalysisDetailsDTO = new TrendAnalysisDetailsDTO();
			trendAnalysisDetailsDTO.setInventoryGraphResponse(getInventoryGraphSummaryDetails());
			trendAnalysisDetailsDTO.setInTransitGraphResponse(getInTransitGraphSummaryDetails());
			trendAnalysisDetailsDTO.setDemandGraphResponse(getDemandGraphSummaryDetails());
			trendAnalysisDetailsDTO.setShortageGraphResponse(getShortageGraphSummaryDetails());
			return trendAnalysisDetailsDTO;
	}
	
	@RequestMapping("/demandsupply/drilldown")
	public @ResponseBody List<TrendAnalysisDetailsDTO> getDemandSupplyDrillDownDetails(@Param("date") String date, HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
			Date formatedDate = UtilManager.convertStringToDate(date);
			Map<String, TrendAnalysisDetailsDTO> dsDrillDownDetailsMap = new HashMap();
			populateInventoryDrillDownDetails(dsDrillDownDetailsMap, formatedDate);
			populateInTransitDrillDownDetails(dsDrillDownDetailsMap, formatedDate);
			populateDemandShortageDrillDownDetails(dsDrillDownDetailsMap, formatedDate);
			return new ArrayList<TrendAnalysisDetailsDTO>(dsDrillDownDetailsMap.values());
	}
	
	@RequestMapping("/demandsupply/inventorypopup")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getDemandSupplyInventoryPopUpDetails(@Param("material") String material, @Param("date") String date, HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
			Date formatedDate = UtilManager.convertStringToDate(date);
			List<TIShipmentTransitConsumedDetailsDTO> inventoryAtFSGDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
			List<InventoryStockHistoryDetailsEntity> entityList = this.iTrendAnalysisRepository.getInventoryPopUpDetails(material, formatedDate);
			for(InventoryStockHistoryDetailsEntity entity: entityList)
			{
				Double quantity = entity.getQuantity();
				if(UtilManager.isValidInteget(quantity))
				{
					TIShipmentTransitConsumedDetailsDTO atFSGDTO = new TIShipmentTransitConsumedDetailsDTO();
					atFSGDTO.setMaterial(entity.getMaterial());
					atFSGDTO.setMaterialDescription(entity.getMaterialDescription());
					atFSGDTO.setQuantity(UtilManager.convertDoubleToInteger(quantity));
					atFSGDTO.setProductCost(entity.getMaterialCost());
					atFSGDTO.setUnitOfMeasure(entity.getUnitOfMeasure());
					atFSGDTO.setLocation(entity.getLocation());
					atFSGDTO.setTransactionDate(entity.getDate().toString());			
					inventoryAtFSGDTOs.add(atFSGDTO);
				}
			}
			return inventoryAtFSGDTOs;
	}
	
	@RequestMapping("/demandsupply/intransitpopup")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getDemandSupplyInTransitPopUpDetails(@Param("material") String material, @Param("date") String date, HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
			Date formatedDate = UtilManager.convertStringToDate(date);
			List<TIShipmentTransitConsumedDetailsDTO> inventoryTransitDetailsDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
			List<InTransitInventoryHistoryDetailsEntity> entityList = this.iTrendAnalysisRepository.getInTransitPopUpDetails(material, formatedDate);
			for(InTransitInventoryHistoryDetailsEntity entity: entityList)
			{
				Double quantity = entity.getQuantity();
				if(UtilManager.isValidInteget(quantity))
				{
					TIShipmentTransitConsumedDetailsDTO transitDetailsDTO = new TIShipmentTransitConsumedDetailsDTO();
					String fromLocation = entity.getFromlocation();
					transitDetailsDTO.setLocation(entity.getTolocation());
					UtilManager.setFromLocation(transitDetailsDTO, fromLocation);
					transitDetailsDTO.setQuantity(UtilManager.convertDoubleToInteger(quantity));
					transitDetailsDTO.setMaterial(entity.getMaterial());
					transitDetailsDTO.setMaterialDescription(entity.getMaterialDesc());
					transitDetailsDTO.setUnitOfMeasure(entity.getUnitOfMeasure());
					transitDetailsDTO.setProNumber(entity.getProNumber());
					transitDetailsDTO.setCarrierName(entity.getCarrierName());
					transitDetailsDTO.setProductCost(UtilManager.formatProductCost(entity.getMaterialCost()));
					transitDetailsDTO.setTransactionDate(entity.getDate().toString());
					inventoryTransitDetailsDTOs.add(transitDetailsDTO);
				}
				
			}
			return inventoryTransitDetailsDTOs;
	}
	
	@RequestMapping("/demandsupply/demandpopup")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getDemandSupplyDemandPopUpDetails(@Param("material") String material, @Param("date") String date, HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
			Date formatedDate = UtilManager.convertStringToDate(date);
			List<TIShipmentTransitConsumedDetailsDTO> customerMaterialDemandDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
			List<DemandShortageHistoryDetailsEntity> entityList = this.iTrendAnalysisRepository.getDemandShortagePopUpDetails(material, formatedDate);
			for(DemandShortageHistoryDetailsEntity entity: entityList)
			{
				Double demandQty = entity.getDemand();
				if(UtilManager.isValidInteget(demandQty))
				{
					TIShipmentTransitConsumedDetailsDTO materialDemandDTO = new TIShipmentTransitConsumedDetailsDTO();
					materialDemandDTO.setDemandQty(UtilManager.convertDoubleToInteger(demandQty));
	//				materialDemandDTO.setShortageQty(UtilManager.convertDoubleToIntString(entity.getShortage()));
					materialDemandDTO.setMaterial(entity.getMaterial());
					materialDemandDTO.setMaterialDescription(entity.getMaterialDesc());
					materialDemandDTO.setCustomerCode(entity.getCustomerId());
					materialDemandDTO.setDemandWeek(String.valueOf(entity.getFiscalWeek()));
					materialDemandDTO.setLocation(entity.getFsgLocation());
					materialDemandDTO.setYear(entity.getYear().toString());
					materialDemandDTO.setTransactionDate(entity.getDate().toString());
					customerMaterialDemandDTOs.add(materialDemandDTO);
				}
			}
			return customerMaterialDemandDTOs;
		
	}
	
	@RequestMapping("/demandsupply/shortagepopup")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getDemandSupplyShortagePopUpDetails(@Param("material") String material, @Param("date") String date, HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);			
		Date formatedDate = UtilManager.convertStringToDate(date);
			List<TIShipmentTransitConsumedDetailsDTO> customerMaterialShortageDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
			List<DemandShortageHistoryDetailsEntity> entityList = this.iTrendAnalysisRepository.getDemandShortagePopUpDetails(material, formatedDate);
			for(DemandShortageHistoryDetailsEntity entity: entityList)
			{
				Double shortageQty = entity.getShortage();
				if(UtilManager.isValidInteget(shortageQty))
				{
					TIShipmentTransitConsumedDetailsDTO materialShortageDTO = new TIShipmentTransitConsumedDetailsDTO();
	//				materialDemandDTO.setDemandQty(UtilManager.convertDoubleToIntString(demandQty));
					materialShortageDTO.setShortageQty(UtilManager.convertDoubleToInteger(shortageQty));
					materialShortageDTO.setMaterial(entity.getMaterial());
					materialShortageDTO.setMaterialDescription(entity.getMaterialDesc());
					materialShortageDTO.setCustomerCode(entity.getCustomerId());
					materialShortageDTO.setDemandWeek(String.valueOf(entity.getFiscalWeek()));
					materialShortageDTO.setLocation(entity.getFsgLocation());
					materialShortageDTO.setYear(entity.getYear().toString());
					materialShortageDTO.setTransactionDate(entity.getDate().toString());
					customerMaterialShortageDTOs.add(materialShortageDTO);
				}
			}
			return customerMaterialShortageDTOs;
		
	}
	
	private void populateInventoryDrillDownDetails(
			Map<String, TrendAnalysisDetailsDTO> dsDrillDownDetailsMap, Date formatedDate)
	{
		List<DSInventoryDrillDownDetailsEntity> inventoryEntityList = iTrendAnalysisRepository.getInventoryDrillDownDetails(formatedDate);
		for(DSInventoryDrillDownDetailsEntity entity : inventoryEntityList)
		{
			Double inventoryQty = entity.getQuantity();
			if(UtilManager.isValidInteget(inventoryQty))
			{
				String material = entity.getMaterial();
				TrendAnalysisDetailsDTO dto = new TrendAnalysisDetailsDTO();
				dto.setMaterial(material);
				dto.setMaterialDesc(entity.getMaterialDesc());
				dto.setInventory(UtilManager.convertDoubleToInteger(inventoryQty));
				dto.setInTransit(DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE);
				dto.setDemand(DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE);
				dto.setShortage(DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE);
				dsDrillDownDetailsMap.put(material, dto);
			}
		}
	}
	
	private void populateInTransitDrillDownDetails(
			Map<String, TrendAnalysisDetailsDTO> dsDrillDownDetailsMap, Date date) 
	{
		List<DSInTransitDrillDownDetailsEntity> inTransitEntityList = iTrendAnalysisRepository.getInTransitDrillDownDetails(date);
		for(DSInTransitDrillDownDetailsEntity entity : inTransitEntityList)
		{
			Double intransitQty = entity.getQuantity();
			if(UtilManager.isValidInteget(intransitQty))
			{
				TrendAnalysisDetailsDTO dto = null;
				String material = entity.getMaterial();
				if(dsDrillDownDetailsMap.containsKey(material))
				{
					dto = dsDrillDownDetailsMap.get(material);
				}
				else
				{
					dto = new TrendAnalysisDetailsDTO();
					dto.setMaterial(material);
					dto.setMaterialDesc(entity.getMaterialDesc());
					dto.setInventory(DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE);				
					dto.setDemand(DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE);
					dto.setShortage(DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE);
					
				}
				dto.setInTransit(UtilManager.convertDoubleToInteger(intransitQty));
				dsDrillDownDetailsMap.put(material, dto);
			}
		}
		
	}
	
	private void populateDemandShortageDrillDownDetails(
			Map<String, TrendAnalysisDetailsDTO> dsDrillDownDetailsMap, Date date) 
	{
		List<DSDemandShortageDrillDownDetailsEntity> demandShortageEntityList = iTrendAnalysisRepository.getDemandShortageDrillDownDetails(date);
		for(DSDemandShortageDrillDownDetailsEntity entity : demandShortageEntityList)
		{
			Double demandQty = entity.getDemand();
			Double shortageQty = entity.getShortage();
			if(UtilManager.isValidInteget(demandQty) || UtilManager.isValidInteget(shortageQty))
			{
				TrendAnalysisDetailsDTO dto = null;
				String material = entity.getMaterial();				
				if(dsDrillDownDetailsMap.containsKey(material))
				{
					dto = dsDrillDownDetailsMap.get(material);
				}
				else
				{
					dto = new TrendAnalysisDetailsDTO();
					dto.setMaterial(material);
					dto.setMaterialDesc(entity.getMaterialDesc());
					dto.setInventory(DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE);
					dto.setInTransit(DigitalInventoryConstants.QUANTITY_DEFAULT_INTEGER_VALUE);
				}
				dto.setDemand(UtilManager.convertDoubleToInteger(demandQty));
				dto.setShortage(UtilManager.convertDoubleToInteger(shortageQty));
				dsDrillDownDetailsMap.put(material, dto);
			}
		}
		
	}

	public String getInventoryGraphSummaryDetails() 
	{
		StringBuffer jsonResponse = new StringBuffer();
		List<DemandSupplyInventoryGraphDetailsEntity> entityList = this.iTrendAnalysisRepository.getInventoryGraphDetails();
		jsonResponse.append(DigitalInventoryConstants.LEFT_BRACKET);
		
		for(DemandSupplyInventoryGraphDetailsEntity entity : entityList)
			prepareJsonStringArray(jsonResponse, entity.getDate(),entity.getStockQty());
		
		if(jsonResponse.length()>1)
		jsonResponse = jsonResponse.deleteCharAt(jsonResponse.length()-1);
		jsonResponse.append(DigitalInventoryConstants.RIGHT_BRACKET);
		return jsonResponse.toString();
	}
	

	public String getInTransitGraphSummaryDetails() 
	{
		StringBuffer jsonResponse = new StringBuffer();
		List<DemandSupplyInTransitGraphDetailsEntity> entityList = this.iTrendAnalysisRepository.getInTransitGraphDetails();
		jsonResponse.append(DigitalInventoryConstants.LEFT_BRACKET);
		
		for(DemandSupplyInTransitGraphDetailsEntity entity : entityList)
			prepareJsonStringArray(jsonResponse, entity.getDate(),entity.getInTransitQty());
		
		if(jsonResponse.length()>1)
		jsonResponse = jsonResponse.deleteCharAt(jsonResponse.length()-1);
		jsonResponse.append(DigitalInventoryConstants.RIGHT_BRACKET);
		return jsonResponse.toString();
	}
	

	public String getDemandGraphSummaryDetails() 
	{
		StringBuffer jsonResponse = new StringBuffer();
		List<DemandSupplyDemandGraphDetailsEntity> entityList = this.iTrendAnalysisRepository.getDemandGraphDetails();
		jsonResponse.append(DigitalInventoryConstants.LEFT_BRACKET);
		
		for(DemandSupplyDemandGraphDetailsEntity entity : entityList)
			prepareJsonStringArray(jsonResponse, entity.getDate(),entity.getDemandQty());
		
		if(jsonResponse.length()>1)
		jsonResponse = jsonResponse.deleteCharAt(jsonResponse.length()-1);
		jsonResponse.append(DigitalInventoryConstants.RIGHT_BRACKET);
		return jsonResponse.toString();
	}
	

	public String getShortageGraphSummaryDetails() 
	{
		StringBuffer jsonResponse = new StringBuffer();
		List<DemandSupplyShortageGraphDetailsEntity> entityList = this.iTrendAnalysisRepository.getShortageGraphDetails();
		jsonResponse.append(DigitalInventoryConstants.LEFT_BRACKET);
		
		for(DemandSupplyShortageGraphDetailsEntity entity : entityList)
			prepareJsonStringArray(jsonResponse, entity.getDate(),entity.getShortageQty());
		
		if(jsonResponse.length()>1)
		jsonResponse = jsonResponse.deleteCharAt(jsonResponse.length()-1);
		jsonResponse.append(DigitalInventoryConstants.RIGHT_BRACKET);
		return jsonResponse.toString();
	}

	private void prepareJsonStringArray(StringBuffer jsonResponse, String date, Double quantity) 
	{
		jsonResponse.append(DigitalInventoryConstants.LEFT_BRACKET);
		jsonResponse.append(UtilManager.convertStringDateToMilliSeconds(date));
		jsonResponse.append(DigitalInventoryConstants.COMMA);
		jsonResponse.append(quantity);
		jsonResponse.append(DigitalInventoryConstants.RIGHT_BRACKET);
		jsonResponse.append(DigitalInventoryConstants.COMMA);
	}
}
