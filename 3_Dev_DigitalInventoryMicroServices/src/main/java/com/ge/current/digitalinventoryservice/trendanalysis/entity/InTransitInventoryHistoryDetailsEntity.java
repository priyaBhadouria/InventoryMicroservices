/**
 * 
 */
package com.ge.current.digitalinventoryservice.trendanalysis.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ncheredd
 *
 */
@Entity
@Table(name="intransit_to_fsg_history_table_view",schema="audit")
public class InTransitInventoryHistoryDetailsEntity implements Serializable
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
	
	private Double cost;
	
	@Column(name="material_cost")
	private Double materialCost;
	
	@Column(name="unit_of_measure")
	private String unitOfMeasure;	
	
	@Column(name="to_location")
	private String tolocation;
	
	@Column(name="from_location")
	private String fromlocation;
	
	@Column(name="pro_number")
	private String proNumber;
	
	@Column(name="carrier_name")
	private String carrierName;
	
	@Column(name="created_dtm")
	private Date date;

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
	 * @return the tolocation
	 */
	public String getTolocation() {
		return tolocation;
	}

	/**
	 * @param tolocation the tolocation to set
	 */
	public void setTolocation(String tolocation) {
		this.tolocation = tolocation;
	}

	/**
	 * @return the fromlocation
	 */
	public String getFromlocation() {
		return fromlocation;
	}

	/**
	 * @param fromlocation the fromlocation to set
	 */
	public void setFromlocation(String fromlocation) {
		this.fromlocation = fromlocation;
	}

	/**
	 * @return the proNumber
	 */
	public String getProNumber() {
		return proNumber;
	}

	/**
	 * @param proNumber the proNumber to set
	 */
	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}

	/**
	 * @return the carrierName
	 */
	public String getCarrierName() {
		return carrierName;
	}

	/**
	 * @param carrierName the carrierName to set
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
