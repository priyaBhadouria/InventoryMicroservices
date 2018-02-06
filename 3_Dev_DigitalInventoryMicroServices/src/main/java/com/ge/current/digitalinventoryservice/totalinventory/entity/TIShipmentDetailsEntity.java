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
@Table(name="ge_shipment_drill_down_view",schema="analytics")
public class TIShipmentDetailsEntity implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vid")
	private Integer viewId;
	
	private String material;
	
	@Column(name="material_description")
	private String materialDescription;
	
	private Double quantity;
	
	private Double cost;
	
	@Column(name="material_cost")
	private Double materialCost;
	
//	@Column(name="current_quantity")
//	private Double currentQty;
//	
//	@Column(name="intransit_quantity")
//	private Double intransitQty;
	
	private String location;
	
	@Column(name="unit_of_measure")
	private String unitOfMeasure;
	
//	private Double cost;
//	
//	@Column(name="current_material_cost")
//	private Double currentMaterialCost;
//	
//	@Column(name="intransit_material_cost")
//	private Double intransitMaterialCost;
	
	private Character status;

	/**
	 * @return the viewId
	 */
	public Integer getViewId() {
		return viewId;
	}

	/**
	 * @param viewId the viewId to set
	 */
	public void setViewId(Integer viewId) {
		this.viewId = viewId;
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
	 * @return the materialDescription
	 */
	public String getMaterialDescription() {
		return materialDescription;
	}

	/**
	 * @param materialDescription the materialDescription to set
	 */
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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
	 * @return the status
	 */
	public Character getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Character status) {
		this.status = status;
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
