package com.ge.current.digitalinventoryservice.fsgoverview.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "All_Location_consumption_rate_veiw", schema = "analytics")
public class TotalConsumptionRateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4921937939988777676L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "all_inventory")
	private int allInventory;

	@Column(name = "all_transit")
	private int allTransit;

	@Column(name = "all_demand")
	private int allDemand;

	@Column(name = "all_shortage")
	private int allShortage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAllInventory() {
		return allInventory;
	}

	public void setAllInventory(int allInventory) {
		this.allInventory = allInventory;
	}

	public int getAllTransit() {
		return allTransit;
	}

	public void setAllTransit(int allTransit) {
		this.allTransit = allTransit;
	}

	public int getAllDemand() {
		return allDemand;
	}

	public void setAllDemand(int allDemand) {
		this.allDemand = allDemand;
	}

	public int getAllShortage() {
		return allShortage;
	}

	public void setAllShortage(int allShortage) {
		this.allShortage = allShortage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}