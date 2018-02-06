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
@Table(name = "fsg_consumption_rate_all_veiw", schema = "analytics")
public class FSGConsumptionRateOverViewEntity implements Serializable {

	private static final long serialVersionUID = -7779454521129959506L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonIgnore
	private int id;

	@Column(name = "fsg_location")
	private String fsgLocation;

	@Column(name = "fsg_transit")
	private int totalTransitToFSG;

	@Column(name = "fsg_inventory")
	private int totalFSGQuantity;

	@Column(name = "fsg_demand")
	private int totalFSGDemand;

	@Column(name = "fsg_shortage")
	private int totalFSGShortage;

	public int getTotalTransitToFSG() {
		return totalTransitToFSG;
	}

	public void setTotalTransitToFSG(int totalTransitToFSG) {
		this.totalTransitToFSG = totalTransitToFSG;
	}

	public int getTotalFSGQuantity() {
		return totalFSGQuantity;
	}

	public void setTotalFSGQuantity(int totalFSGQuantity) {
		this.totalFSGQuantity = totalFSGQuantity;
	}

	public int getTotalFSGDemand() {
		return totalFSGDemand;
	}

	public void setTotalFSGDemand(int totalFSGDemand) {
		this.totalFSGDemand = totalFSGDemand;
	}

	public int getTotalFSGShortage() {
		return totalFSGShortage;
	}

	public void setTotalFSGShortage(int totalFSGShortage) {
		this.totalFSGShortage = totalFSGShortage;
	}

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

}
