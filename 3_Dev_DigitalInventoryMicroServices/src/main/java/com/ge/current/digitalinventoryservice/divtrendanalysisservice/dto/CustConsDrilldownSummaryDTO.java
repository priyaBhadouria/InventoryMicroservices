package com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto;

import java.io.Serializable;

public class CustConsDrilldownSummaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String material;

	private String materialDesc;

	private String custId;

	private String custDesc;

	private String installationLoc;

	private String receivedLoc;

	private String date;

	private Double custConsumptions = 0d;

	private Double custReturns = 0d;

	/**
	 * 
	 * @return the material
	 */

	public String getMaterial() {
		return material;
	}

	/**
	 * 
	 * @param material
	 */

	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * 
	 * @return the materialDesc
	 */

	public String getMaterialDesc() {
		return materialDesc;
	}

	/**
	 * 
	 * @param materialDesc
	 */
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	/**
	 * 
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * 
	 * @param custId
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * 
	 * @return the custDesc
	 */
	public String getCustDesc() {
		return custDesc;
	}

	/**
	 * 
	 * @param custDesc
	 */
	public void setCustDesc(String custDesc) {
		this.custDesc = custDesc;
	}

	/**
	 * 
	 * @return the installationLoc
	 */
	public String getInstallationLoc() {
		return installationLoc;
	}

	/**
	 * 
	 * @param installationLoc
	 */
	public void setInstallationLoc(String installationLoc) {
		this.installationLoc = installationLoc;
	}

	/**
	 * 
	 * @return the receivedLoc
	 */
	public String getReceivedLoc() {
		return receivedLoc;
	}

	/**
	 * 
	 * @param receivedLoc
	 */
	public void setReceivedLoc(String receivedLoc) {
		this.receivedLoc = receivedLoc;
	}

	/**
	 * 
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return the custConsumptions
	 */
	public Double getCustConsumptions() {
		return custConsumptions;
	}

	/**
	 * 
	 * @param custConsumptions
	 */
	public void setCustConsumptions(Double custConsumptions) {
		this.custConsumptions = custConsumptions;
	}

	/**
	 * 
	 * @return the custReturns
	 */
	public Double getCustReturns() {
		return custReturns;
	}

	/**
	 * 
	 * @param custReturns
	 */
	public void setCustReturns(Double custReturns) {
		this.custReturns = custReturns;
	}

}
