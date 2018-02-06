/**
 * 
 */
package com.ge.current.digitalinventoryservice.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author ncheredd
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FSGInventoryDrillDownDetailsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String fsg;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer inventory;

	@JsonProperty("intransit")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer inTransit;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer demand;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer shortage;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String material;

	@JsonProperty("materialdesc")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String materialDescription;

	@JsonProperty("weeklybucket")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String weeklyBucket;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String year;

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
		this.inventory = UtilManager.setDefaultValueToJsonInteger(inventory);
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
		this.inTransit = UtilManager.setDefaultValueToJsonInteger(inTransit);
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
		this.demand = UtilManager.setDefaultValueToJsonInteger(demand);
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
		this.shortage = UtilManager.setDefaultValueToJsonInteger(shortage);
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
		this.material = UtilManager.isJsonValidString(material);
	}

	/**
	 * @return the materialDescription
	 */
	public String getMaterialDescription() {
		return materialDescription;
	}

	/**
	 * @param materialDescription
	 *            the materialDescription to set
	 */
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = UtilManager
				.isJsonValidString(materialDescription);
	}

	/**
	 * @return the weeklyBucket
	 */
	public String getWeeklyBucket() {
		return weeklyBucket;
	}

	/**
	 * @param weeklyBucket
	 *            the weeklyBucket to set
	 */
	public void setWeeklyBucket(String weeklyBucket) {
		this.weeklyBucket = UtilManager.isJsonValidString(weeklyBucket);
	}

	/**
	 * @return the fsg
	 */
	public String getFsg() {
		return fsg;
	}

	/**
	 * @param fsg
	 *            the fsg to set
	 */
	public void setFsg(String fsg) {
		this.fsg = UtilManager.isJsonValidString(fsg);
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = UtilManager.isJsonValidString(year);
	}

}
