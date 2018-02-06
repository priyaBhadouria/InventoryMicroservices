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
@Table(name="customer_demand_inventory_drill_down_view",schema="analytics")
public class CustomerDemandInventoryDetailsEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name="customerid")
	private String customerId;
	
	@Column(name="customer_desc")
	private String customerDesc;
	
	@Column(name="demand")
	private Double demandQty;
	
	@Column(name="shortage")
	private Double shortageQty;
	
//	@Column(name="unit_of_measure")
//	private String unitOfMeasure;	
	
	@Column(name="fiscal_week")
	private String fiscalWeek;
	
	private String year;

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
	 * @return the customerDesc
	 */
	public String getCustomerDesc() {
		return customerDesc;
	}

	/**
	 * @param customerDesc the customerDesc to set
	 */
	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}
	
	


//	/**
//	 * @return the unitOfMeasure
//	 */
//	public String getUnitOfMeasure() {
//		return unitOfMeasure;
//	}
//
//	/**
//	 * @param unitOfMeasure the unitOfMeasure to set
//	 */
//	public void setUnitOfMeasure(String unitOfMeasure) {
//		this.unitOfMeasure = unitOfMeasure;
//	}

	/**
	 * @return the demandQty
	 */
	public Double getDemandQty() {
		return demandQty;
	}

	/**
	 * @param demandQty the demandQty to set
	 */
	public void setDemandQty(Double demandQty) {
		this.demandQty = demandQty;
	}

	/**
	 * @return the shortageQty
	 */
	public Double getShortageQty() {
		return shortageQty;
	}

	/**
	 * @param shortageQty the shortageQty to set
	 */
	public void setShortageQty(Double shortageQty) {
		this.shortageQty = shortageQty;
	}

	/**
	 * @return the fiscalWeek
	 */
	public String getFiscalWeek() {
		return fiscalWeek;
	}

	/**
	 * @param fiscalWeek the fiscalWeek to set
	 */
	public void setFiscalWeek(String fiscalWeek) {
		this.fiscalWeek = fiscalWeek;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

}
