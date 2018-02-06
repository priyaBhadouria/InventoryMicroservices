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
@Table(name = "sap_purchasing_order_stage", schema = "staging")
public class PurchasingOrderEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LICENSE_SEQ")
	@SequenceGenerator(name="LICENSE_SEQ",sequenceName="staging.core_purchase_order_seq",allocationSize=1)
	@Column(name = "stage_seq_key")
	private Long seq;
	
	@Column(name = "purchasing_document")
	private String purchasing_Document;
	
	private Double item;
	private String vendor;
	
	@Column(name = "material_group")
	private String material_Group;
	private String material;
	@Column(name = "purch_organization")
	private String purch_Organization;
	@Column(name = "purch_group")
	private String purch_Group;
	private String plant;
	@Column(name = "storage_location")
	private String storage_Location;

	@Column(name = "document_date")
	private Date document_Date;
	@Column(name = "order_qty")
	private Double order_Qty;
	@Column(name = "order_unit")
	private String order_Unit;
	@Column(name = "net_order_value")
	private Double net_Order_Value;
	private String currency;
	@Column(name = "order_price_unit")
	private String order_Price_Unit;
	private String incomplete;
	@Column(name = "purchasing_doc_type")
	private String purchasing_Doc_Type;
	@Column(name = "supplying_plant")
	private String supplying_Plant;
	@Column(name = "material_description")
	private String short_Text;

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
	public String getPurchasing_Document() {
		return purchasing_Document;
	}
	public void setPurchasing_Document(String purchasing_Document) {
		this.purchasing_Document = purchasing_Document;
	}
	public Double getItem() {
		return item;
	}
	public void setItem(Double item) {
		this.item = item;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getMaterial_Group() {
		return material_Group;
	}
	public void setMaterial_Group(String material_Group) {
		this.material_Group = material_Group;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getPurch_Organization() {
		return purch_Organization;
	}
	public void setPurch_Organization(String purch_Organization) {
		this.purch_Organization = purch_Organization;
	}
	public String getPurch_Group() {
		return purch_Group;
	}
	public void setPurch_Group(String purch_Group) {
		this.purch_Group = purch_Group;
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
	public Date getDocument_Date() {
		return document_Date;
	}
	public void setDocument_Date(Date document_Date) {
		this.document_Date = document_Date;
	}
	public Double getOrder_Qty() {
		return order_Qty;
	}
	public void setOrder_Qty(Double order_Qty) {
		this.order_Qty = order_Qty;
	}
	public String getOrder_Unit() {
		return order_Unit;
	}
	public void setOrder_Unit(String order_Unit) {
		this.order_Unit = order_Unit;
	}
	public Double getNet_Order_Value() {
		return net_Order_Value;
	}
	public void setNet_Order_Value(Double net_Order_Value) {
		this.net_Order_Value = net_Order_Value;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getOrder_Price_Unit() {
		return order_Price_Unit;
	}
	public void setOrder_Price_Unit(String order_Price_Unit) {
		this.order_Price_Unit = order_Price_Unit;
	}
	public String getIncomplete() {
		return incomplete;
	}
	public void setIncomplete(String incomplete) {
		this.incomplete = incomplete;
	}
	public String getPurchasing_Doc_Type() {
		return purchasing_Doc_Type;
	}
	public void setPurchasing_Doc_Type(String purchasing_Doc_Type) {
		this.purchasing_Doc_Type = purchasing_Doc_Type;
	}
	public String getSupplying_Plant() {
		return supplying_Plant;
	}
	public void setSupplying_Plant(String supplying_Plant) {
		this.supplying_Plant = supplying_Plant;
	}
	public String getShort_Text() {
		return short_Text;
	}
	public void setShort_Text(String short_Text) {
		this.short_Text = short_Text;
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
