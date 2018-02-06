package com.ge.current.digitalinventoryservice.totalTransaction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ge.current.digitalinventoryservice.util.UtilManager;

public class TotalInventoryTransactionDTO {

	private String source;

	private String sourceType;

	private String destination;

	private String destinationType;

	private String material;

	private String materialDescription;

	private Double quantity;

	private String transactionType;

	private String shipmentOrder;

	private String customerPO;

	private String trackingNumber;

	private String transactionDate;

	private Long stoFrom;

	private Long stoTo;

	private String dateFrom;

	private String dateTo;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = UtilManager.isJsonValidString(source);
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = UtilManager.isJsonValidString(sourceType);
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = UtilManager.isJsonValidString(destination);
	}

	public String getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(String destinationType) {
		this.destinationType = UtilManager.isJsonValidString(destinationType);
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = UtilManager.isJsonValidString(material);
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = UtilManager
				.isJsonValidString(materialDescription);
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return transactionType;
	}

	public void setType(String type) {
		this.transactionType = UtilManager.isJsonValidString(type);
	}

	public String getShipmentOrder() {
		return shipmentOrder;
	}

	public void setShipmentOrder(String shipmentOrder) {
		this.shipmentOrder =shipmentOrder;
	}

	public String getCustomerPO() {
		return customerPO;
	}

	public void setCustomerPO(String customerPO) {
		this.customerPO = UtilManager.isJsonValidString(customerPO);
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = UtilManager.isJsonValidString(trackingNumber);
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = UtilManager.isJsonValidString(transactionDate);
	}

	public Long getStoFrom() {
		return stoFrom;
	}

	public void setStoFrom(Long stoFrom) {
		this.stoFrom = stoFrom;
	}

	public Long getStoTo() {
		return stoTo;
	}

	public void setStoTo(Long stoTo) {
		this.stoTo = stoTo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = UtilManager.isJsonValidString(dateFrom);
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = UtilManager.isJsonValidString(dateTo);
	}

}
