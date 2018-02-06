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

import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "core_shipment_stage", schema = "staging")
public class CoreShipmentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LICENSE_SEQ")
	@SequenceGenerator(name="LICENSE_SEQ",sequenceName="staging.core_shipment_stage_seq",allocationSize=1)
	@Column(name = "stage_seq_key")
	private Long seq;
	@Column(name = "storage_location")
	private String ship_To_Name;
	@Column(name = "order_num")
	private Long order_Number;
	@Column(name = "customer_po")
	private String customer_Purchase_Order_Number;
	@Column(name = "order_entry_date")
	private Date order_Entry_Date;
	@Column(name = "order_sum_del_req_date")
	private Date order_Summary_Del_Req_Date;
	@Column(name = "material")
	private String product_Code;
	@Column(name = "material_description")
	private String product_Description;
	@Column(name = "order_item_qty_unexp")
	private Double order_Item_Quantity_UnExp;
	@Column(name = "order_item_ship_qty_unexp")
	private Double order_Item_Shipped_Qty_UnExp;
	@Column(name = "ship_line_status_code")
	private String ship_Line_Status_Code;
	@Column(name = "pick_tick_num")
	private String pick_Ticket_Number;
	@Column(name = "plant")
	private String component_Number;
	@Column(name = "bol")
	private String BOL;
	@Column(name = "carrier_scac_code")
	private String carrier_SCAC_Code;
	@Column(name = "bol_pro_number")
	private String BOL_Pro_Number;
	@Column(name = "ship_line_ship_date")
	private Date ship_Line_Ship_Date;

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

	public String getShip_To_Name() {
		return ship_To_Name;
	}

	public void setShip_To_Name(String ship_To_Name) {
		this.ship_To_Name = ship_To_Name;
	}

	public Long getOrder_Number() {
		return order_Number;
	}

	public void setOrder_Number(Long order_Number) {
		this.order_Number = order_Number;
	}

	public String getCustomer_Purchase_Order_Number() {
		return customer_Purchase_Order_Number;
	}

	public void setCustomer_Purchase_Order_Number(
			String customer_Purchase_Order_Number) {
		this.customer_Purchase_Order_Number = customer_Purchase_Order_Number;
	}

	public Date getOrder_Entry_Date() {
		return order_Entry_Date;
	}

	public void setOrder_Entry_Date(Date order_Entry_Date) {
		this.order_Entry_Date = order_Entry_Date;
	}

	public Date getOrder_Summary_Del_Req_Date() {
		return order_Summary_Del_Req_Date;
	}

	public void setOrder_Summary_Del_Req_Date(Date order_Summary_Del_Req_Date) {
		this.order_Summary_Del_Req_Date = order_Summary_Del_Req_Date;
	}

	public String getProduct_Code() {
		return product_Code;
	}

	public void setProduct_Code(String product_Code) {
		this.product_Code = product_Code;
	}

	public String getProduct_Description() {
		return product_Description;
	}

	public void setProduct_Description(String product_Description) {
		this.product_Description = product_Description;
	}

	public Double getOrder_Item_Quantity_UnExp() {
		return order_Item_Quantity_UnExp;
	}

	public void setOrder_Item_Quantity_UnExp(Double order_Item_Quantity_UnExp) {
		this.order_Item_Quantity_UnExp = order_Item_Quantity_UnExp;
	}

	public Double getOrder_Item_Shipped_Qty_UnExp() {
		return order_Item_Shipped_Qty_UnExp;
	}

	public void setOrder_Item_Shipped_Qty_UnExp(
			Double order_Item_Shipped_Qty_UnExp) {
		this.order_Item_Shipped_Qty_UnExp = order_Item_Shipped_Qty_UnExp;
	}

	public String getShip_Line_Status_Code() {
		return ship_Line_Status_Code;
	}

	public void setShip_Line_Status_Code(String ship_Line_Status_Code) {
		this.ship_Line_Status_Code = ship_Line_Status_Code;
	}

	public String getPick_Ticket_Number() {
		return pick_Ticket_Number;
	}

	public void setPick_Ticket_Number(String pick_Ticket_Number) {
		this.pick_Ticket_Number = pick_Ticket_Number;
	}

	public String getComponent_Number() {
		return component_Number;
	}

	public void setComponent_Number(String component_Number) {
		this.component_Number = component_Number;
	}

	public String getBOL() {
		return BOL;
	}

	public void setBOL(String bOL) {
		BOL = bOL;
	}

	public String getCarrier_SCAC_Code() {
		return carrier_SCAC_Code;
	}

	public void setCarrier_SCAC_Code(String carrier_SCAC_Code) {
		this.carrier_SCAC_Code = carrier_SCAC_Code;
	}

	public String getBOL_Pro_Number() {
		return BOL_Pro_Number;
	}

	public void setBOL_Pro_Number(String bOL_Pro_Number) {
		BOL_Pro_Number = bOL_Pro_Number;
	}

	public Date getShip_Line_Ship_Date() {
		return ship_Line_Ship_Date;
	}

	public void setShip_Line_Ship_Date(Date ship_Line_Ship_Date) {
		this.ship_Line_Ship_Date = ship_Line_Ship_Date;
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

}
