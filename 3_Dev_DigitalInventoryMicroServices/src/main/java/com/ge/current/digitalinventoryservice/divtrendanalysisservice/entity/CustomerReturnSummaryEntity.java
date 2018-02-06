package com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_return_trend_view", schema = "validstage")
public class CustomerReturnSummaryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String Id;

	@Column(name = "return_date")
	private LocalDateTime returnDate;

	@Column(name = "all_returned_qty")
	private Double allReturnedQty;

	@Column(name = "site_returned_qty")
	private Double siteReturnedQty;

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
	 * @return returnDate
	 */
	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	/**
	 * 
	 * @param issuedDate
	 */
	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * 
	 * @return allReturnedQty
	 */
	public Double getAllReturnedQty() {
		return allReturnedQty;
	}

	/**
	 * 
	 * @param allReturnedQty
	 */
	public void setAllReturnedQty(Double allReturnedQty) {
		this.allReturnedQty = allReturnedQty;
	}

	/**
	 * 
	 * @return siteReturnedQty
	 */
	public Double getSiteReturnedQty() {
		return siteReturnedQty;
	}

	/**
	 * 
	 * @param siteReturnedQty
	 */
	public void setSiteReturnedQty(Double siteReturnedQty) {
		this.siteReturnedQty = siteReturnedQty;
	}

	/**
	 * 
	 * @return
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
