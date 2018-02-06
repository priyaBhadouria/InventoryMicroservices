package com.ge.current.digitalinventoryservice.totalTransaction.dto;

public class TotalFSGSummaryDTO {

	private String fsg_location;
	private String location_desc;
	private Double onHand;
	private Double demand;
	private String demandCost;
	private Double inTransit;
	private String inTransitCost;
	private Double shortage;
	private String shortageCost;
	private String onHandCost;
	public String getFsg_location() {
		return fsg_location;
	}
	public void setFsg_location(String fsg_location) {
		this.fsg_location = fsg_location;
	}
	public String getDemandCost() {
		return demandCost;
	}
	public void setDemandCost(String demandCost) {
		this.demandCost = demandCost;
	}
	public String getInTransitCost() {
		return inTransitCost;
	}
	public void setInTransitCost(String inTransitCost) {
		this.inTransitCost = inTransitCost;
	}
	public String getShortageCost() {
		return shortageCost;
	}
	public void setShortageCost(String shortageCost) {
		this.shortageCost = shortageCost;
	}
	public String getLocation_desc() {
		return location_desc;
	}
	public void setLocation_desc(String location_desc) {
		this.location_desc = location_desc;
	}
	public Double getOnHand() {
		return onHand;
	}
	public void setOnHand(Double onHand) {
		this.onHand = onHand;
	}
	public Double getDemand() {
		return demand;
	}
	public void setDemand(Double demand) {
		this.demand = demand;
	}
	public Double getInTransit() {
		return inTransit;
	}
	public void setInTransit(Double inTransit) {
		this.inTransit = inTransit;
	}
	public Double getShortage() {
		return shortage;
	}
	public void setShortage(Double shortage) {
		this.shortage = shortage;
	}
	public String getOnHandCost() {
		return onHandCost;
	}
	public void setOnHandCost(String string) {
		this.onHandCost = string;
	}
	
	
}
