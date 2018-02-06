package com.ge.current.div.dataFeed.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "fsg_demand_stage", schema = "staging")
public class CustomerDemandEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LICENSE_SEQ")
	@SequenceGenerator(name="LICENSE_SEQ",sequenceName="staging.fsg_demand_stage_seq",allocationSize=1)
	@Column(name = "stage_seq_key")
	private Long seq;
	@Column(name = "material")
	private String material;
	@Column(name = "material_description")
	private String material_Text;
	@Column(name = "quantity")
	private Double quantity;
	@Column(name = "plant")
	private String plant;
	@Column(name = "storage_location")
	private String storage_Location;
	@Column(name = "project_definition")
	private String project_Definition;
	@Column(name = "requirement_date")
	private Date requirement_Date;
	@Column(name = "wbs_element")
	private String wbs_Element;
	@Column(name = "network")
	private String network;
	@Column(name = "activity")
	private String activity;
	@Column(name = "unit_of_measure")
	private String unit_Of_Measure;
	@Column(name = "reservation_purc_req")
	private String reservation_Purc_Req;
	@Column(name = "purchase_requisition")
	private String purchase_Requisition;
	@Column(name = "requisition_item")
	private Double requisition_Item;
	@Column(name = "purchase_ord_exists")
	private String purchase_Ord_Exists;
	@Column(name = "deletion_indicator")
	private String deletion_Indicator;
	@Column(name = "reservation")
	private String reservation;
	
	@Column(name = "file_seq_number")
	private Long file_seq_number;
	@Column(name = "creation_date")
	private Date creationDate;
	@Column(name = "is_processed")
	private String isProcessed;
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getMaterial_Text() {
		return material_Text;
	}
	public void setMaterial_Text(String material_Text) {
		this.material_Text = material_Text;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getStorage_Location() {
		return storage_Location;
	}
	public void setStorage_Location(String storage_Location) {
		this.storage_Location = storage_Location;
	}
	public String getProject_Definition() {
		return project_Definition;
	}
	public void setProject_Definition(String project_Definition) {
		this.project_Definition = project_Definition;
	}
	public Date getRequirement_Date() {
		return requirement_Date;
	}
	public void setRequirement_Date(Date requirement_Date) {
		this.requirement_Date = requirement_Date;
	}
	public String getWbs_Element() {
		return wbs_Element;
	}
	public void setWbs_Element(String wbs_Element) {
		this.wbs_Element = wbs_Element;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getUnit_Of_Measure() {
		return unit_Of_Measure;
	}
	public void setUnit_Of_Measure(String unit_Of_Measure) {
		this.unit_Of_Measure = unit_Of_Measure;
	}
	public String getReservation_Purc_Req() {
		return reservation_Purc_Req;
	}
	public void setReservation_Purc_Req(String reservation_Purc_Req) {
		this.reservation_Purc_Req = reservation_Purc_Req;
	}
	public String getPurchase_Requisition() {
		return purchase_Requisition;
	}
	public void setPurchase_Requisition(String purchase_Requisition) {
		this.purchase_Requisition = purchase_Requisition;
	}
	
	public Double getRequisition_Item() {
		return requisition_Item;
	}
	public void setRequisition_Item(Double requisition_Item) {
		this.requisition_Item = requisition_Item;
	}
	public String getPurchase_Ord_Exists() {
		return purchase_Ord_Exists;
	}
	public void setPurchase_Ord_Exists(String purchase_Ord_Exists) {
		this.purchase_Ord_Exists = purchase_Ord_Exists;
	}
	public String getDeletion_Indicator() {
		return deletion_Indicator;
	}
	public void setDeletion_Indicator(String deletion_Indicator) {
		this.deletion_Indicator = deletion_Indicator;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public Long getFile_seq_number() {
		return file_seq_number;
	}
	public void setFile_seq_number(Long file_seq_number) {
		this.file_seq_number = file_seq_number;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getIsProcessed() {
		return isProcessed;
	}
	public void setIsProcessed(String isProcessed) {
		this.isProcessed = isProcessed;
	}
}
