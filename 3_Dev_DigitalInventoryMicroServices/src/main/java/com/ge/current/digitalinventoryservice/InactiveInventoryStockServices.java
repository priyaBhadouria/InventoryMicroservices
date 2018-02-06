/**
 * 
 */
package com.ge.current.digitalinventoryservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.current.digitalinventoryservice.dto.InactiveInventoryStockDetailsDTO;
import com.ge.current.digitalinventoryservice.inactivestocks.entity.InactiveInventoryStockDetailsEntity;
import com.ge.current.digitalinventoryservice.inactivestocks.entity.InactiveInventoryStockRatioEntity;
import com.ge.current.digitalinventoryservice.repository.IInactiveInventoryStockRepository;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author ncheredd
 *
 */

@RestController
@RequestMapping("/div/inactivestock")
public class InactiveInventoryStockServices {
	@Autowired
	OauthVerification oAuth;

	@Autowired
	private IInactiveInventoryStockRepository inactiveInventoryStockRepository;

	@RequestMapping("/getInactiveInventoryRatiodetails")
	public @ResponseBody InactiveInventoryStockDetailsDTO getInactiveStockRatioDetails(
			HttpServletRequest request, HttpServletResponse response) {
		 //oAuth.oAuthValidator1(request, response);
		InactiveInventoryStockRatioEntity entity = this.inactiveInventoryStockRepository
				.getInactiveStockRatioDetails();
		InactiveInventoryStockDetailsDTO detailsDTO = new InactiveInventoryStockDetailsDTO();
		detailsDTO.setDays_11_to_20(UtilManager.convertNumberToMillions(entity
				.getDays_11_to_20()));
		detailsDTO.setDays_21_to_30(UtilManager.convertNumberToMillions(entity
				.getDays_21_to_30()));
		detailsDTO.setDays_31_to_60(UtilManager.convertNumberToMillions(entity
				.getDays_31_to_60()));
		detailsDTO.setDays_60_plus(UtilManager.convertNumberToMillions(entity
				.getDays_60_plus()));
		return detailsDTO;
	}

	@RequestMapping("/getMostInactiveInventorydetails")
	public @ResponseBody List<InactiveInventoryStockDetailsDTO> getInactiveInventoryStockDetails(
			@RequestParam(value = "fromDays") int fromDays,
			HttpServletRequest request, HttpServletResponse response) {
		 //oAuth.oAuthValidator1(request, response);
		List<InactiveInventoryStockDetailsEntity> detailsEntities = null;
		detailsEntities = this.inactiveInventoryStockRepository
				.getMostInactiveDaysInventoryStockDetails(fromDays);
		return formatingInactiveStockDetails(detailsEntities);
	}

	@RequestMapping("/getInactiveInventorydetails")
	public @ResponseBody List<InactiveInventoryStockDetailsDTO> getInactiveInventoryStockDetails(
			@RequestParam(value = "fromDays") int fromDays,
			@RequestParam(value = "toDays") int toDays,
			HttpServletRequest request, HttpServletResponse response) {
		 //oAuth.oAuthValidator1(request, response);
		List<InactiveInventoryStockDetailsEntity> detailsEntities = this.inactiveInventoryStockRepository
				.getInactiveInventoryStockDetails(fromDays, toDays);
		return formatingInactiveStockDetails(detailsEntities);
	}

	private List<InactiveInventoryStockDetailsDTO> formatingInactiveStockDetails(
			List<InactiveInventoryStockDetailsEntity> detailsEntities) {
		List<InactiveInventoryStockDetailsDTO> inactiveStockDetailsDTOs = new ArrayList<InactiveInventoryStockDetailsDTO>();
		for (InactiveInventoryStockDetailsEntity entity : detailsEntities) {
			Double quantity = entity.getQuantity();
			if (UtilManager.isValidInteget(quantity)) {
				InactiveInventoryStockDetailsDTO detailsDTO = new InactiveInventoryStockDetailsDTO();
				detailsDTO.setMaterial(entity.getMaterial());
				detailsDTO.setMaterialDescription(entity.getMaterialDesc());
				detailsDTO.setLocation(entity.getFsg());
				detailsDTO.setQuantity(UtilManager
						.convertDoubleToInteger(entity.getQuantity()));
				detailsDTO.setProductCost(UtilManager.formatProductCost(entity
						.getMaterialCost()));
				inactiveStockDetailsDTOs.add(detailsDTO);
			}
		}
		return inactiveStockDetailsDTOs;
	}
}
