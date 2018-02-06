package com.ge.current.digitalinventoryservice.totalTransaction.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ge.current.digitalinventoryservice.totalTransaction.dto.TotalInventoryTransactionDTO;

@Entity
@Table(name = "total_inventory_transaction_view", schema = "analytics")
public class TotalInventoryTransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
	@Column(name = "transaction_id")private Long transactionId;
	private String source;
	@Column(name="source_type")private String sourceType;
	private String destination;
	@Column(name="destination_type")private String destinationType;
	
	
	private String material;
	@Column(name="material_description")private String materialDescription;
	private Double quantity;
	@Column(name="transaction_type")private String transactionType;
	@Column(name="transaction_date")private Date transactionDate;

	//null
	@Column(name="shipment_order")private Long shipmentOrder;
	@Column(name="customer_po")private String customerPO;
	@Column(name="tracking_number")private String trackingNumber;
		public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
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
		this.transactionType = type;
	}
	public Long getShipmentOrder() {
		return shipmentOrder;
	}
	public void setShipmentOrder(Long shipmentOrder) {
		this.shipmentOrder = shipmentOrder;
	}
	public String getCustomerPO() {
		return customerPO;
	}
	public void setCustomerPO(String customerPO) {
		this.customerPO = customerPO;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
