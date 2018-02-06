package com.ge.current.digitalinventoryservice.totalinventory.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FSG_DAILY_STOCK_NET_INVENTORY_VIEW",schema="analytics")
public class TotalNetInventoryAtFSGDetailsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer fsg_daily_stock_id;
	
	@Column(name="STORAGE_LOCATION")
	private String location;
	
	private String material;
	
	@Column(name="material_description")
	private String materialDescription;
	
	@Column(name="prev_stock")
	private Double previousStock;
	
	@Column(name="curr_stock")
	private Double currentStock;
	
	@Column(name="BO")
	private Double backOrder;
	
	@Column(name="COM")
	private Double committed;
	
	@Column(name="UN")
	private Double unAvailableStock;
	
	@Column(name="NET")
	private Double netAvailable;
	
	private Double issued;
	
	private Double received;
	
	@Column(name="unit_of_measure")
	private String unitOfMeasure;
	
	private Double cost;

	public Double getUnAvailableStock() {
		return unAvailableStock;
	}

	public void setUnAvailableStock(Double unAvailableStock) {
		this.unAvailableStock = unAvailableStock;
	}

	public Integer getFsg_daily_stock_id() {
		return fsg_daily_stock_id;
	}

	public void setFsg_daily_stock_id(Integer fsg_daily_stock_id) {
		this.fsg_daily_stock_id = fsg_daily_stock_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Double getPreviousStock() {
		return previousStock;
	}

	public void setPreviousStock(Double previousStock) {
		this.previousStock = previousStock;
	}

	public Double getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(Double currentStock) {
		this.currentStock = currentStock;
	}

	public Double getBackOrder() {
		return backOrder;
	}

	public void setBackOrder(Double backOrder) {
		this.backOrder = backOrder;
	}

	public Double getCommitted() {
		return committed;
	}

	public void setCommitted(Double committed) {
		this.committed = committed;
	}

	public Double getNetAvailable() {
		return netAvailable;
	}

	public void setNetAvailable(Double netAvailable) {
		this.netAvailable = netAvailable;
	}

	public Double getIssued() {
		return issued;
	}

	public void setIssued(Double issued) {
		this.issued = issued;
	}

	public Double getReceived() {
		return received;
	}

	public void setReceived(Double received) {
		this.received = received;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
}
