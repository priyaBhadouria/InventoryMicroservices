package com.ge.current.div.dataFeed.service;

import java.util.List;

import org.json.JSONArray;

import com.ge.current.div.dataFeed.dto.CoreShipmentJsonDTO;
import com.ge.current.div.dataFeed.dto.CustomerConsumptionJsonDTO;
import com.ge.current.div.dataFeed.dto.GEShipmentJsonDTO;
import com.ge.current.div.dataFeed.dto.PurchasingOrderDTO;
import com.ge.current.div.dataFeed.dto.SAPDemandDTO;
import com.ge.current.div.dataFeed.dto.SAPUploadDTO;
import com.ge.current.div.dataFeed.entity.CoreShipmentEntity;
import com.ge.current.div.dataFeed.entity.CustomerConsumptionEntity;
import com.ge.current.div.dataFeed.entity.GEShipmentEntity;
import com.ge.current.div.dataFeed.entity.PurchasingOrderEntity;
import com.ge.current.div.dataFeed.entity.CustomerDemandEntity;
import com.ge.current.div.dataFeed.entity.SAPLoggerEntity;

public interface IDataFeedService {
public List<GEShipmentEntity> postGELEDShipment(List<GEShipmentJsonDTO> jsonDTOs);

	
	public List<CoreShipmentEntity> postGECoreShipment(List<CoreShipmentJsonDTO> jsonDTOs);
	
	public List<CustomerConsumptionEntity> postCustomerConsumption(List<CustomerConsumptionJsonDTO> consumptionJsonDTOs);
	
	public List<PurchasingOrderEntity> postPurchasingOrder(List<PurchasingOrderDTO> purchasingOrderDTOs);
	
	public List<CustomerDemandEntity> postCustomerDemand(List<SAPDemandDTO> sapDemandDTOs);

	public List<SAPUploadDTO> getSAPUploadDetails();

	public List<SAPLoggerEntity> getSAPLoggerData(Long sapUploadId);

}

