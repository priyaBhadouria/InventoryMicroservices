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
@Table(name="inventory_received_stock_issued_view", schema="analytics")
public class TotalInventoryAtFSGDetailsEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name="fsg")
	private String location;
	
	private String material;
	
	private Double quantity;
	
	@Column(name="material_description")
	private String materialDescription;
	
	@Column(name="unit_of_measure")
	private String unitOfMeasure;
	
	@Column(name="order_status")
	private String orderStatus;
	
	private Double cost;
	
//	@Column(name="material_cost")
//	private Double materialCost;
	
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		TotalInventoryAtFSGDetailsEntity entity = (TotalInventoryAtFSGDetailsEntity)obj;
		return (this.material == entity.material || (this.material != null && this.material.equals(entity.material))) && (this.location == entity.location || (this.location != null && this.location.equals(entity.location)));
//				&& (this.orderStatus == entity.orderStatus || (this.orderStatus != null && this.orderStatus.equals(entity.orderStatus)));
	}

	public int hashCode()
	{
		int hash = 7;
		hash = 31 * hash + (null == this.material ? 0 : this.material.hashCode());
		hash = 31 * hash + (null == this.location ? 0 : this.location.hashCode());
//		hash = 31 * hash + (null == this.orderStatus ? 0 : this.orderStatus.hashCode());
		return hash;
	}
	
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
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

//	/**
//	 * @return the materialCost
//	 */
//	public Double getMaterialCost() {
//		return materialCost;
//	}
//
//	/**
//	 * @param materialCost the materialCost to set
//	 */
//	public void setMaterialCost(Double materialCost) {
//		this.materialCost = materialCost;
//	}
	
}
