/**
 * 
 */
package com.ge.current.digitalinventoryservice.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author ncheredd
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TIShipmentTransitConsumedDetailsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String plant;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String material;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("materialdesc")
	private String materialDescription;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("Quantity")
	private Integer quantity;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String location;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("unitofmeasure")
	private String unitOfMeasure;

	@JsonProperty("custcode")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String customerCode;

	@JsonProperty("custname")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String customerName;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("receiveddate")
	private String receivedDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("issuedate")
	private String issueDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("ponumber")
	private String poNumber;

	@JsonProperty("receivedfrom")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String receivedFrom;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String status;

	@JsonProperty("orderstatus")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String orderStatus;

	@JsonProperty("installationdate")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String installationDate;

	@JsonProperty("demand_week")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String demandWeek;

	@JsonProperty("productcost")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double productCost;

	@JsonProperty("carriername")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String carrierName;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("pronumber")
	private String proNumber;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String year;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("demand_qty")
	private Integer demandQty;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("shortage_qty")
	private Integer shortageQty;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("transaction_date")
	private String transactionDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("curr_stock")
	private Integer currentStock;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("prev_stock")
	private Integer previousStock;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("BO")
	private Integer backOrder;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("COM")
	private Integer committed;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("UN")
	private Integer unavailableStock;
		
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("NET")
	private Integer netAvailable;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("issued")
	private Integer issued;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("received")
	private Integer received;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("total_demand_qty")
	private Integer totalDemandQty;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("total_shortage_qty")
	private Integer totalShortageQty; 
	
	
	/**
	 * @return the plant
	 */
	public String getPlant() {
		return plant;
	}

	/**
	 * @param plant
	 *            the plant to set
	 */
	public void setPlant(String plant) {
		this.plant = UtilManager.isJsonValidString(plant);
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(String material) {
		this.material = UtilManager.isJsonValidString(material);
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = UtilManager.setDefaultValueToJsonInteger(quantity);
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = UtilManager.isJsonValidString(location);
	}

	/**
	 * @return the poNumber
	 */
	public String getPoNumber() {
		return poNumber;
	}

	/**
	 * @param poNumber
	 *            the poNumber to set
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = UtilManager.isJsonValidString(poNumber);
	}

	/**
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * @param customerCode
	 *            the customerCode to set
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = UtilManager.isJsonValidString(customerCode);
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = UtilManager.isJsonValidString(customerName);
	}

	/**
	 * @return the receivedDate
	 */
	public String getReceivedDate() {
		return receivedDate;
	}

	/**
	 * @param receivedDate
	 *            the receivedDate to set
	 */
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = UtilManager.isJsonValidString(receivedDate);
	}

	/**
	 * @return the proNumber
	 */
	public String getProNumber() {
		return proNumber;
	}

	/**
	 * @param proNumber
	 *            the proNumber to set
	 */
	public void setProNumber(String proNumber) {
		this.proNumber = UtilManager.isJsonValidString(proNumber);
	}

	/**
	 * @return the receivedFrom
	 */
	public String getReceivedFrom() {
		return receivedFrom;
	}

	/**
	 * @param receivedFrom
	 *            the receivedFrom to set
	 */
	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = UtilManager.isJsonValidString(receivedFrom);
	}

	/**
	 * @return the materialDescription
	 */
	public String getMaterialDescription() {
		return materialDescription;
	}

	/**
	 * @param materialDescription
	 *            the materialDescription to set
	 */
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = UtilManager
				.isJsonValidString(materialDescription);
	}

	/**
	 * @return the unitOfMeasure
	 */
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	/**
	 * @param unitOfMeasure
	 *            the unitOfMeasure to set
	 */
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = UtilManager.isJsonValidString(unitOfMeasure);
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = UtilManager.isJsonValidString(status);
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus
	 *            the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = UtilManager.isJsonValidString(orderStatus);
	}

	/**
	 * @return the installationDate
	 */
	public String getInstallationDate() {
		return installationDate;
	}

	/**
	 * @param installationDate
	 *            the installationDate to set
	 */
	public void setInstallationDate(String installationDate) {
		this.installationDate = UtilManager.isJsonValidString(installationDate);
	}

	/**
	 * @return the demandWeek
	 */
	public String getDemandWeek() {
		return demandWeek;
	}

	/**
	 * @param demandWeek
	 *            the demandWeek to set
	 */
	public void setDemandWeek(String demandWeek) {
		this.demandWeek = UtilManager.isJsonValidString(demandWeek);
	}

	/**
	 * @return the issueDate
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate
	 *            the issueDate to set
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = UtilManager.isJsonValidString(issueDate);
	}

	/**
	 * @return the productCost
	 */
	public Double getProductCost() {
		return productCost;
	}

	/**
	 * @param productCost
	 *            the productCost to set
	 */
	public void setProductCost(Double productCost) {
		this.productCost = UtilManager.setDefaultValueToJsonDouble(productCost);
	}

	/**
	 * @return the carrierName
	 */
	public String getCarrierName() {
		return carrierName;
	}

	/**
	 * @param carrierName
	 *            the carrierName to set
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = UtilManager.isJsonValidString(carrierName);
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = UtilManager.isJsonValidString(year);
	}

	/**
	 * @return the demandQty
	 */
	public Integer getDemandQty() {
		return demandQty;
	}

	/**
	 * @param demandQty
	 *            the demandQty to set
	 */
	public void setDemandQty(Integer demandQty) {
		this.demandQty = UtilManager.setDefaultValueToJsonInteger(demandQty);
	}

	/**
	 * @return the shortageQty
	 */
	public Integer getShortageQty() {
		return shortageQty;
	}

	/**
	 * @param shortageQty
	 *            the shortageQty to set
	 */
	public void setShortageQty(Integer shortageQty) {
		this.shortageQty = UtilManager
				.setDefaultValueToJsonInteger(shortageQty);
	}

	/**
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(Integer currentStock) {
		this.currentStock = currentStock;
	}

	public Integer getPreviousStock() {
		return previousStock;
	}

	public void setPreviousStock(Integer previousStock) {
		this.previousStock = previousStock;
	}

	public Integer getBackOrder() {
		return backOrder;
	}

	public void setBackOrder(Integer backOrder) {
		this.backOrder = backOrder;
	}

	public Integer getCommitted() {
		return committed;
	}

	public void setCommitted(Integer committed) {
		this.committed = committed;
	}
	public Integer getUnavailableStock() {
		return unavailableStock;
	}

	public void setUnavailableStock(Integer unavailableStock) {
		this.unavailableStock = unavailableStock;
	}
	public Integer getNetAvailable() {
		return netAvailable;
	}

	public void setNetAvailable(Integer netAvailable) {
		this.netAvailable = netAvailable;
	}

	public Integer getIssued() {
		return issued;
	}

	public void setIssued(Integer issued) {
		this.issued = issued;
	}

	public Integer getReceived() {
		return received;
	}

	public void setReceived(Integer received) {
		this.received = received;
	}
	
	public Integer getTotalDemandQty() {
		return totalDemandQty;
	}

	public void setTotalDemandQty(Integer totalDemandQty) {
		this.totalDemandQty = totalDemandQty;
	}

	public Integer getTotalShortageQty() {
		return totalShortageQty;
	}

	public void setTotalShortageQty(Integer totalShortageQty) {
		this.totalShortageQty = totalShortageQty;
	} 
}