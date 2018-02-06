package com.ge.current.digitalinventoryservice.fsgoverview.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fsg_material_consumption_rate_veiw", schema = "analytics")
public class FSGMaterialConsumptionRateEntity implements Serializable {

	private static final long serialVersionUID = -5084215650165935117L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonIgnore
	private int id;

	@Column(name = "fsg_location")
	private String fsgLocation;

	@Column(name = "mterial_id")
	private int materialId;

	@Column(name = "demand")
	private int demand;

	@Column(name = "inventory")
	private int inventory;

	@Column(name = "transit")
	private int inTransit;

	@Column(name = "shortage")
	private int shortage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFsgLocation() {
		return fsgLocation;
	}

	public void setFsgLocation(String fsgLocation) {
		this.fsgLocation = fsgLocation;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getInTransit() {
		return inTransit;
	}

	public void setInTransit(int inTransit) {
		this.inTransit = inTransit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getShortage() {
		return shortage;
	}

	public void setShortage(int shortage) {
		this.shortage = shortage;
	}

}
