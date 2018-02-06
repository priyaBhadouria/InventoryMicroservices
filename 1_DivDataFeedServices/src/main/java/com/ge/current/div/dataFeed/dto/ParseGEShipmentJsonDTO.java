package com.ge.current.div.dataFeed.dto;

import java.io.Serializable;
import java.util.List;




public class ParseGEShipmentJsonDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<GEShipmentJsonDTO> Recordset;

	public List<GEShipmentJsonDTO> getRecordset() {
		return Recordset;
	}

	public void setRecordset(List<GEShipmentJsonDTO> recordset) {
		Recordset = recordset;
	}
	
	
}
