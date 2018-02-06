package com.ge.current.div.dataFeed.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "asset_installed_certificate", schema = "validstage")
public class CustomerConsumptionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LICENSE_SEQ")
	@SequenceGenerator(name="LICENSE_SEQ",sequenceName="validstage.asset_installed_seq",allocationSize=1)
	@Column(name = "stage_seq_key")
	private Long seq;

	@Column(name = "material_document")
	private String material_Document;
	@Column(name = "movement_type")
	private String movement_Type;
	@Column(name = "material")
	private String material;
	@Column(name = "plant")
	private String plant;
	@Column(name = "storage_location")
	private String storage_Location;
	@Column(name = "currency")
	private String currency;
	@Column(name = "amount_in_lc")
	private Double amount_In_LC;
	@Column(name = "asset_installed")
	private Double quantity;
	@Column(name = "unit_of_measure")
	private String base_Unit_Of_Measure;
	@Column(name = "reservation")
	private String reservation;
	@Column(name = "network")
	private String network;
	@Column(name = "wbs_element")
	private String wbs_Element;
	@Column(name = "wbs_description")
	private String wbs_Discription;
	@Column(name = "wbs_code")
	private String wbs_Code;
	@Column(name = "posting_date")
	private Date posting_Date;
	@Column(name = "reference")
	private String reference;
	@Column(name = "delivery")
	private String delivery;
      @Column(name = "file_seq_number")
      private Long file_seq_number;
	 
	@Column(name = "creation_date")
	private Date creationDate;
	@Column(name = "is_processed")
	private String isProcessed;
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public Long getFile_seq_number() {
		return file_seq_number;
	}
	public void setFile_seq_number(Long file_seq_number) {
		this.file_seq_number = file_seq_number;
	}
	public String getMaterial_Document() {
		return material_Document;
	}
	public void setMaterial_Document(String material_Document) {
		this.material_Document = material_Document;
	}
	public String getMovement_Type() {
		return movement_Type;
	}
	public void setMovement_Type(String movement_Type) {
		this.movement_Type = movement_Type;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getAmount_In_LC() {
		return amount_In_LC;
	}
	public void setAmount_In_LC(Double amount_In_LC) {
		this.amount_In_LC = amount_In_LC;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getBase_Unit_Of_Measure() {
		return base_Unit_Of_Measure;
	}
	public void setBase_Unit_Of_Measure(String base_Unit_Of_Measure) {
		this.base_Unit_Of_Measure = base_Unit_Of_Measure;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getWbs_Element() {
		return wbs_Element;
	}
	public void setWbs_Element(String wbs_Element) {
		this.wbs_Element = wbs_Element;
	}
	public String getWbs_Discription() {
		return wbs_Discription;
	}
	public void setWbs_Discription(String wbs_Discription) {
		this.wbs_Discription = wbs_Discription;
	}
	public String getWbs_Code() {
		return wbs_Code;
	}
	public void setWbs_Code(String wbs_Code) {
		this.wbs_Code = wbs_Code;
	}
	public Date getPosting_Date() {
		return posting_Date;
	}
	public void setPosting_Date(Date posting_Date) {
		this.posting_Date = posting_Date;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
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
