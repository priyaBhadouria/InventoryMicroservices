package com.ge.current.div.dataFeed.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.current.div.dataFeed.dao.IDataFeedDao;
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


@Service
public class DataFeedServiceImpl implements IDataFeedService {


	@Autowired
	IDataFeedDao dataFeedDao;

	@Override
	public List<GEShipmentEntity>postGELEDShipment(List<GEShipmentJsonDTO> jsonDTOs) {
		return dataFeedDao.postGELEDShipment(jsonDTOs);
	}
	
	

	@Override
	public List<CoreShipmentEntity> postGECoreShipment(List<CoreShipmentJsonDTO> coreShipmentDTOs) {
		return dataFeedDao.postGECoreShipment(coreShipmentDTOs);
	}

	@Override
	public List<CustomerConsumptionEntity> postCustomerConsumption(
			List<CustomerConsumptionJsonDTO> consumptionJsonDTOs) {
		return dataFeedDao.postCustomerConsumption(consumptionJsonDTOs);
	}

	@Override
	public List<PurchasingOrderEntity> postPurchasingOrder(
			List<PurchasingOrderDTO> purchasingOrderDTOs) {
		return dataFeedDao.postPurchasingOrder(purchasingOrderDTOs);
	}

	@Override
	public List<CustomerDemandEntity> postCustomerDemand(List<SAPDemandDTO> sapDemandDTOs) {
		return dataFeedDao.postCustomerDemand(sapDemandDTOs);
	}

	@Override
	public List<SAPUploadDTO> getSAPUploadDetails() {
		return dataFeedDao.getSAPUploadDetails();
	}

	@Override
	public List<SAPLoggerEntity> getSAPLoggerData(Long sapUploadId) {
		return dataFeedDao.getSAPLoggerData(sapUploadId);
	}


	
}
