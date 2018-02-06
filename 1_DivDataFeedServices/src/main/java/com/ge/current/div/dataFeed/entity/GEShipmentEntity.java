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
@Table(name = "plant_sap_stage", schema = "staging")
public class GEShipmentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LICENSE_SEQ")
	@SequenceGenerator(name="LICENSE_SEQ",sequenceName="staging.stage_seq_key_seq",allocationSize=1)
	@Column(name = "stage_seq_key")
	private Long seq;
	@Column(name = "delivery_number")
	private Long delivery_Number;
	@Column(name = "delivery_item_number")
	private Double delivery_Item;
	@Column(name = "delivery_cr_date")
	private Date delivery_Cr_Date;
	@Column(name = "material")
	private String material_Number;
	@Column(name = "plant")
	private String plant;
	@Column(name = "quantity")
	private Double del_Quantity;
	@Column(name = "rec_plant")
	private String rec_Plant;
	@Column(name = "storage_location")
	private String rec_Storage_Location;
	@Column(name = "sto")
	private Long order_Number;
	@Column(name = "sto_item")
	private Double order_Item;
	@Column(name = "material_description")
	private String material_Description;
	@Column(name = "shipment_num")
	private Long shipment_Number;
	@Column(name = "shipment_crd")
	private Date shipment_Crd;
	@Column(name = "shipment_to_country")
	private String ship_To_Country;
	@Column(name = "shipment_to_name")
	private String ship_To_Name;
	@Column(name = "shipment_to_street")
	private String ship_To_Street;
	@Column(name = "shipment_to_zipcode")
	private String ship_To_Postal_Code;
	@Column(name = "shipment_to_city")
	private String ship_To_City;
	@Column(name = "carrier_name")
	private String carrier_Name;
	@Column(name = "pro_number")
	private String pro_Number;
	@Column(name = "unit_of_measure")
	private String uom;

	// direct value
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

	public Long getDelivery_Number() {
		return delivery_Number;
	}

	public void setDelivery_Number(Long delivery_Number) {
		this.delivery_Number = delivery_Number;
	}

	public Double getDelivery_Item() {
		return delivery_Item;
	}

	public void setDelivery_Item(Double delivery_Item) {
		this.delivery_Item = delivery_Item;
	}

	public Date getDelivery_Cr_Date() {
		return delivery_Cr_Date;
	}

	public void setDelivery_Cr_Date(Date delivery_Cr_Date) {
		this.delivery_Cr_Date = delivery_Cr_Date;
	}

	public String getMaterial_Number() {
		return material_Number;
	}

	public void setMaterial_Number(String material_Number) {
		this.material_Number = material_Number;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public Double getDel_Quantity() {
		return del_Quantity;
	}

	public void setDel_Quantity(Double del_quantity) {
		this.del_Quantity = del_quantity;
	}

	public String getRec_Plant() {
		return rec_Plant;
	}

	public void setRec_Plant(String rec_Plant) {
		this.rec_Plant = rec_Plant;
	}

	public String getRec_Storage_Location() {
		return rec_Storage_Location;
	}

	public void setRec_Storage_Location(String rec_Storage_Location) {
		this.rec_Storage_Location = rec_Storage_Location;
	}

	public Long getOrder_Number() {
		return order_Number;
	}

	public void setOrder_Number(Long order_Number) {
		this.order_Number = order_Number;
	}

	public Double getOrder_Item() {
		return order_Item;
	}

	public void setOrder_Item(Double order_Item) {
		this.order_Item = order_Item;
	}

	public String getMaterial_Description() {
		return material_Description;
	}

	public void setMaterial_Description(String material_Description) {
		this.material_Description = material_Description;
	}

	public Long getShipment_Number() {
		return shipment_Number;
	}

	public void setShipment_Number(Long shipment_Number) {
		this.shipment_Number = shipment_Number;
	}

	public Date getShipment_Crd() {
		return shipment_Crd;
	}

	public void setShipment_Crd(Date shipment_Crd) {
		this.shipment_Crd = shipment_Crd;
	}

	public String getShip_To_Country() {
		return ship_To_Country;
	}

	public void setShip_To_Country(String ship_To_Country) {
		this.ship_To_Country = ship_To_Country;
	}

	public String getShip_To_Name() {
		return ship_To_Name;
	}

	public void setShip_To_Name(String ship_To_Name) {
		this.ship_To_Name = ship_To_Name;
	}

	public String getShip_To_Street() {
		return ship_To_Street;
	}

	public void setShip_To_Street(String ship_To_Street) {
		this.ship_To_Street = ship_To_Street;
	}

	public String getShip_To_Postal_Code() {
		return ship_To_Postal_Code;
	}

	public void setShip_To_Postal_Code(String ship_To_Postal_Code) {
		this.ship_To_Postal_Code = ship_To_Postal_Code;
	}

	public String getShip_To_City() {
		return ship_To_City;
	}

	public void setShip_To_City(String ship_To_City) {
		this.ship_To_City = ship_To_City;
	}

	public String getCarrier_Name() {
		return carrier_Name;
	}

	public void setCarrier_Name(String carrier_Name) {
		this.carrier_Name = carrier_Name;
	}

	public String getPro_Number() {
		return pro_Number;
	}

	public void setPro_Number(String pro_Number) {
		this.pro_Number = pro_Number;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
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

	public Long getFile_seq_number() {
		return file_seq_number;
	}

	public void setFile_seq_number(Long file_seq_number) {
		this.file_seq_number = file_seq_number;
	}

	/*
	 * public int getSeq() { return seq; } public void setSeq(int seq) {
	 * this.seq = seq; }
	 */

}
