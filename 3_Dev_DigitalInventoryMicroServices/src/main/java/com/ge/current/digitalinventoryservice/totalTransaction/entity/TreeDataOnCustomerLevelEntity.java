/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalTransaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vs825995
 *
 */
@Entity
@Table(name = "tree_data_table_for_customer_level_view", schema = "analytics")
public class TreeDataOnCustomerLevelEntity {

	@Id
	private int id;

	private String customer;

	private String material;

	@Column(name = "material_description")
	private String materialDescription;

	@Column(name = "current_qty")
	private Double currentQty;

	@Column(name = "plant")
	private String source;

	@Column(name = "unit_of_measure")
	private String uom;
	
	@Column(name = "intransit_qty")
	private Double intransitQty;

	//
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	/**
	 * @return the currentQty
	 */
	public Double getCurrentQty() {
		return currentQty;
	}

	/**
	 * @param currentQty the currentQty to set
	 */
	public void setCurrentQty(Double currentQty) {
		this.currentQty = currentQty;
	}

	/**
	 * @return the intransitQty
	 */
	public Double getIntransitQty() {
		return intransitQty;
	}

	/**
	 * @param intransitQty the intransitQty to set
	 */
	public void setIntransitQty(Double intransitQty) {
		this.intransitQty = intransitQty;
	}

}
