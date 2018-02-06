package com.ge.current.digitalinventoryservice.totalTransaction.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="summary_view_stock_at_service_provider", schema="analytics")
public class TotalFSGSummaryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="on_hand")
	private Double onHand;
	
	@Column(name="storage_location")
	private String fsgLocation;
	
	@Column(name="fsg_desc")
	private String fsgDesc;
	
	@Column(name="on_hand_cost")
	private Double onHandCost;
	
	@Column(name="intransit")
	private Double fsgTransit;
	
	@Column(name="transit_cost")
	private Double fsgTransitCost;
	
	@Column(name="demand")
	private Double fsgDemand;
	
	@Column(name="demand_cost")
	private Double fsgDemandCost;
	
	@Column(name="shortage")
	private Double fsgShortage;
	
	@Column(name="shortage_cost")
	private Double fsgShortageCost;

	public String getFsgLocation() {
		return fsgLocation;
	}

	public void setFsgLocation(String fsgLocation) {
		this.fsgLocation = fsgLocation;
	}

	public Double getFsgTransitCost() {
		return fsgTransitCost;
	}

	public void setFsgTransitCost(Double fsgTransitCost) {
		this.fsgTransitCost = fsgTransitCost;
	}

	public Double getFsgDemandCost() {
		return fsgDemandCost;
	}

	public void setFsgDemandCost(Double fsgDemandCost) {
		this.fsgDemandCost = fsgDemandCost;
	}

	public Double getFsgShortageCost() {
		return fsgShortageCost;
	}

	public void setFsgShortageCost(Double fsgShortageCost) {
		this.fsgShortageCost = fsgShortageCost;
	}

	public String getFsgDesc() {
		return fsgDesc;
	}

	public void setFsgDesc(String fsgDesc) {
		this.fsgDesc = fsgDesc;
	}

	public Double getOnHand() {
		return onHand;
	}

	public void setOnHand(Double onHand) {
		this.onHand = onHand;
	}

	public Double getOnHandCost() {
		return onHandCost;
	}

	public void setOnHandCost(Double onHandCost) {
		this.onHandCost = onHandCost;
	}

	public Double getFsgTransit() {
		return fsgTransit;
	}

	public void setFsgTransit(Double fsgTransit) {
		this.fsgTransit = fsgTransit;
	}

	public Double getFsgDemand() {
		return fsgDemand;
	}

	public void setFsgDemand(Double fsgDemand) {
		this.fsgDemand = fsgDemand;
	}

	public Double getFsgShortage() {
		return fsgShortage;
	}

	public void setFsgShortage(Double fsgShortage) {
		this.fsgShortage = fsgShortage;
	}
	
	
}
