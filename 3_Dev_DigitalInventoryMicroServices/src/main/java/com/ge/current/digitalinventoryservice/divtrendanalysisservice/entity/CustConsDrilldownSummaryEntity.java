package com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_consumption_trend_drilldown_view", schema = "analytics")
public class CustConsDrilldownSummaryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String Id;

	@Column(name = "material")
	private String material;

	@Column(name = "material_description")
	private String materialDescription;

	@Column(name = "installation_date")
	private LocalDate date;

	@Column(name = "customer_Consumption")
	private Double custConsQuantity;

	@Column(name = "Customer_Returns")
	private Double custReturnQuantity;

	/**
	 * 
	 * @return Id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * 
	 * @return material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * 
	 * @param material
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * 
	 * @return materialDescription
	 */
	public String getMaterialDescription() {
		return materialDescription;
	}

	/**
	 * 
	 * @param materialDescription
	 */
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	/**
	 * 
	 * @return date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * 
	 * @return custConsQuantity
	 */
	public Double getCustConsQuantity() {
		return custConsQuantity;
	}

	/**
	 * 
	 * @param custConsQuantity
	 */
	public void setCustConsQuantity(Double custConsQuantity) {
		this.custConsQuantity = custConsQuantity;
	}

	/**
	 * 
	 * @return custReturnQuantity
	 */
	public Double getCustReturnQuantity() {
		return custReturnQuantity;
	}

	/**
	 * 
	 * @param custReturnQuantity
	 */
	public void setCustReturnQuantity(Double custReturnQuantity) {
		this.custReturnQuantity = custReturnQuantity;
	}

}
