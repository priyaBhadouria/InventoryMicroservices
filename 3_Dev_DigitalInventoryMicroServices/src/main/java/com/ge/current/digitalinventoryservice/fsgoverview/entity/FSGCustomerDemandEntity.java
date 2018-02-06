/**
 * 
 */
package com.ge.current.digitalinventoryservice.fsgoverview.entity;

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
@Table(name = "fsg_material_customer_demand_view", schema = "analytics")
public class FSGCustomerDemandEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "storage_location")
	private String fsgLocation;

	@Column(name = "customerid")
	private String customerId;

	@Column(name = "customer_desc")
	private String customerDescription;

	@Column(name = "materialid")
	private String materialId;

	@Column(name = "demand_qty")
	private String demandQty;

	@Column(name = "shortage")
	private String shortage;

	@Column(name = "fiscal_week")
	private Integer fiscalWeek;

	@Column(name = "year")
	private Integer year;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFsgLocation() {
		return fsgLocation;
	}

	public void setFsgLocation(String fsgLocation) {
		this.fsgLocation = fsgLocation;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getDemandQty() {
		return demandQty;
	}

	public void setDemandQty(String demandQty) {
		this.demandQty = demandQty;
	}

	public String getShortage() {
		return shortage;
	}

	public void setShortage(String shortage) {
		this.shortage = shortage;
	}

	public Integer getFiscalWeek() {
		return fiscalWeek;
	}

	public void setFiscalWeek(Integer fiscalWeek) {
		this.fiscalWeek = fiscalWeek;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
