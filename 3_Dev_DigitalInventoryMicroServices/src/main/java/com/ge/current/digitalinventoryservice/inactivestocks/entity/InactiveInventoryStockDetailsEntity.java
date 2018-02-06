/**
 * 
 */
package com.ge.current.digitalinventoryservice.inactivestocks.entity;

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
@Table(name = "inactive_stock_details_view", schema = "validstage")
public class InactiveInventoryStockDetailsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String material;

	@Column(name = "material_description")
	private String materialDesc;

	private Double quantity;

	@Column(name = "unit_of_measure")
	private String unitOfMeasure;

	private Double cost;

	@Column(name = "material_cost")
	private Double materialCost;

	private String fsg;

	@Column(name = "inactive_days")
	private Integer inactiveDays;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
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
	 * @param unitOfMeasure
	 *            the unitOfMeasure to set
	 */
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
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
	 * @param materialCost
	 *            the materialCost to set
	 */
	public void setMaterialCost(Double materialCost) {
		this.materialCost = materialCost;
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
		this.fsg = fsg;
	}

	/**
	 * @return the inactiveDays
	 */
	public Integer getInactiveDays() {
		return inactiveDays;
	}

	/**
	 * @param inactiveDays
	 *            the inactiveDays to set
	 */
	public void setInactiveDays(Integer inactiveDays) {
		this.inactiveDays = inactiveDays;
	}

}
