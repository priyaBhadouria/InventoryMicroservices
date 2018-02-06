package com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_consumption_trend_popup_view", schema = "analytics")
public class CustTrendFSGDrilldownEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String Id;

	@Column(name = "material")
	private String material;

	@Column(name = "customer")
	private String customerId;

	@Column(name = "customer_desc")
	private String customerDesc;

	@Column(name = "installation_date")
	private LocalDate date;

	@Column(name = "Customer_Consumption")
	private Double CustConsQuantity;

	@Column(name = "Customer_Return")
	private Double custRQuantity;

	@Column(name = "storage_location")
	private String installationLoc;

	@Column(name = "received_to")
	private String receivedLoc;

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
	 * @return customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * 
	 * @param customerId
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * 
	 * @return customerDesc
	 */
	public String getCustomerDesc() {
		return customerDesc;
	}

	/**
	 * 
	 * @param customerDesc
	 */
	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
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
	 * @return CustConsQuantity
	 */
	public Double getCustConsQuantity() {
		return CustConsQuantity;
	}

	/**
	 * 
	 * @param custConsQuantity
	 */
	public void setCustConsQuantity(Double custConsQuantity) {
		CustConsQuantity = custConsQuantity;
	}

	/**
	 * 
	 * @return custRQuantity
	 */
	public Double getCustRQuantity() {
		return custRQuantity;
	}

	/**
	 * 
	 * @param custRQuantity
	 */
	public void setCustRQuantity(Double custRQuantity) {
		this.custRQuantity = custRQuantity;
	}

	/**
	 * 
	 * @return installationLoc
	 */
	public String getInstallationLoc() {
		return installationLoc;
	}

	/**
	 * 
	 * @param installationLoc
	 */
	public void setInstallationLoc(String installationLoc) {
		this.installationLoc = installationLoc;
	}

	/**
	 * 
	 * @return receivedLoc
	 */
	public String getReceivedLoc() {
		return receivedLoc;
	}

	/**
	 * 
	 * @param receivedLoc
	 */
	public void setReceivedLoc(String receivedLoc) {
		this.receivedLoc = receivedLoc;
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
}
