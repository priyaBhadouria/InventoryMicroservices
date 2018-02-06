/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalTransaction.dto;

/**
 * @author vs825995
 *
 */
public class TreeDataOnCustomerLevelDTO {

	private String customer;

	private String material;

	private String materialDescription;

	private Double currentQty;
	
	private Double intransitQty;

	private String isFSG;

	private String source;

	private String uom;

	public String getCustomer() {
		return customer;
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

	public String getIsFSG() {
		return isFSG;
	}

	public void setIsFSG(String isFSG) {
		this.isFSG = isFSG;
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

}
