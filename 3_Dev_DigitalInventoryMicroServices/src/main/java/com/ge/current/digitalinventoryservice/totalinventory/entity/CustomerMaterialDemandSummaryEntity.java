package com.ge.current.digitalinventoryservice.totalinventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="customer_material_demand_summary",schema="analytics")
public class CustomerMaterialDemandSummaryEntity {
	
	
	@Id
	private int id;
	
	private Integer year;

	@Column(name="fiscal_week")
	private Integer fiscalWeek;
	
	@Column(name="total_shortage")
	private Double totalShortageQty;
	
	@Column(name="total_demand")
	private Double totalDemandQty;
	
	@Column(name="material")
	private String material;
	
	@Column(name="material_description")
	private String materialDesc;
	
	@Column(name="serving_location")
	private String servingLocation;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	

	public Integer getFiscalWeek() {
		return fiscalWeek;
	}

	public void setFiscalWeek(Integer fiscalWeek) {
		this.fiscalWeek = fiscalWeek;
	}

	public Double getTotalShortageQty() {
		return totalShortageQty;
	}

	public void setTotalShortageQty(Double totalShortageQty) {
		this.totalShortageQty = totalShortageQty;
	}

	public Double getTotalDemandQty() {
		return totalDemandQty;
	}

	public void setTotalDemandQty(Double totalDemandQty) {
		this.totalDemandQty = totalDemandQty;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getServingLocation() {
		return servingLocation;
	}

	public void setServingLocation(String servingLocation) {
		this.servingLocation = servingLocation;
	}

	

}

