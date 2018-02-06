/**
 * 
 */
package com.ge.current.digitalinventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ncheredd
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TIDollarValueSummaryDTO {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("totalqty_$_value")
	private String totalQuantity;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("transittofsg_$_value")
	private String transitToFSGQuantity;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("atfsg_$_value")
	private String atFSGQuantity;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("transittocust_$_value")
	private String transitToCustomerQuantity;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("atcustomer_$_value")
	private String atCustomerQuantity;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("consuption_$_value")
	private String customerConsumedQuantity;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("custdemand_$_value")
	private String customerDemand;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("fsg_demand_$_value")
	private String fsgDemand;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("fsg_shortage_$_value")
	private String fsgShortage;

	/**
	 * @return the totalQuantity
	 */
	public String getTotalQuantity() {
		return totalQuantity;
	}

	/**
	 * @param totalQuantity
	 *            the totalQuantity to set
	 */
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	/**
	 * @return the transitToFSGQuantity
	 */
	public String getTransitToFSGQuantity() {
		return transitToFSGQuantity;
	}

	/**
	 * @param transitToFSGQuantity
	 *            the transitToFSGQuantity to set
	 */
	public void setTransitToFSGQuantity(String transitToFSGQuantity) {
		this.transitToFSGQuantity = transitToFSGQuantity;
	}

	/**
	 * @return the atFSGQuantity
	 */
	public String getAtFSGQuantity() {
		return atFSGQuantity;
	}

	/**
	 * @param atFSGQuantity
	 *            the atFSGQuantity to set
	 */
	public void setAtFSGQuantity(String atFSGQuantity) {
		this.atFSGQuantity = atFSGQuantity;
	}

	/**
	 * @return the transitToCustomerQuantity
	 */
	public String getTransitToCustomerQuantity() {
		return transitToCustomerQuantity;
	}

	/**
	 * @param transitToCustomerQuantity
	 *            the transitToCustomerQuantity to set
	 */
	public void setTransitToCustomerQuantity(String transitToCustomerQuantity) {
		this.transitToCustomerQuantity = transitToCustomerQuantity;
	}

	/**
	 * @return the atCustomerQuantity
	 */
	public String getAtCustomerQuantity() {
		return atCustomerQuantity;
	}

	/**
	 * @param atCustomerQuantity
	 *            the atCustomerQuantity to set
	 */
	public void setAtCustomerQuantity(String atCustomerQuantity) {
		this.atCustomerQuantity = atCustomerQuantity;
	}

	/**
	 * @return the customerConsumedQuantity
	 */
	public String getCustomerConsumedQuantity() {
		return customerConsumedQuantity;
	}

	/**
	 * @param customerConsumedQuantity
	 *            the customerConsumedQuantity to set
	 */
	public void setCustomerConsumedQuantity(String customerConsumedQuantity) {
		this.customerConsumedQuantity = customerConsumedQuantity;
	}

	/**
	 * @return the customerDemand
	 */
	public String getCustomerDemand() {
		return customerDemand;
	}

	/**
	 * @param customerDemand
	 *            the customerDemand to set
	 */
	public void setCustomerDemand(String customerDemand) {
		this.customerDemand = customerDemand;
	}

	/**
	 * @return the fsgDemand
	 */
	public String getFsgDemand() {
		return fsgDemand;
	}

	/**
	 * @param fsgDemand
	 *            the fsgDemand to set
	 */
	public void setFsgDemand(String fsgDemand) {
		this.fsgDemand = fsgDemand;
	}

	/**
	 * @return the fsgShortage
	 */
	public String getFsgShortage() {
		return fsgShortage;
	}

	/**
	 * @param fsgShortage
	 *            the fsgShortage to set
	 */
	public void setFsgShortage(String fsgShortage) {
		this.fsgShortage = fsgShortage;
	}

}
