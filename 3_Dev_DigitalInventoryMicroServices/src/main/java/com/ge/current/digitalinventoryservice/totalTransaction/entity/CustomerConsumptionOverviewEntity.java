/**
 * 
 */
package com.ge.current.digitalinventoryservice.totalTransaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vs825995
 *
 */

@Entity
@Table(name = "customer_consumption_overview_view", schema = "analytics")
public class CustomerConsumptionOverviewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "total_sites_installed")
	private int totalSitesInstalled;

	@Column(name = "assets_installed")
	private double assetsInstalled;

	@Column(name = "assets_returned")
	private double assetsReturned;
	
	@Column(name = "totalvariance")
	private Integer totalVariance;


	public int getTotalSitesInstalled() {
		return totalSitesInstalled;
	}

	public void setTotalSitesInstalled(int totalSitesInstalled) {
		this.totalSitesInstalled = totalSitesInstalled;
	}

	public double getAssetsInstalled() {
		return assetsInstalled;
	}

	public void setAssetsInstalled(double assetsInstalled) {
		this.assetsInstalled = assetsInstalled;
	}

	public double getAssetsReturned() {
		return assetsReturned;
	}

	public void setAssetsReturned(double assetsReturned) {
		this.assetsReturned = assetsReturned;
	}

	public Integer getTotalVariance() {
		return totalVariance;
	}

	public void setTotalVariance(Integer totalVariance) {
		this.totalVariance = totalVariance;
	}

}
