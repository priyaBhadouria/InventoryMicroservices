
package com.ge.current.digitalinventoryservice.totalinventory.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="installer_received_details_view",schema="analytics")
public class InstallerReceivedEntity

{
	
	@Id
	private Integer id;
	
	@Column(name="chr_number")
private String FSGShipmentNumber;
	
	@Column(name="customer_name")
	private String customerNumber;
	
	@Column(name="material")
	private String material;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="unit_of_measure")
	private String uom;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="origin_pickup_name")
	private String originPickUpName;
	
	@Column(name="origin_pickup_address_1")
	private String originPickUpAddress1;
	
	@Column(name="origin_pickup_city")
	private String originPickUpCity;
	
	@Column(name="origin_pickup_state")
	private String originPickUpState;
	
	@Column(name="destination_delivery_name")
	private String destinationDeliveryName;
	
	
	@Column(name="destination_delivery_address_1")
	private String destinationDeliveryAddress1;
	
	@Column(name="destination_delivery_state")
	private String destinationDeliveryState;

	
	@Column(name="actual_pick_up_arrival_date")
	private Date actualPickUpArrivalDate;
	
	@Column(name="actual_delivery_arrival_date")
	private Date actualDeliveryArrivalDate;

	public String getFSGShipmentNumber() {
		return FSGShipmentNumber;
	}

	public void setFSGShipmentNumber(String fSGShipmentNumber) {
		FSGShipmentNumber = fSGShipmentNumber;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOriginPickUpName() {
		return originPickUpName;
	}

	public void setOriginPickUpName(String originPickUpName) {
		this.originPickUpName = originPickUpName;
	}

	public String getOriginPickUpAddress1() {
		return originPickUpAddress1;
	}

	public void setOriginPickUpAddress1(String originPickUpAddress1) {
		this.originPickUpAddress1 = originPickUpAddress1;
	}

	public String getOriginPickUpCity() {
		return originPickUpCity;
	}

	public void setOriginPickUpCity(String originPickUpCity) {
		this.originPickUpCity = originPickUpCity;
	}

	public String getOriginPickUpState() {
		return originPickUpState;
	}

	public void setOriginPickUpState(String originPickUpState) {
		this.originPickUpState = originPickUpState;
	}

	public String getDestinationDeliveryName() {
		return destinationDeliveryName;
	}

	public void setDestinationDeliveryName(String destinationDeliveryName) {
		this.destinationDeliveryName = destinationDeliveryName;
	}

	public String getDestinationDeliveryAddress1() {
		return destinationDeliveryAddress1;
	}

	public void setDestinationDeliveryAddress1(String destinationDeliveryAddress1) {
		this.destinationDeliveryAddress1 = destinationDeliveryAddress1;
	}

	public String getDestinationDeliveryState() {
		return destinationDeliveryState;
	}

	public void setDestinationDeliveryState(String destinationDeliveryState) {
		this.destinationDeliveryState = destinationDeliveryState;
	}

	public Date getActualPickUpArrivalDate() {
		return actualPickUpArrivalDate;
	}

	public void setActualPickUpArrivalDate(Date actualPickUpArrivalDate) {
		this.actualPickUpArrivalDate = actualPickUpArrivalDate;
	}

	public Date getActualDeliveryArrivalDate() {
		return actualDeliveryArrivalDate;
	}

	public void setActualDeliveryArrivalDate(Date actualDeliveryArrivalDate) {
		this.actualDeliveryArrivalDate = actualDeliveryArrivalDate;
	}



}