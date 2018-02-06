/**
 * 
 */
package com.ge.current.digitalinventoryservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.testng.IInstanceInfo;

import com.ge.current.digitalinventoryservice.dto.TIDollarValueSummaryDTO;
import com.ge.current.digitalinventoryservice.dto.TIShipmentTransitConsumedDetailsDTO;
import com.ge.current.digitalinventoryservice.repository.IInstallerReceivedRepository;
import com.ge.current.digitalinventoryservice.repository.ITotalInventoryRepository;
import com.ge.current.digitalinventoryservice.totalTransaction.dto.TotalFSGSummaryDTO;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TotalFSGSummaryEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.CustomerConsumedInventoryDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.CustomerDemandInventoryDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.CustomerMaterialDemandDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.CustomerMaterialDemandSummaryEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.InstallerReceivedEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.InventoryTransitToCustomerDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.InventoryTransitToFSGDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TIShipmentDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventoryAtCustomerDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventoryAtFSGDetailsEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventoryDollarValueSummaryEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalInventorySummaryEntity;
import com.ge.current.digitalinventoryservice.totalinventory.entity.TotalNetInventoryAtFSGDetailsEntity;
import com.ge.current.digitalinventoryservice.util.DigitalInventoryConstants;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author ncheredd
 *
 */
@RestController
@RequestMapping("/div/totalinventory")
public class TotalInventoryServices {
	private static final long serialVersionUID = 1L;

	@Autowired
	OauthVerification oAuth;

	@Autowired
	private ITotalInventoryRepository totalInventoryService;
	
	@Autowired
	private IInstallerReceivedRepository   installerReceivedService;

	@RequestMapping("/getTotalInventorySummary")
	public @ResponseBody TotalInventorySummaryEntity getTotalInventorySummaryDetails(HttpServletRequest request,
			HttpServletResponse response) {

		oAuth.oAuthValidator1(request, response);
		return this.totalInventoryService.getTotalInventorySummary();
	}

	@RequestMapping(value="/getTotalFSGSummary" , method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TotalFSGSummaryDTO> getTotalFSGSummaryDetails(HttpServletRequest request,
                                    HttpServletResponse response) {

                    oAuth.oAuthValidator1(request, response);
                    
                    Double transit=0.0;
                    
                    List<TotalFSGSummaryEntity> fsgSummaryEntities = new ArrayList<TotalFSGSummaryEntity>();
                    fsgSummaryEntities = this.totalInventoryService.getTotalFSGSummary();
                    System.out.println("Data..."+fsgSummaryEntities);
                   List<TotalFSGSummaryDTO> fsgSummaryDTOs = new ArrayList<TotalFSGSummaryDTO>();
                    for (TotalFSGSummaryEntity entity : fsgSummaryEntities) {
                                    TotalFSGSummaryDTO summaryDTO = new TotalFSGSummaryDTO();
                                    
                                    summaryDTO.setFsg_location(entity.getFsgLocation());                                   
                                    summaryDTO.setLocation_desc(entity.getFsgDesc());
                                    summaryDTO.setOnHand(entity.getOnHand());
                                    summaryDTO.setOnHandCost(UtilManager.convertNumberToMillions(entity.getOnHandCost()));
                                    summaryDTO.setShortage(entity.getFsgShortage());
                                    summaryDTO.setShortageCost(UtilManager.convertNumberToMillions(entity.getFsgShortageCost()));
                                    summaryDTO.setDemand(entity.getFsgDemand());
                                    summaryDTO.setDemandCost(UtilManager.convertNumberToMillions(entity.getFsgDemandCost()));
                                    if(entity.getFsgTransit()!=null)
                                    {
                                    	 summaryDTO.setInTransit(entity.getFsgTransit());
                                    	 summaryDTO.setInTransitCost(UtilManager.convertNumberToMillions(entity.getFsgTransitCost()));
                                    }
                                    else
                                    {
                                     summaryDTO.setInTransit(transit);
                                   	 summaryDTO.setInTransitCost(UtilManager.convertNumberToMillions(transit));
                                    }
                                    fsgSummaryDTOs.add(summaryDTO);
                    }
                 
                    return fsgSummaryDTOs;
    }

	
	@RequestMapping(value = "/getTIShipmentDetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getTIShipmentConsumedDrillDown(
			HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> shipmentDTOList = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
		List<TIShipmentDetailsEntity> shipmentDetailsList = this.totalInventoryService
				.getTotalInventoryShipmentDetails();
		for (TIShipmentDetailsEntity entity : shipmentDetailsList) {
			Double quantity = entity.getQuantity();
			if (UtilManager.isValidInteget(quantity)) {
				TIShipmentTransitConsumedDetailsDTO stockDTO = new TIShipmentTransitConsumedDetailsDTO();
				stockDTO.setMaterial(entity.getMaterial());
				stockDTO.setMaterialDescription(entity.getMaterialDescription());
				stockDTO.setUnitOfMeasure(entity.getUnitOfMeasure());
				stockDTO.setLocation(entity.getLocation());
				stockDTO.setProductCost(UtilManager.formatProductCost(entity.getMaterialCost()));
				stockDTO.setQuantity(UtilManager.convertDoubleToInteger(quantity));
				setStatus(entity.getStatus(), stockDTO);
				shipmentDTOList.add(stockDTO);
			}
		}
		return shipmentDTOList;
	}

	private void setStatus(Character status, TIShipmentTransitConsumedDetailsDTO stockDTO) {
		if (status != null && status == DigitalInventoryConstants.STATUS_S)
			stockDTO.setStatus(DigitalInventoryConstants.STOCK_STATUS);
		else
			stockDTO.setStatus(DigitalInventoryConstants.SHIPMENT_STATUS);
	}

	@RequestMapping(value = "/getTITransitToFSGDetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getTITransitDetails(HttpServletRequest request,
			HttpServletResponse response)

	{
		oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> inventoryTransitDetailsDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
		List<InventoryTransitToFSGDetailsEntity> inventoryTransitDetailsEntities = totalInventoryService
				.getInventoryTransitToFSGDetails();
		for (InventoryTransitToFSGDetailsEntity entity : inventoryTransitDetailsEntities) {
			Double quantity = entity.getQuantity();
			if (UtilManager.isValidInteget(quantity)) {
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
				inventoryTransitDetailsDTOs.add(transitDetailsDTO);
			}
		}
		return inventoryTransitDetailsDTOs;
	}

	/*@RequestMapping(value = "/getTIDetailsAtFSG", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getTIDetailsAtFSG(HttpServletRequest request,
			HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> inventoryAtFSGDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
		Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> stockEntityMap = new HashMap<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity>();
		Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> receivedkEntityMap = new HashMap<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity>();
		Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> issuedEntityMap = new HashMap<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity>();
		Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> netEntityMap = new HashMap<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity>();
		Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> boEntityMap = new HashMap<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity>();
		Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> comEntityMap = new HashMap<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity>();

		List<TotalInventoryAtFSGDetailsEntity> finalinventoryAtFSG = null;

		List<TotalInventoryAtFSGDetailsEntity> inventoryAtFSGEntities = totalInventoryService
				.getInventoryAtFSGDetails();
		populatingDataBasedOnStatus(inventoryAtFSGEntities, stockEntityMap, receivedkEntityMap, issuedEntityMap,
				netEntityMap, boEntityMap, comEntityMap);
		finalinventoryAtFSG = populateFinalInventoryAtFSG(stockEntityMap, receivedkEntityMap, issuedEntityMap,
				netEntityMap, boEntityMap, comEntityMap);
		for (TotalInventoryAtFSGDetailsEntity entity : finalinventoryAtFSG) {
			Double quantity = entity.getQuantity();
			if (UtilManager.isValidInteget(quantity)) {
				Double materialCost = entity.getCost();
				TIShipmentTransitConsumedDetailsDTO atFSGDTO = new TIShipmentTransitConsumedDetailsDTO();
				atFSGDTO.setLocation(entity.getLocation());
				atFSGDTO.setQuantity(UtilManager.convertDoubleToInteger(quantity));
				atFSGDTO.setMaterial(entity.getMaterial());
				atFSGDTO.setMaterialDescription(entity.getMaterialDescription());
				atFSGDTO.setUnitOfMeasure(entity.getUnitOfMeasure());
				atFSGDTO.setOrderStatus(entity.getOrderStatus());
				setProductCost(quantity, materialCost, atFSGDTO);
				inventoryAtFSGDTOs.add(atFSGDTO);
			}
		}
		return inventoryAtFSGDTOs;
	}*/
	@RequestMapping(value = "/getTIDetailsAtFSG", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getTIDetailsAtFSG(HttpServletRequest request,
			HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> inventoryAtFSGDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
		
		List<TotalNetInventoryAtFSGDetailsEntity> netInventoryEntities = totalInventoryService.getTotalNetInventoryDetails();
		
		
		for (TotalNetInventoryAtFSGDetailsEntity entity : netInventoryEntities) {
				TIShipmentTransitConsumedDetailsDTO inventoryAtFSGDTO = new TIShipmentTransitConsumedDetailsDTO();
				inventoryAtFSGDTO.setLocation(entity.getLocation());
				inventoryAtFSGDTO.setBackOrder(UtilManager.convertDoubleToInteger(entity.getBackOrder()));
				inventoryAtFSGDTO.setCommitted(UtilManager.convertDoubleToInteger(entity.getCommitted()));
				inventoryAtFSGDTO.setUnavailableStock(UtilManager.convertDoubleToInteger(entity.getUnAvailableStock()));

				//inventoryAtFSGDTO.setCurrentStock(UtilManager.convertDoubleToInteger(entity.getCurrentStock()));
				inventoryAtFSGDTO.setNetAvailable(UtilManager.convertDoubleToInteger(entity.getNetAvailable()));
				inventoryAtFSGDTO.setPreviousStock(UtilManager.convertDoubleToInteger(entity.getPreviousStock()));
				inventoryAtFSGDTO.setReceived(UtilManager.convertDoubleToInteger(entity.getReceived()));
				inventoryAtFSGDTO.setMaterial(entity.getMaterial());
				inventoryAtFSGDTO.setMaterialDescription(entity.getMaterialDescription());
				inventoryAtFSGDTO.setUnitOfMeasure(entity.getUnitOfMeasure());
				inventoryAtFSGDTO.setProductCost(UtilManager.formatProductCost(entity.getCost()));
				inventoryAtFSGDTOs.add(inventoryAtFSGDTO);
			
		}
		return inventoryAtFSGDTOs;
		
		
	}
	
	
	private void setProductCost(Double quantity, Double materialCost, TIShipmentTransitConsumedDetailsDTO atFSGDTO) {
		if (materialCost != null) {
			Double productCost = quantity * materialCost;
			productCost = (double) Math.round(productCost * 100.0) / 100.0;
			atFSGDTO.setProductCost(UtilManager.formatProductCost(productCost));
		} else
			atFSGDTO.setProductCost(DigitalInventoryConstants.PRODUCT_COST_DEFAULT_VALUE);
	}

	private List<TotalInventoryAtFSGDetailsEntity> populateFinalInventoryAtFSG(
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> stockEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> receivedkEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> issuedEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> netEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> boEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> comEntityMap) {
		List<TotalInventoryAtFSGDetailsEntity> finalinventoryAtFSG = new ArrayList<TotalInventoryAtFSGDetailsEntity>();

		System.out.println(
				"**********************************************************************************************");
		System.out.println("stockEntityMap :" + stockEntityMap.size());
		System.out.println("receivedkEntityMap :" + receivedkEntityMap.size());
		System.out.println("issuedEntityMap :" + issuedEntityMap.size());
		System.out.println(
				"**********************************************************************************************");

		for (Entry<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> entrySet : stockEntityMap
				.entrySet()) {
			TotalInventoryAtFSGDetailsEntity stock = entrySet.getValue();
			TotalInventoryAtFSGDetailsEntity recieved = receivedkEntityMap.get(entrySet.getKey());
			TotalInventoryAtFSGDetailsEntity issued = issuedEntityMap.get(entrySet.getKey());
			TotalInventoryAtFSGDetailsEntity net = netEntityMap.get(entrySet.getKey());
			TotalInventoryAtFSGDetailsEntity bo = boEntityMap.get(entrySet.getKey());
			TotalInventoryAtFSGDetailsEntity com = comEntityMap.get(entrySet.getKey());

			if (recieved != null && stock != null
					&& issued != null) /*
										 * case :1 - we have received material A
										 * and same day issued.
										 */
			{
				double stockQty = stock.getQuantity();
				double receivedQty = recieved.getQuantity();
				double issuedQty = issued.getQuantity();
				if (stockQty == 0) {
					recieved.setQuantity(0.0);
				} else if (stockQty != 0) {
					double yesterdayStock = stockQty + issuedQty - receivedQty;
					if (yesterdayStock < issuedQty) {
						receivedQty = yesterdayStock + receivedQty - issuedQty;
						recieved.setQuantity(receivedQty);
						stock.setQuantity(0.0);
						net.setQuantity(0.0);
					} else if (yesterdayStock > issuedQty)
					{
						stock.setQuantity(yesterdayStock - issuedQty);
						net.setQuantity(yesterdayStock - issuedQty-com.getQuantity());
					}
					else if (yesterdayStock == issuedQty)
						stock.setQuantity(0.0);
						net.setQuantity(0.0);
				}

			} else if (recieved != null && stock != null
					&& issued == null) /*
										 * case : 2 - we have received material
										 * A but not issued on same day .
										 */
			{
				double stockQty = stock.getQuantity();
				double receivedQuantity = recieved.getQuantity();
				stock.setQuantity(stockQty - receivedQuantity);
				net.setQuantity(stockQty - receivedQuantity -com.getQuantity());
				
			}
			stock.setOrderStatus(DigitalInventoryConstants.SET_STOCK_STATUS);
			finalinventoryAtFSG.add(stock);
			if (recieved != null)
				finalinventoryAtFSG.add(recieved);
			if (net != null)
			{
				net.setOrderStatus(DigitalInventoryConstants.SET_NET_STATUS);
				finalinventoryAtFSG.add(net);
			}
			if (bo != null)
				finalinventoryAtFSG.add(bo);
			if (com != null)
			{
				com.setOrderStatus(DigitalInventoryConstants.SET_COM_STATUS);
				finalinventoryAtFSG.add(com);
			}
		}

		return finalinventoryAtFSG;
	}

	private void populatingDataBasedOnStatus(List<TotalInventoryAtFSGDetailsEntity> inventoryAtFSGEntities,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> stockEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> receivedkEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> issuedEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> netEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> boEntityMap,
			Map<TotalInventoryAtFSGDetailsEntity, TotalInventoryAtFSGDetailsEntity> comEntityMap) {
		for (TotalInventoryAtFSGDetailsEntity entity : inventoryAtFSGEntities) {
			String status = entity.getOrderStatus();
			if (status.equals(DigitalInventoryConstants.STOCK_STATUS))
				stockEntityMap.put(entity, entity);
			else if (status.equals(DigitalInventoryConstants.RECEIVED_STATUS))
				receivedkEntityMap.put(entity, entity);
			else if (status.equals(DigitalInventoryConstants.ISSUED_STATUS))
				issuedEntityMap.put(entity, entity);
			else if (status.equals(DigitalInventoryConstants.BO_STATUS))
				boEntityMap.put(entity, entity);
			else if (status.equals(DigitalInventoryConstants.COM_STATUS))
				comEntityMap.put(entity, entity);
			else if (status.equals(DigitalInventoryConstants.NET_STATUS))
				netEntityMap.put(entity, entity);
		}

	}

	@RequestMapping(value = "/getTITransitToCustomerDetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getTITransitToCustomerDetails(
			HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> transitToCustomerDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
		List<InventoryTransitToCustomerDetailsEntity> transitToCustomerEntities = totalInventoryService
				.getInventoryTransitToCustomerDetails();
		for (InventoryTransitToCustomerDetailsEntity entity : transitToCustomerEntities) {
			Double qty = entity.getQuantity();
			if (UtilManager.isValidInteget(qty)) {
				TIShipmentTransitConsumedDetailsDTO transitDetailsDTO = new TIShipmentTransitConsumedDetailsDTO();
				transitDetailsDTO.setLocation(entity.getLocation());
				transitDetailsDTO.setQuantity(UtilManager.convertDoubleToInteger(qty));
				transitDetailsDTO.setMaterial(entity.getMaterial());
				transitDetailsDTO.setMaterialDescription(entity.getMaterialDesc());
				transitDetailsDTO.setCustomerCode(entity.getCustomerCode());
				transitDetailsDTO.setCustomerName(entity.getCustomerName());
				transitDetailsDTO.setUnitOfMeasure(entity.getUnitOfMeasure());
				transitDetailsDTO.setIssueDate(entity.getIssueDate());
				transitDetailsDTO.setProductCost(UtilManager.formatProductCost(entity.getMaterialCost()));
				transitToCustomerDTOs.add(transitDetailsDTO);
			}
		}
		return transitToCustomerDTOs;
	}

	@RequestMapping(value = "/getInventoryAtCustomerDetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getInventoryAtCustomerDetails(
			HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> inventoryAtCustDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
		List<TotalInventoryAtCustomerDetailsEntity> inventoryAtCustEntities = totalInventoryService
				.getInventoryAtCustomerDetails();
		for (TotalInventoryAtCustomerDetailsEntity entity : inventoryAtCustEntities) {
			Double qty = entity.getQuantity();
			if (UtilManager.isValidInteget(qty)) {
				TIShipmentTransitConsumedDetailsDTO atCustomerDTO = new TIShipmentTransitConsumedDetailsDTO();
				atCustomerDTO.setQuantity(UtilManager.convertDoubleToInteger(qty));
				atCustomerDTO.setMaterial(entity.getMaterial());
				atCustomerDTO.setMaterialDescription(entity.getMaterialDesc());
				atCustomerDTO.setCustomerCode(entity.getCustomerCode());
				atCustomerDTO.setCustomerName(entity.getCustomerName());
				atCustomerDTO.setUnitOfMeasure(entity.getUnitOfMeasure());
				atCustomerDTO.setReceivedDate(entity.getReceivedDate());
				atCustomerDTO.setReceivedFrom(entity.getReceivedFrom());
				atCustomerDTO.setPoNumber(entity.getPoNumber());
				atCustomerDTO.setProductCost(UtilManager.formatProductCost(entity.getMaterialCost()));
				inventoryAtCustDTOs.add(atCustomerDTO);
			}
		}
		return inventoryAtCustDTOs;
	}

	@RequestMapping(value = "/getInventoryConsumedDetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getInventoryConsumedDetails(
			HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> inventoryConsumedDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
		List<CustomerConsumedInventoryDetailsEntity> inventoryConsumedEntities = totalInventoryService
				.getCustomerConsumedInventoryDetails();
		for (CustomerConsumedInventoryDetailsEntity entity : inventoryConsumedEntities) {
			Double qty = entity.getQuantity();
			if (UtilManager.isValidInteget(qty)) {
				TIShipmentTransitConsumedDetailsDTO inventoryConsumedDTO = new TIShipmentTransitConsumedDetailsDTO();
				inventoryConsumedDTO.setQuantity(UtilManager.convertDoubleToInteger(qty));
				inventoryConsumedDTO.setMaterial(entity.getMaterial());
				inventoryConsumedDTO.setMaterialDescription(entity.getMaterialDesc());
				inventoryConsumedDTO.setCustomerName(entity.getCustomerName());
				inventoryConsumedDTO.setReceivedFrom(entity.getReceivedFrom());
				inventoryConsumedDTO.setCustomerCode(entity.getCustomerCode());
				inventoryConsumedDTO.setUnitOfMeasure(entity.getUnitOfMeasure());
				inventoryConsumedDTO.setInstallationDate(entity.getInstallationDate());
				inventoryConsumedDTO.setProductCost(UtilManager.formatProductCost(entity.getMaterialCost()));
				inventoryConsumedDTOs.add(inventoryConsumedDTO);
			}
		}
		return inventoryConsumedDTOs;
	}

	@RequestMapping("/getTotalInventoryDollarSummary")
	public @ResponseBody TIDollarValueSummaryDTO getTotalInventoryDollarValueSummaryDetails(HttpServletRequest request,
			HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		TIDollarValueSummaryDTO dollarValueSummaryDTO = new TIDollarValueSummaryDTO();
		TotalInventoryDollarValueSummaryEntity entity = this.totalInventoryService
				.getTotalInventoryDollarValueSummary();
		dollarValueSummaryDTO.setTotalQuantity(UtilManager.convertNumberToMillions(entity.getTotalQuantity()));
		dollarValueSummaryDTO
				.setTransitToFSGQuantity(UtilManager.convertNumberToMillions(entity.getTransitToFSGQuantity()));
		dollarValueSummaryDTO.setAtFSGQuantity(UtilManager.convertNumberToMillions(entity.getAtFSGQuantity()));
		dollarValueSummaryDTO.setTransitToCustomerQuantity(
				UtilManager.convertNumberToMillions(entity.getTransitToCustomerQuantity()));
		dollarValueSummaryDTO
				.setAtCustomerQuantity(UtilManager.convertNumberToMillions(entity.getAtCustomerQuantity()));
		dollarValueSummaryDTO
				.setCustomerConsumedQuantity(UtilManager.convertNumberToMillions(entity.getCustomerConsumedQuantity()));
		dollarValueSummaryDTO.setCustomerDemand(UtilManager.convertNumberToMillions(entity.getCustomerDemand()));
		return dollarValueSummaryDTO;
	}

	@RequestMapping(value = "/getCustomerDemandDetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getCustomerDemandDetails(HttpServletRequest request,
			HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> customerDemandDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
		List<CustomerDemandInventoryDetailsEntity> customerDemandEntities = totalInventoryService
				.getCustomerDemandInventoryDetails();
		for (CustomerDemandInventoryDetailsEntity entity : customerDemandEntities) {
			Double qty = entity.getDemandQty();
			if (UtilManager.isValidInteget(qty)) {
				TIShipmentTransitConsumedDetailsDTO demandDTO = new TIShipmentTransitConsumedDetailsDTO();
				demandDTO.setDemandQty(UtilManager.convertDoubleToInteger(qty));
				demandDTO.setShortageQty(UtilManager.convertDoubleToInteger(entity.getShortageQty()));
				demandDTO.setCustomerName(entity.getCustomerDesc());
				demandDTO.setCustomerCode(entity.getCustomerId());
				demandDTO.setDemandWeek(entity.getFiscalWeek());
				demandDTO.setYear(entity.getYear());
				customerDemandDTOs.add(demandDTO);
			}
		}
		return customerDemandDTOs;
	}

	@RequestMapping(value = "/getCustomerMaterialDemandDetails", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getCustomerMaterilaDemandDetails(
			@Param("customerId") String customerId, @Param("fiscalWeek") Integer fiscalWeek,
			@Param("year") Integer year, HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> customerMaterialDemandDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();
		List<CustomerMaterialDemandDetailsEntity> customerMaterialDemandEntities = totalInventoryService
				.getCustomerMaterialDemandInventoryDetails(customerId, fiscalWeek, year);
		for (CustomerMaterialDemandDetailsEntity entity : customerMaterialDemandEntities) {
			Double demandQty = entity.getDemand();
			if (UtilManager.isValidInteget(demandQty)) {
				TIShipmentTransitConsumedDetailsDTO materialDemandDTO = new TIShipmentTransitConsumedDetailsDTO();
				materialDemandDTO.setDemandQty(UtilManager.convertDoubleToInteger(demandQty));
				materialDemandDTO.setShortageQty(UtilManager.convertDoubleToInteger(entity.getShortage()));
				materialDemandDTO.setMaterial(entity.getMaterial());
				materialDemandDTO.setMaterialDescription(entity.getMaterialDesc());
				materialDemandDTO.setDemandWeek(String.valueOf(entity.getFiscalWeek()));
				materialDemandDTO.setLocation(entity.getFsgLocation());
				materialDemandDTO.setYear(entity.getYear().toString());
				customerMaterialDemandDTOs.add(materialDemandDTO);
			}
		}
		return customerMaterialDemandDTOs;
	}
	
	@RequestMapping(value="/getInstallerReceived" , method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<InstallerReceivedEntity> getInstallerReceivedDetails(HttpServletRequest request,
                                    HttpServletResponse response) {
		 List<InstallerReceivedEntity> installerReceivedEntities=	(List<InstallerReceivedEntity>) installerReceivedService.findAll();
		return installerReceivedEntities;
	}
	
	@RequestMapping(value = "/getCustomerMaterialDemandOverview", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody List<TIShipmentTransitConsumedDetailsDTO> getCustomerDemandMaterialSummery(HttpServletRequest request, HttpServletResponse response){
		//oAuth.oAuthValidator1(request, response);
		List<TIShipmentTransitConsumedDetailsDTO> customerMaterialDemandDTOs = new ArrayList<TIShipmentTransitConsumedDetailsDTO>();	
		List<CustomerMaterialDemandSummaryEntity> customerDemandOfMaterials = totalInventoryService.getCustomerMaterialDemandSummary();
		for(CustomerMaterialDemandSummaryEntity entity : customerDemandOfMaterials){
			Double totalDemandQty = entity.getTotalDemandQty();
			Double totalShortageQty = entity.getTotalShortageQty();
			if (UtilManager.isValidInteget(totalDemandQty)) {
				TIShipmentTransitConsumedDetailsDTO demandByFiscalWeekDTO = new TIShipmentTransitConsumedDetailsDTO();
				demandByFiscalWeekDTO.setTotalDemandQty(UtilManager.convertDoubleToInteger(totalDemandQty));
				demandByFiscalWeekDTO.setTotalShortageQty(UtilManager.convertDoubleToInteger(totalShortageQty));
				demandByFiscalWeekDTO.setYear(String.valueOf(entity.getYear()));
				demandByFiscalWeekDTO.setDemandWeek(String.valueOf(entity.getFiscalWeek()));
				demandByFiscalWeekDTO.setMaterial(String.valueOf(entity.getMaterial()));
				demandByFiscalWeekDTO.setMaterialDescription(String.valueOf(entity.getMaterialDesc()));
				demandByFiscalWeekDTO.setLocation(String.valueOf(entity.getServingLocation()));
				
				customerMaterialDemandDTOs.add(demandByFiscalWeekDTO);
			}
		}

		return customerMaterialDemandDTOs;
		
	}
	

}
