package com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_consumption_trend_view", schema = "analytics")
public class CustomerConsumptionSummaryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String Id;

	@Column(name = "installation_date ")
	private LocalDateTime installationDate;

	@Column(name = "all_consumed_qty")
	private Double allConsumedQty;

	@Column(name = "customer_consumed_qty")
	private Double customerConsumedQty;

	@Column(name = "customer_site_id")
	private String customerSiteId;

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
	 * @return installationDate
	 */

	public LocalDateTime getInstallationDate() {
		return installationDate;
	}

	/**
	 * 
	 * @param installationDate
	 */

	public void setInstallationDate(LocalDateTime installationDate) {
		this.installationDate = installationDate;
	}

	/**
	 * 
	 * @return allConsumedQty
	 */
	public Double getAllConsumedQty() {
		return allConsumedQty;
	}

	/**
	 * 
	 * @param allConsumedQty
	 */
	public void setAllConsumedQty(Double allConsumedQty) {
		this.allConsumedQty = allConsumedQty;
	}

	/**
	 * 
	 * @return customerConsumedQty
	 */
	public Double getCustomerConsumedQty() {
		return customerConsumedQty;
	}

	/**
	 * 
	 * @param customerConsumedQty
	 */
	public void setCustomerConsumedQty(Double customerConsumedQty) {
		this.customerConsumedQty = customerConsumedQty;
	}

	/**
	 * 
	 * @return customerSiteId
	 */
	public String getCustomerSiteId() {
		return customerSiteId;
	}

	/**
	 * 
	 * @param customerSiteId
	 */
	public void setCustomerSiteId(String customerSiteId) {
		this.customerSiteId = customerSiteId;
	}

}
