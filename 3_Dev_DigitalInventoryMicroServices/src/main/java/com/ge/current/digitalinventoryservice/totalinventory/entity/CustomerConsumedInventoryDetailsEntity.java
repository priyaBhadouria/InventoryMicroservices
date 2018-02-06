/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalinventory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ncheredd
 *
 */
@Entity
@Table(name="customer_consumed_inventory_drill_down_view",schema="analytics")
public class CustomerConsumedInventoryDetailsEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private String material;
	
	@Column(name="material_description")
	private String materialDesc;
	
	private Double quantity;
	
	@Column(name="unit_of_measure")
	private String unitOfMeasure;
	
	@Column(name="customer_code")
	private String customerCode;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="received_from")
	private String receivedFrom;
	
	@Column(name="installation_date")
	private String installationDate;
	
	private Double cost;
	
	@Column(name="material_cost")
	private Double materialCost;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
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
	 * @param materialDesc the materialDesc to set
	 */
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the unitOfMeasure
	 */
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	/**
	 * @param unitOfMeasure the unitOfMeasure to set
	 */
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	/**
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * @param customerCode the customerCode to set
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the receivedFrom
	 */
	public String getReceivedFrom() {
		return receivedFrom;
	}

	/**
	 * @param receivedFrom the receivedFrom to set
	 */
	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	/**
	 * @return the installationDate
	 */
	public String getInstallationDate() {
		return installationDate;
	}

	/**
	 * @param installationDate the installationDate to set
	 */
	public void setInstallationDate(String installationDate) {
		this.installationDate = installationDate;
	}

	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}

	/**
	 * @return the materialCost
	 */
	public Double getMaterialCost() {
		return materialCost;
	}

	/**
	 * @param materialCost the materialCost to set
	 */
	public void setMaterialCost(Double materialCost) {
		this.materialCost = materialCost;
	}

}
