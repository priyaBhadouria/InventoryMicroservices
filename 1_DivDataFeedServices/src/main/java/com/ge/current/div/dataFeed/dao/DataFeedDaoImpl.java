package com.ge.current.div.dataFeed.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
import com.ge.current.div.dataFeed.entity.SAPUploadEntity;
import com.ge.current.div.dataFeed.repository.ICoreShipmentRepository;
import com.ge.current.div.dataFeed.repository.ICustomerConsumptionRepository;
import com.ge.current.div.dataFeed.repository.IGEShipmentRepository;
import com.ge.current.div.dataFeed.repository.IPurchasingOrderRepository;
import com.ge.current.div.dataFeed.repository.ICustomerDemandRepository;
import com.ge.current.div.dataFeed.repository.ISAPLoggerRepository;
import com.ge.current.div.dataFeed.repository.ISAPUploadIdSeq;
import com.ge.current.div.dataFeed.repository.ISAPUploadRepository;

@Repository
public class DataFeedDaoImpl implements IDataFeedDao {

	@Autowired
	private IGEShipmentRepository geShipmentRepository;
	@Autowired
	private ICoreShipmentRepository coreShipmentRepository;

	@Autowired
	private ICustomerConsumptionRepository consumptionJsonRepository;
	@Autowired
	private ISAPUploadIdSeq sapUploadSeq;
	@Autowired
	private ISAPUploadRepository sapUploadRepo;
	@Autowired
	private IPurchasingOrderRepository purchasingOrderRepo;
	@Autowired
	private ICustomerDemandRepository sapDemandRepo;
	@Autowired
	private ISAPLoggerRepository loggerRepository;

	//DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public List<GEShipmentEntity> postGELEDShipment(
			List<GEShipmentJsonDTO> geShipmentDTOs) {
		List<GEShipmentEntity> geShipmentEntityList = new ArrayList<GEShipmentEntity>();
		java.util.Date today = new java.util.Date();
		int totRecords = geShipmentDTOs.size();
		int recordsPassed = 0;
		int recordsFailed = 0;
		StringBuffer log = new StringBuffer();
		Long sequenceNo;
		sequenceNo = sapUploadSeq.getSeq() + 1;
		int recordNumber = 0;
		SAPLoggerEntity loggerEntity = new SAPLoggerEntity();
		SAPUploadEntity sapUploadEntity = new SAPUploadEntity();
		for (GEShipmentJsonDTO geShipmentDTO : geShipmentDTOs) {

			System.out.println("Total Rows" + totRecords);
			StringBuffer logRecord = new StringBuffer();
			recordNumber = recordNumber + 1;

			GEShipmentEntity geShipmentEntity = new GEShipmentEntity();

			Long sto = geShipmentDTO.getOrder_Number();
			if (sto.toString() != "" && sto != null) {
				geShipmentEntity.setOrder_Number(sto);
			} else {
				logRecord = logRecord.append("sto,");
			}
			Double stoItem = geShipmentDTO.getOrder_Item();
			if (stoItem.toString() != "" && stoItem != null) {
				geShipmentEntity.setOrder_Item(stoItem);
			} else {
				logRecord = logRecord.append("stoItem,");
			}
			String material = geShipmentDTO.getMaterial_Number();
			if (material != "" && material != null) {
				geShipmentEntity.setMaterial_Number(material);
			} else {
				logRecord = logRecord.append("material,");
			}
			String materialDesc = geShipmentDTO.getMaterial_Description();
			if (materialDesc != "" && materialDesc != null) {
				geShipmentEntity.setMaterial_Description(materialDesc);
			} else {
				logRecord = logRecord.append("material Description,");
			}
			String plant = geShipmentDTO.getPlant();
			if (plant != "" && plant != null) {
				geShipmentEntity.setPlant(plant);
			} else {
				logRecord = logRecord.append("plant,");
			}
			String storageLoc = geShipmentDTO.getRec_Storage_Location();
			if (storageLoc != "" && storageLoc != null) {
				geShipmentEntity.setRec_Storage_Location(storageLoc);
			} else {
				logRecord = logRecord.append("storage Location,");
			}
			Double quantity = geShipmentDTO.getDel_Quantity();
			if (quantity.toString() != "" && quantity != null) {
				geShipmentEntity.setDel_Quantity(quantity);
			} else {
				logRecord = logRecord.append("quantity,");
			}
			String uom = geShipmentDTO.getUom();
			if (uom != "" && uom != null) {
				geShipmentEntity.setUom(uom);
			} else {
				logRecord = logRecord.append("uom,");
			}
			Long delivery_number = geShipmentDTO.getDelivery_Number();
			if (delivery_number.toString() != "" && delivery_number != null) {
				geShipmentEntity.setDelivery_Number(delivery_number);
			} else {
				logRecord = logRecord.append("delivery Number,");
			}
			if (logRecord.length() != 0) {
				log.append("(");
				log = log.append(recordNumber);
				log.append(")");
				log = log.append(logRecord);
			}
			geShipmentEntity.setCarrier_Name(geShipmentDTO.getCarrier_Name());
			geShipmentEntity.setDelivery_Item(geShipmentDTO.getDelivery_Item());
			geShipmentEntity.setPro_Number(geShipmentDTO.getPro_Number());
			geShipmentEntity.setRec_Plant(geShipmentDTO.getRec_Plant());
			geShipmentEntity.setShipment_Number(geShipmentDTO
					.getShipment_Number());
			geShipmentEntity.setShip_To_City(geShipmentDTO.getShip_To_City());
			geShipmentEntity.setShip_To_Country(geShipmentDTO
					.getShip_To_Country());
			geShipmentEntity.setShip_To_Name(geShipmentDTO.getShip_To_Name());
			geShipmentEntity.setShip_To_Postal_Code(geShipmentDTO
					.getShip_To_Postal_Code());
			geShipmentEntity.setShip_To_Street(geShipmentDTO
					.getShip_To_Street());
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date deliveryCrDate;
			Date shipmentCrDate;
			try {
				deliveryCrDate = df.parse(geShipmentDTO.getDelivery_Cr_Date());
				shipmentCrDate = df.parse(geShipmentDTO.getShipment_Crd());
				geShipmentEntity.setDelivery_Cr_Date(deliveryCrDate);
				geShipmentEntity.setShipment_Crd(shipmentCrDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			geShipmentEntity.setCreationDate(today);
			geShipmentEntity.setIsProcessed("N");
			geShipmentEntity.setFile_seq_number(sequenceNo);
			if (logRecord.length() == 0) {
				try {
					if (geShipmentRepository.save(geShipmentEntity) != null) {
						geShipmentEntityList.add(geShipmentEntity);
						recordsPassed = recordsPassed + 1;
					}
				} catch (Exception e) {
					log.append("Exception Occured...");
					log = log.append(e.getMessage());
					System.out.println("ERROR***" + log);
				}

			}

		}
		recordsFailed = totRecords - recordsPassed;
		sapUploadEntity.setIsProcessed("N");
		sapUploadEntity.setRecordsFailed(recordsFailed);
		sapUploadEntity.setRecordsPassed(recordsPassed);
		sapUploadEntity.setTotRecords(totRecords);
		sapUploadEntity.setServiceName("postGELEDShipment");
		sapUploadEntity.setCreateDtm(today);
		sapUploadRepo.save(sapUploadEntity);
		if (log.length() != 0) {
			log.append(" cannot be empty");
			loggerEntity.setErrorLog(log.toString());
			loggerEntity.setCreateDtm(today);
			loggerEntity.setSapUploadId(sapUploadSeq.getSeq());
			System.out.println("Error  " + log);
			System.out.println("Error Seq " + sapUploadSeq.getSeq());
			loggerRepository.save(loggerEntity);
		}
		System.out.println("Row Passed" + recordsPassed);
		System.out.println("Row Failed" + (totRecords - recordsPassed));
		return geShipmentEntityList;
	}

	@Override
	public List<CoreShipmentEntity> postGECoreShipment(
			List<CoreShipmentJsonDTO> coreShipmentDTOs) {
		List<CoreShipmentEntity> coreShipmentEntityList = new ArrayList<CoreShipmentEntity>();
		int totRecords = coreShipmentDTOs.size();
		int recordsPassed = 0;
		int recordsFailed = 0;
		Long sequenceNo = sapUploadSeq.getSeq() + 1;
		java.util.Date today = new java.util.Date();
		SAPUploadEntity sapUploadEntity = new SAPUploadEntity();
		String log = "";
		SAPLoggerEntity loggerEntity = new SAPLoggerEntity();
		for (CoreShipmentJsonDTO coreShipmentDTO : coreShipmentDTOs) {
			CoreShipmentEntity coreShipmentEntity = new CoreShipmentEntity();
			coreShipmentEntity.setBOL(coreShipmentDTO.getBOL());
			coreShipmentEntity.setBOL_Pro_Number(coreShipmentDTO
					.getBOL_Pro_Number());
			coreShipmentEntity.setCarrier_SCAC_Code(coreShipmentDTO
					.getCarrier_SCAC_Code());
			coreShipmentEntity.setComponent_Number(coreShipmentDTO
					.getComponent_Number());
			coreShipmentEntity
					.setCustomer_Purchase_Order_Number(coreShipmentDTO
							.getCustomer_Purchase_Order_Number());
			coreShipmentEntity.setOrder_Item_Quantity_UnExp(coreShipmentDTO
					.getOrder_Item_Quantity_UnExp());
			coreShipmentEntity.setOrder_Item_Shipped_Qty_UnExp(coreShipmentDTO
					.getOrder_Item_Shipped_Qty_UnExp());
			coreShipmentEntity.setOrder_Number(coreShipmentDTO
					.getOrder_Number());
			coreShipmentEntity.setPick_Ticket_Number(coreShipmentDTO
					.getPick_Ticket_Number());
			coreShipmentEntity.setProduct_Code(coreShipmentDTO
					.getProduct_Code());
			coreShipmentEntity.setProduct_Description(coreShipmentDTO
					.getProduct_Description());
			coreShipmentEntity.setShip_Line_Status_Code(coreShipmentDTO
					.getShip_Line_Status_Code());
			coreShipmentEntity.setShip_To_Name(coreShipmentDTO
					.getShip_To_Name());
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date orderEntryDate;
			Date orderSummaryDelReqDate;
			Date shipLineShipDate;
			try {
				orderEntryDate = df
						.parse(coreShipmentDTO.getOrder_Entry_Date());
				orderSummaryDelReqDate = df.parse(coreShipmentDTO
						.getOrder_Summary_Del_Req_Date());
				shipLineShipDate = df.parse(coreShipmentDTO
						.getShip_Line_Ship_Date());
				coreShipmentEntity.setOrder_Entry_Date(orderEntryDate);
				coreShipmentEntity
						.setOrder_Summary_Del_Req_Date(orderSummaryDelReqDate);
				coreShipmentEntity.setShip_Line_Ship_Date(shipLineShipDate);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			coreShipmentEntity.setCreationDate(today);
			coreShipmentEntity.setIsProcessed("N");
			coreShipmentEntity.setFile_seq_number(sequenceNo);
			try {
				if (coreShipmentRepository.save(coreShipmentEntity) != null) {
					coreShipmentEntityList.add(coreShipmentEntity);
					recordsPassed = recordsPassed + 1;

				}
			} catch (Exception e) {
				log = "Exception Occured...";
				log = log + e.getMessage();
				// System.out.println("ERROR***"+log);
			}
			recordsFailed = totRecords - recordsPassed;
			sapUploadEntity.setIsProcessed("N");
			sapUploadEntity.setRecordsFailed(recordsFailed);
			sapUploadEntity.setRecordsPassed(recordsPassed);
			sapUploadEntity.setTotRecords(totRecords);
			sapUploadEntity.setServiceName("postGECoreShipment");
			sapUploadEntity.setCreateDtm(today);
			sapUploadRepo.save(sapUploadEntity);

			if (log.length() != 0) {
				loggerEntity.setErrorLog(log);
				loggerEntity.setCreateDtm(today);
				loggerEntity.setSapUploadId(sapUploadSeq.getSeq());
				System.out.println("Error  " + log);
				System.out.println("Error Seq " + sapUploadSeq.getSeq());
				loggerRepository.save(loggerEntity);
			}
		}
		return coreShipmentEntityList;
	}

	@Override
	public List<CustomerConsumptionEntity> postCustomerConsumption(
			List<CustomerConsumptionJsonDTO> consumptionJsonDTOs) {
		List<CustomerConsumptionEntity> customerConsumptionEntityList = new ArrayList<CustomerConsumptionEntity>();
		int totRecords = consumptionJsonDTOs.size();
		int recordsPassed = 0;
		int recordsFailed = 0;
		int recordNumber = 0;
		Long sequenceNo = sapUploadSeq.getSeq() + 1;
		String error = "";
		SAPUploadEntity sapUploadEntity = new SAPUploadEntity();
		StringBuffer log = new StringBuffer();
		log.append("Foreign key constraint violated by ");
		SAPLoggerEntity loggerEntity = new SAPLoggerEntity();
		for (CustomerConsumptionJsonDTO consumptionJsonDTO : consumptionJsonDTOs) {
			recordNumber = recordNumber + 1;
			CustomerConsumptionEntity consumptionJsonEntity = new CustomerConsumptionEntity();

			consumptionJsonEntity.setAmount_In_LC(consumptionJsonDTO
					.getAmount_In_LC());
			consumptionJsonEntity.setBase_Unit_Of_Measure(consumptionJsonDTO
					.getBase_Unit_Of_Measure());
			consumptionJsonEntity.setCurrency(consumptionJsonDTO.getCurrency());
			consumptionJsonEntity.setDelivery(consumptionJsonDTO.getDelivery());
			consumptionJsonEntity.setMaterial(consumptionJsonDTO.getMaterial());
			consumptionJsonEntity.setMaterial_Document(consumptionJsonDTO
					.getMaterial_Document());
			consumptionJsonEntity.setMovement_Type(consumptionJsonDTO
					.getMovement_Type());
			consumptionJsonEntity.setNetwork(consumptionJsonDTO.getNetwork());
			consumptionJsonEntity.setPlant(consumptionJsonDTO.getPlant());
			consumptionJsonEntity.setQuantity(consumptionJsonDTO.getQuantity());
			consumptionJsonEntity.setReference(consumptionJsonDTO
					.getReference());
			consumptionJsonEntity.setReservation(consumptionJsonDTO
					.getReservation());
			consumptionJsonEntity.setStorage_Location(consumptionJsonDTO
					.getStorage_Location());
			consumptionJsonEntity.setWbs_Code(consumptionJsonDTO.getWbs_Code());
			consumptionJsonEntity.setWbs_Discription(consumptionJsonDTO
					.getWbs_Discription());
			consumptionJsonEntity.setWbs_Element(consumptionJsonDTO
					.getWbs_Element());
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date postingDate;
			try {

				postingDate = df.parse(consumptionJsonDTO.getPosting_Date());

				consumptionJsonEntity.setPosting_Date(postingDate);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			java.util.Date today = new java.util.Date();
			consumptionJsonEntity.setCreationDate(today);
			consumptionJsonEntity.setIsProcessed("N");
			consumptionJsonEntity.setFile_seq_number(sequenceNo);
			// new
			try {
				consumptionJsonRepository.save(consumptionJsonEntity); // might
																		// throw
																		// exception
				customerConsumptionEntityList.add(consumptionJsonEntity);
				recordsPassed = recordsPassed + 1;
			} catch (Exception e) {
				error = e.getMessage();
				log.append("(" + recordNumber + ")");
				if (error.contains("storage")) {
					log.append(" storage_location,");
				}
				if (error.contains("plant")) {
					log.append(" plant,");
				}
				if (error.contains("unit_of_measure")) {
					log.append(" unit_of_measure,");
				}
				if (error.contains("material")) {
					log.append(" material,");
				}
				// log
				// ="material,plant,storage_location,unit_of_measure may violate foreign key constraint !!!";
				System.out.println("ERROR***" + log);
			}
			//

			recordsFailed = totRecords - recordsPassed;
			sapUploadEntity.setIsProcessed("Y");
			sapUploadEntity.setRecordsFailed(recordsFailed);
			sapUploadEntity.setRecordsPassed(recordsPassed);
			sapUploadEntity.setTotRecords(totRecords);
			sapUploadEntity.setServiceName("postCustomerConsumption");
			sapUploadEntity.setCreateDtm(today);
			sapUploadRepo.save(sapUploadEntity);

			if (error.length() != 0) {
				loggerEntity.setErrorLog(log.toString());
				loggerEntity.setCreateDtm(today);
				loggerEntity.setSapUploadId(sapUploadSeq.getSeq());
				System.out.println("Error  " + log);
				System.out.println("Error Seq " + sapUploadSeq.getSeq());
				loggerRepository.save(loggerEntity);
			}
		}
		return customerConsumptionEntityList;
	}

	@Override
	public List<PurchasingOrderEntity> postPurchasingOrder(
			List<PurchasingOrderDTO> purchasingOrderDTOs) {
		List<PurchasingOrderEntity> purchasingOrderEntityList = new ArrayList<PurchasingOrderEntity>();
		int totRecords = purchasingOrderDTOs.size();
		int recordsPassed = 0;
		int recordsFailed = 0;

		Long sequenceNo = sapUploadSeq.getSeq() + 1;
		java.util.Date today = new java.util.Date();
		SAPUploadEntity sapUploadEntity = new SAPUploadEntity();
		String log = "";
		SAPLoggerEntity loggerEntity = new SAPLoggerEntity();
		for (PurchasingOrderDTO purchasingOrderDTO : purchasingOrderDTOs) {
System.out.println("DAO impl"+purchasingOrderDTO.toString());
			PurchasingOrderEntity purchasingOrderEntity = new PurchasingOrderEntity();

			purchasingOrderEntity.setCurrency(purchasingOrderDTO.getCurrency());
			purchasingOrderEntity.setIncomplete(purchasingOrderDTO
					.getIncomplete());
			purchasingOrderEntity.setItem(purchasingOrderDTO.getItem());
			purchasingOrderEntity.setMaterial(purchasingOrderDTO.getMaterial());
			purchasingOrderEntity.setMaterial_Group(purchasingOrderDTO
					.getMaterial_Group());
			purchasingOrderEntity.setNet_Order_Value(purchasingOrderDTO
					.getNet_Order_Value());
			purchasingOrderEntity.setOrder_Price_Unit(purchasingOrderDTO
					.getOrder_Price_Unit());
			purchasingOrderEntity.setOrder_Qty(purchasingOrderDTO
					.getOrder_Qty());
			purchasingOrderEntity.setOrder_Unit(purchasingOrderDTO
					.getOrder_Unit());
			purchasingOrderEntity.setPlant(purchasingOrderDTO.getPlant());
			purchasingOrderEntity.setPurch_Group(purchasingOrderDTO
					.getPurch_Group());
			purchasingOrderEntity.setPurch_Organization(purchasingOrderDTO
					.getPurch_Organization());
			purchasingOrderEntity.setPurchasing_Doc_Type(purchasingOrderDTO
					.getPurchasing_Doc_Type());
			purchasingOrderEntity.setPurchasing_Document(purchasingOrderDTO
					.getPurchasing_Document());
			purchasingOrderEntity.setShort_Text(purchasingOrderDTO
					.getShort_Text());
			purchasingOrderEntity.setStorage_Location(purchasingOrderDTO
					.getStorage_Location());
			purchasingOrderEntity.setSupplying_Plant(purchasingOrderDTO
					.getSupplying_Plant());
			purchasingOrderEntity.setVendor(purchasingOrderDTO.getVendor());
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date documentDate;
			try {
				documentDate = df.parse(purchasingOrderDTO.getDocument_Date());
				purchasingOrderEntity.setDocument_Date(documentDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			purchasingOrderEntity.setCreationDate(today);
			purchasingOrderEntity.setIsProcessed("Y");
			purchasingOrderEntity.setFile_seq_number(sequenceNo);
			try {
				if (purchasingOrderRepo.save(purchasingOrderEntity) != null) {
					purchasingOrderEntityList.add(purchasingOrderEntity);
					recordsPassed = recordsPassed + 1;
				}
			} catch (Exception e) {
				log = "Exception Occured...";
				log = log + e.getMessage();
				 System.out.println("ERROR***"+log);
			}
			recordsFailed = totRecords - recordsPassed;
			sapUploadEntity.setIsProcessed("N");
			sapUploadEntity.setRecordsFailed(recordsFailed);
			sapUploadEntity.setRecordsPassed(recordsPassed);
			sapUploadEntity.setTotRecords(totRecords);
			sapUploadEntity.setServiceName("postPurchasingOrder");
			sapUploadEntity.setCreateDtm(today);
			sapUploadRepo.save(sapUploadEntity);

			if (log.length() != 0) {
				loggerEntity.setErrorLog(log);
				loggerEntity.setCreateDtm(today);
				loggerEntity.setSapUploadId(sapUploadSeq.getSeq());

				System.out.println("Error  " + log);
				System.out.println("Error Seq " + sapUploadSeq.getSeq());
				loggerRepository.save(loggerEntity);
			}
		}
		return purchasingOrderEntityList;
	}

	@Override
	public List<CustomerDemandEntity> postCustomerDemand(
			List<SAPDemandDTO> sapDemandDTOs) {
		List<CustomerDemandEntity> customerDemandEntityList = new ArrayList<CustomerDemandEntity>();
		int totRecords = sapDemandDTOs.size();
		int recordsPassed = 0;
		int recordsFailed = 0;
		java.util.Date today = new java.util.Date();

		Long sequenceNo = sapUploadSeq.getSeq() + 1;
		SAPUploadEntity sapUploadEntity = new SAPUploadEntity();
		String log = "";
		SAPLoggerEntity loggerEntity = new SAPLoggerEntity();
		for (SAPDemandDTO sapDemandDTO : sapDemandDTOs) {

			CustomerDemandEntity sapDemandEntity = new CustomerDemandEntity();

			sapDemandEntity.setActivity(sapDemandDTO.getActivity());
			sapDemandEntity.setDeletion_Indicator(sapDemandDTO
					.getDeletion_Indicator());
			sapDemandEntity.setMaterial(sapDemandDTO.getMaterial());
			sapDemandEntity.setMaterial_Text(sapDemandDTO.getMaterial_Text());
			sapDemandEntity.setNetwork(sapDemandDTO.getNetwork());
			sapDemandEntity.setPlant(sapDemandDTO.getPlant());
			sapDemandEntity.setProject_Definition(sapDemandDTO
					.getProject_Definition());
			sapDemandEntity.setPurchase_Ord_Exists(sapDemandDTO
					.getPurchase_Ord_Exists());
			sapDemandEntity.setPurchase_Requisition(sapDemandDTO
					.getPurchase_Requisition());
			sapDemandEntity.setQuantity(sapDemandDTO.getQuantity());
			sapDemandEntity.setRequisition_Item(sapDemandDTO
					.getRequisition_Item());
			sapDemandEntity.setReservation(sapDemandDTO.getReservation());
			sapDemandEntity.setReservation_Purc_Req(sapDemandDTO
					.getReservation_Purc_Req());
			sapDemandEntity.setStorage_Location(sapDemandDTO
					.getStorage_Location());
			sapDemandEntity.setUnit_Of_Measure(sapDemandDTO
					.getUnit_Of_Measure());
			sapDemandEntity.setWbs_Element(sapDemandDTO.getWbs_Element());
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date requirementDate;
			try {
				requirementDate = df.parse(sapDemandDTO.getRequirement_Date());
				sapDemandEntity.setRequirement_Date(requirementDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			sapDemandEntity.setCreationDate(today);
			sapDemandEntity.setIsProcessed("N");
			sapDemandEntity.setFile_seq_number(sequenceNo);
			try {
				if (sapDemandRepo.save(sapDemandEntity) != null) {
					customerDemandEntityList.add(sapDemandEntity);
					recordsPassed = recordsPassed+1;
				}
			} catch (Exception e) {
				log = "Exception Occured...";
				log = log + e.getMessage();
				// System.out.println("ERROR***"+log);
			}
			recordsFailed = totRecords - recordsPassed;
			sapUploadEntity.setIsProcessed("N");
			sapUploadEntity.setRecordsFailed(recordsFailed);
			sapUploadEntity.setRecordsPassed(recordsPassed);
			sapUploadEntity.setTotRecords(totRecords);
			sapUploadEntity.setServiceName("postCustomerDemand");
			sapUploadEntity.setCreateDtm(today);
			sapUploadRepo.save(sapUploadEntity);

			if (log.length() != 0) {
				loggerEntity.setErrorLog(log);
				loggerEntity.setCreateDtm(today);
				loggerEntity.setSapUploadId(sapUploadSeq.getSeq());

				System.out.println("Error  " + log);
				System.out.println("Error Seq " + sapUploadSeq.getSeq());
				loggerRepository.save(loggerEntity);
			}
		}
		return customerDemandEntityList;

	}

	@Override
	public List<SAPUploadDTO> getSAPUploadDetails() {

		List<SAPUploadEntity> sapUploadEntities = new ArrayList<SAPUploadEntity>();
		sapUploadEntities = (List<SAPUploadEntity>) this.sapUploadRepo
				.findAllDescOrder();

		List<SAPUploadDTO> sapUploadDTOs = new ArrayList<SAPUploadDTO>();

		for (SAPUploadEntity sapUploadEntity : sapUploadEntities) {

			SAPUploadDTO sapUploadDTO = new SAPUploadDTO();
			sapUploadDTO.setId(sapUploadEntity.getId());
			sapUploadDTO.setIsProcessed(sapUploadEntity.getIsProcessed());
			sapUploadDTO.setRecordsFailed(sapUploadEntity.getRecordsFailed());
			sapUploadDTO.setRecordsPassed(sapUploadEntity.getRecordsPassed());
			sapUploadDTO.setServiceName(sapUploadEntity.getServiceName());
			sapUploadDTO.setTotRecords(sapUploadEntity.getTotRecords());
			sapUploadDTO.setCreateDtm(convertDatetoString(sapUploadEntity
					.getCreateDtm()));
			sapUploadDTOs.add(sapUploadDTO);
		}

		return sapUploadDTOs;
	}

	@Override
	public List<SAPLoggerEntity> getSAPLoggerData(Long sapUploadId) {
		List<SAPLoggerEntity> loggerEntities = new ArrayList<SAPLoggerEntity>();
		loggerEntities = this.loggerRepository.getSAPLoggerData(sapUploadId);
		return loggerEntities;
	}

	public String convertDatetoString(Date dateInput) {
		String stringDate = null;
		if (dateInput != null) {
			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

			stringDate = dateFormat.format(dateInput);
		}
		return stringDate;
	}

	
}
