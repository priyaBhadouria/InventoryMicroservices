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

import com.ge.current.digitalinventoryservice.dto.FSGAggregationSummaryDTO;
import com.ge.current.digitalinventoryservice.dto.FSGInventoryDrillDownDetailsDTO;
import com.ge.current.digitalinventoryservice.dto.TIDollarValueSummaryDTO;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGAggregationSummaryEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGInventoryTransitDemandShortageDrillDownEntity;
import com.ge.current.digitalinventoryservice.fsgoverview.entity.FSGOverviewDollarValueSummaryEntity;
import com.ge.current.digitalinventoryservice.repository.IFSGOverviewRepository;
import com.ge.current.digitalinventoryservice.util.DigitalInventoryConstants;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author ncheredd
 *
 */
@RestController
@RequestMapping("/div/fsgoverview")
public class FSGOverviewServices {
	@Autowired
	OauthVerification oAuth;

	@Autowired
	private IFSGOverviewRepository fsgOverview;

	@RequestMapping("/getFSGAggregationSummary")
	public @ResponseBody FSGAggregationSummaryDTO getFSGAggregationSummary(
			@Param("fsg") String fsg, HttpServletRequest request,
			HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		FSGAggregationSummaryDTO fsgSummaryDTO = null;
		List<FSGAggregationSummaryEntity> fsgAggregationSummaryEntityList = fsgOverview
				.getFSGAggregationSummary(fsg);
		fsgSummaryDTO = populateFSGSummaryToJsonBean(fsgAggregationSummaryEntityList);
		return fsgSummaryDTO;
	}

	@RequestMapping("/getFSGDrillDownDetails")
	public @ResponseBody List<FSGInventoryDrillDownDetailsDTO> getFSGDrillDownDetails(
			@Param("fsg") String fsg, HttpServletRequest request,
			HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		List<FSGInventoryDrillDownDetailsDTO> detailsDTOs = new ArrayList<FSGInventoryDrillDownDetailsDTO>();
		List<FSGInventoryTransitDemandShortageDrillDownEntity> entityList = fsgOverview
				.getFSGDrillDownDetails(fsg);
		for (FSGInventoryTransitDemandShortageDrillDownEntity entity : entityList) {
			FSGInventoryDrillDownDetailsDTO dto = new FSGInventoryDrillDownDetailsDTO();
			dto.setMaterial(entity.getMaterial());
			dto.setMaterialDescription(entity.getMaterialDescription());
			dto.setInventory(UtilManager.convertDoubleToInteger(entity
					.getInventory()));
			dto.setInTransit(UtilManager.convertDoubleToInteger(entity
					.getTransit()));
			dto.setDemand(UtilManager.convertDoubleToInteger(entity.getDemand()));
			dto.setShortage(UtilManager.convertDoubleToInteger(entity
					.getShortage()));
			dto.setWeeklyBucket(UtilManager.setDefaultValueForFWandYear(entity
					.getWeekShort()));
			dto.setYear(UtilManager.setDefaultValueForFWandYear(entity
					.getYear()));
			detailsDTOs.add(dto);
		}
		return detailsDTOs;
	}

	private FSGAggregationSummaryDTO populateFSGSummaryToJsonBean(
			List<FSGAggregationSummaryEntity> fsgAggregationSummaryEntityList) {
		FSGAggregationSummaryDTO fsgSummaryDTO = new FSGAggregationSummaryDTO();
		for (FSGAggregationSummaryEntity entity : fsgAggregationSummaryEntityList) {
			double quantity = entity.getQuantity();
			String category = entity.getCategory();
			if (DigitalInventoryConstants.INVENTORY_STRING.equals(category))
				fsgSummaryDTO.setInventory(quantity);
			else if (DigitalInventoryConstants.INTRANSIT_STRING
					.equals(category))
				fsgSummaryDTO.setInTransit(quantity);
			else if (DigitalInventoryConstants.DEMAND_STRING.equals(category))
				fsgSummaryDTO.setDemand(quantity);
			else if (DigitalInventoryConstants.SHORTAGE_STRING.equals(category))
				fsgSummaryDTO.setShortage(quantity);
		}
		return fsgSummaryDTO;
	}

	@RequestMapping("/getFSGOverviewDollarSummary")
	public @ResponseBody TIDollarValueSummaryDTO getFSGOverviewDollarValueSummaryDetails(
			HttpServletRequest request, HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		TIDollarValueSummaryDTO dollarValueSummaryDTO = new TIDollarValueSummaryDTO();
		FSGOverviewDollarValueSummaryEntity entity = this.fsgOverview
				.getFSGOverviewDollarValueSummary();

		dollarValueSummaryDTO.setTransitToFSGQuantity(UtilManager
				.convertNumberToMillions(entity.getTransitToFSGCost()));
		dollarValueSummaryDTO.setAtFSGQuantity(UtilManager
				.convertNumberToMillions(entity.getAtFSGCost()));
		dollarValueSummaryDTO.setFsgDemand(UtilManager
				.convertNumberToMillions(entity.getFsgDemandCost()));
		dollarValueSummaryDTO.setFsgShortage(UtilManager
				.convertNumberToMillions(entity.getFsgShortageCost()));
		return dollarValueSummaryDTO;
	}

}
