/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalTransaction.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vs825995
 *
 */

@Entity
@Table(name = "customer_consumption_drilldown_variance_view", schema = "analytics")
public class CustomerConsumptionEntity {

	@Id
	private int id;

	private String CustomerSite;

	private String material;

	@Column(name = "material_description")
	private String materialDescription;

	@Column(name = "customer_desc")
	private String customerDesc;

	public String getCustomerDesc() {
		return customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}

	@Column(name = "installed_qty")
	private double currentQty;

	@Column(name = "returned_qty")
	private double returnedQty;

	@Column(name = "fsg_served_location")
	private String fsgServedLocation;

	private String state;

	@Column(name = "last_date")
	private Date lastDate;
	
	@Column(name = "variance")
	private Integer variance;

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getCustomerSite() {
		return CustomerSite;
	}

	public void setCustomerSite(String customerSite) {
		CustomerSite = customerSite;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	public double getCurrentQty() {
		return currentQty;
	}

	public void setCurrentQty(double currentQty) {
		this.currentQty = currentQty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getReturnedQty() {
		return returnedQty;
	}

	public void setReturnedQty(double returnedQty) {
		this.returnedQty = returnedQty;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFsgServedLocation() {
		return fsgServedLocation;
	}

	public void setFsgServedLocation(String fsgServedLocation) {
		this.fsgServedLocation = fsgServedLocation;
	}

	public Integer getVariance() {
		return variance;
	}

	public void setVariance(Integer variance) {
		this.variance = variance;
	}

}
