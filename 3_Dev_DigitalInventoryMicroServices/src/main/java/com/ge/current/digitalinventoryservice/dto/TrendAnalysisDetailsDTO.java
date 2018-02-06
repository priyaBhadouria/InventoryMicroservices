/**
 * 
 */
package com.ge.current.digitalinventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ncheredd
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendAnalysisDetailsDTO {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String inventoryGraphResponse;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String inTransitGraphResponse;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String demandGraphResponse;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String shortageGraphResponse;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String material;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String materialDesc;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer inventory;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer inTransit;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer demand;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer shortage;

	/**
	 * @return the inventoryGraphResponse
	 */
	public String getInventoryGraphResponse() {
		return inventoryGraphResponse;
	}

	/**
	 * @param inventoryGraphResponse
	 *            the inventoryGraphResponse to set
	 */
	public void setInventoryGraphResponse(String inventoryGraphResponse) {
		this.inventoryGraphResponse = inventoryGraphResponse;
	}

	/**
	 * @return the inTransitGraphResponse
	 */
	public String getInTransitGraphResponse() {
		return inTransitGraphResponse;
	}

	/**
	 * @param inTransitGraphResponse
	 *            the inTransitGraphResponse to set
	 */
	public void setInTransitGraphResponse(String inTransitGraphResponse) {
		this.inTransitGraphResponse = inTransitGraphResponse;
	}

	/**
	 * @return the demandGraphResponse
	 */
	public String getDemandGraphResponse() {
		return demandGraphResponse;
	}

	/**
	 * @param demandGraphResponse
	 *            the demandGraphResponse to set
	 */
	public void setDemandGraphResponse(String demandGraphResponse) {
		this.demandGraphResponse = demandGraphResponse;
	}

	/**
	 * @return the shortageGraphResponse
	 */
	public String getShortageGraphResponse() {
		return shortageGraphResponse;
	}

	/**
	 * @param shortageGraphResponse
	 *            the shortageGraphResponse to set
	 */
	public void setShortageGraphResponse(String shortageGraphResponse) {
		this.shortageGraphResponse = shortageGraphResponse;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * @return the materialDesc
	 */
	public String getMaterialDesc() {
		return materialDesc;
	}

	/**
	 * @param materialDesc
	 *            the materialDesc to set
	 */
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	/**
	 * @return the inventory
	 */
	public Integer getInventory() {
		return inventory;
	}

	/**
	 * @param inventory
	 *            the inventory to set
	 */
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the inTransit
	 */
	public Integer getInTransit() {
		return inTransit;
	}

	/**
	 * @param inTransit
	 *            the inTransit to set
	 */
	public void setInTransit(Integer inTransit) {
		this.inTransit = inTransit;
	}

	/**
	 * @return the demand
	 */
	public Integer getDemand() {
		return demand;
	}

	/**
	 * @param demand
	 *            the demand to set
	 */
	public void setDemand(Integer demand) {
		this.demand = demand;
	}

	/**
	 * @return the shortage
	 */
	public Integer getShortage() {
		return shortage;
	}

	/**
	 * @param shortage
	 *            the shortage to set
	 */
	public void setShortage(Integer shortage) {
		this.shortage = shortage;
	}

}
