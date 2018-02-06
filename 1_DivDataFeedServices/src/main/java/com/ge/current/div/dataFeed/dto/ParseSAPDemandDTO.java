package com.ge.current.div.dataFeed.dto;

import java.util.List;

public class ParseSAPDemandDTO {

	public List<SAPDemandDTO> Recordset;

	public List<SAPDemandDTO> getRecordset() {
		return Recordset;
	}

	public void setRecordset(List<SAPDemandDTO> recordset) {
		Recordset = recordset;
	}
	
	
}
