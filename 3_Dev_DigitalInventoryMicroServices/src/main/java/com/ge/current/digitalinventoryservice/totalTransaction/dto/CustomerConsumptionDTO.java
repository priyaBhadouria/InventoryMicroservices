/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalTransaction.dto;

import java.io.Serializable;

/**
 * @author vs825995
 *
 */
public class CustomerConsumptionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CustomerSite;

	private String material;
	private String materialDescription;
	private double currentQty;
	private String lastDate;
	private String customerDesc;
	private double returnedQty;
	private String state;
	private String fsgServedLocation;
	private Integer variance;
	

	public double getReturnedQty() {
		return returnedQty;
	}

	public String getFsgServedLocation() {
		return fsgServedLocation;
	}

	public void setFsgServedLocation(String fsgServedLocation) {
		this.fsgServedLocation = fsgServedLocation;
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

	public String getCustomerDesc() {
		return customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
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

	public Integer getVariance() {
		return variance;
	}

	public void setVariance(Integer variance) {
		this.variance = variance;
	}

}
