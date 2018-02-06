/**
 * 
 */
package com.ge.current.digitalinventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author ncheredd
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InactiveInventoryStockDetailsDTO {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String material;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("materialdesc")
	private String materialDescription;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer quantity;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String location;

	@JsonProperty("productcost")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double productCost;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String days_11_to_20;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String days_21_to_30;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String days_31_to_60;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String days_60_plus;

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
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = UtilManager.setDefaultValueToJsonInteger(quantity);
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = UtilManager.isJsonValidString(location);
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
	 * @return the days_11_to_20
	 */
	public String getDays_11_to_20() {
		return days_11_to_20;
	}

	/**
	 * @param days_11_to_20
	 *            the days_11_to_20 to set
	 */
	public void setDays_11_to_20(String days_11_to_20) {
		this.days_11_to_20 = days_11_to_20;
	}

	/**
	 * @return the days_21_to_30
	 */
	public String getDays_21_to_30() {
		return days_21_to_30;
	}

	/**
	 * @param days_21_to_30
	 *            the days_21_to_30 to set
	 */
	public void setDays_21_to_30(String days_21_to_30) {
		this.days_21_to_30 = days_21_to_30;
	}

	/**
	 * @return the days_31_to_60
	 */
	public String getDays_31_to_60() {
		return days_31_to_60;
	}

	/**
	 * @param days_31_to_60
	 *            the days_31_to_60 to set
	 */
	public void setDays_31_to_60(String days_31_to_60) {
		this.days_31_to_60 = days_31_to_60;
	}

	/**
	 * @return the days_60_plus
	 */
	public String getDays_60_plus() {
		return days_60_plus;
	}

	/**
	 * @param days_60_plus
	 *            the days_60_plus to set
	 */
	public void setDays_60_plus(String days_60_plus) {
		this.days_60_plus = days_60_plus;
	}

	/**
	 * @return the productCost
	 */
	public Double getProductCost() {
		return productCost;
	}

	/**
	 * @param productCost
	 *            the productCost to set
	 */
	public void setProductCost(Double productCost) {
		this.productCost = UtilManager.setDefaultValueToJsonDouble(productCost);
	}
}
