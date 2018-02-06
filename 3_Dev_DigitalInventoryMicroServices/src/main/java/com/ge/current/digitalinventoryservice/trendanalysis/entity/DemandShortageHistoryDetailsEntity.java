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
@Table(name="customer_demand_shortage_history_table_view",schema="audit")
public class DemandShortageHistoryDetailsEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="material_id")
	private String material;
	
	@Column(name="material_description")
	private String materialDesc;
	
	private Double demand;
	
	private Double shortage;
	
	@Column(name="storage_location")
	private String fsgLocation;
	
	@Column(name="fiscal_week")
	private Integer fiscalWeek;
	
	private Integer year;
	
	@Column(name="backup_date")
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
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	 * @return the demand
	 */
	public Double getDemand() {
		return demand;
	}

	/**
	 * @param demand the demand to set
	 */
	public void setDemand(Double demand) {
		this.demand = demand;
	}

	/**
	 * @return the shortage
	 */
	public Double getShortage() {
		return shortage;
	}

	/**
	 * @param shortage the shortage to set
	 */
	public void setShortage(Double shortage) {
		this.shortage = shortage;
	}

	/**
	 * @return the fsgLocation
	 */
	public String getFsgLocation() {
		return fsgLocation;
	}

	/**
	 * @param fsgLocation the fsgLocation to set
	 */
	public void setFsgLocation(String fsgLocation) {
		this.fsgLocation = fsgLocation;
	}

	/**
	 * @return the fiscalWeek
	 */
	public Integer getFiscalWeek() {
		return fiscalWeek;
	}

	/**
	 * @param fiscalWeek the fiscalWeek to set
	 */
	public void setFiscalWeek(Integer fiscalWeek) {
		this.fiscalWeek = fiscalWeek;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
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
