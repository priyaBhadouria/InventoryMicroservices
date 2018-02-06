/**
 * 
 */
package com.ge.current.digitalinventoryservice.fsgoverview.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ncheredd
 *
 */
@Entity
@Table(name = "fsg_overview_dollar_value_summary_view", schema = "validstage")
public class FSGOverviewDollarValueSummaryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transit_to_fsg")
	private Double transitToFSGCost;

	@Column(name = "at_fsg")
	private Double atFSGCost;

	@Column(name = "fsg_demand")
	private Double fsgDemandCost;

	@Column(name = "fsg_shortage")
	private Double fsgShortageCost;

	/**
	 * @return the transitToFSGCost
	 */
	public Double getTransitToFSGCost() {
		return transitToFSGCost;
	}

	/**
	 * @param transitToFSGCost
	 *            the transitToFSGCost to set
	 */
	public void setTransitToFSGCost(Double transitToFSGCost) {
		this.transitToFSGCost = transitToFSGCost;
	}

	/**
	 * @return the atFSGCost
	 */
	public Double getAtFSGCost() {
		return atFSGCost;
	}

	/**
	 * @param atFSGCost
	 *            the atFSGCost to set
	 */
	public void setAtFSGCost(Double atFSGCost) {
		this.atFSGCost = atFSGCost;
	}

	/**
	 * @return the fsgDemandCost
	 */
	public Double getFsgDemandCost() {
		return fsgDemandCost;
	}

	/**
	 * @param fsgDemandCost
	 *            the fsgDemandCost to set
	 */
	public void setFsgDemandCost(Double fsgDemandCost) {
		this.fsgDemandCost = fsgDemandCost;
	}

	/**
	 * @return the fsgShortageCost
	 */
	public Double getFsgShortageCost() {
		return fsgShortageCost;
	}

	/**
	 * @param fsgShortageCost
	 *            the fsgShortageCost to set
	 */
	public void setFsgShortageCost(Double fsgShortageCost) {
		this.fsgShortageCost = fsgShortageCost;
	}
}
