package com.ge.current.div.dataFeed.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.current.div.dataFeed.OauthVerification;
import com.ge.current.div.dataFeed.dto.ParseCustomerConsumptionJsonDTO;
import com.ge.current.div.dataFeed.dto.ParseGEShipmentJsonDTO;
import com.ge.current.div.dataFeed.dto.ParsePurchasingOrderDTO;
import com.ge.current.div.dataFeed.dto.ParseSAPDemandDTO;
import com.ge.current.div.dataFeed.dto.SAPUploadDTO;
import com.ge.current.div.dataFeed.entity.CustomerConsumptionEntity;
import com.ge.current.div.dataFeed.entity.GEShipmentEntity;
import com.ge.current.div.dataFeed.entity.PurchasingOrderEntity;
import com.ge.current.div.dataFeed.entity.CustomerDemandEntity;
import com.ge.current.div.dataFeed.entity.SAPLoggerEntity;
import com.ge.current.div.dataFeed.service.IDataFeedService;


@Configuration
@RestController
public class PostJsonObjectServices {

	@Autowired
	OauthVerification oAuth;
	@Autowired
	private IDataFeedService dataFeedService;
	
	private Logger log = LoggerFactory.getLogger(PostJsonObjectServices.class);
	
	@RequestMapping(value = "/divDataFeed", method = RequestMethod.GET)
	public String getMessage(HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		return "Welcome To Digital Inventory Visibility Data Feed App.... :)";
	}
	
	@RequestMapping(value = "/postGELEDShipment ", method = RequestMethod.POST)
	public List<GEShipmentEntity> postGELEDShipment(
			@RequestBody  String jsonString,HttpServletRequest request, HttpServletResponse response)  {
		oAuth.oAuthValidator1(request, response);
		ObjectMapper mapper = new ObjectMapper();
		ParseGEShipmentJsonDTO parseGEShipmentJsonDTO = null;
		JSONObject jObject  = new JSONObject(jsonString);
		
	       	 try {
		        		 parseGEShipmentJsonDTO = mapper.readValue(jObject.toString(), ParseGEShipmentJsonDTO.class);
		          } catch (JsonParseException e) {
		                 // TODO Auto-generated catch block
		        	  log.error(e.getMessage());
		          } catch (JsonMappingException e) {
		                 // TODO Auto-generated catch block
		        	  log.error(e.getMessage());
		          } catch (IOException e) {
		                 // TODO Auto-generated catch block
		        	  log.error(e.getMessage());
		         }
	       	 
		return dataFeedService.postGELEDShipment(parseGEShipmentJsonDTO.getRecordset());
		
	}
	
	@RequestMapping(value = "/postCustomerConsumption", method = RequestMethod.POST)
	public List<CustomerConsumptionEntity> postCustomerConsumption(
			@RequestBody  String jsonString,HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		ObjectMapper mapper = new ObjectMapper();
		ParseCustomerConsumptionJsonDTO parseCustomerConsumptionJsonDTO = null;
		JSONObject jObject  = new JSONObject(jsonString);
		
	       	 try {
	       		parseCustomerConsumptionJsonDTO = mapper.readValue(jObject.toString(), ParseCustomerConsumptionJsonDTO.class);
		          } catch (JsonParseException e) {
		                 // TODO Auto-generated catch block
		        	  log.error(e.getMessage());
		          } catch (JsonMappingException e) {
		                 // TODO Auto-generated catch block
		        	  log.error(e.getMessage());
		          } catch (IOException e) {
		                 // TODO Auto-generated catch block
		        	  log.error(e.getMessage());
		         }
	       	 
		
		return dataFeedService.postCustomerConsumption(parseCustomerConsumptionJsonDTO.getRecordset());

	}
	
	@RequestMapping(value = "/postPurchasingOrder", method = RequestMethod.POST)
	public List<PurchasingOrderEntity> postPurchasingOrder(
			@RequestBody  String jsonString,HttpServletRequest request, HttpServletResponse response) {
		//oAuth.oAuthValidator1(request, response);
			ObjectMapper mapper = new ObjectMapper();
			ParsePurchasingOrderDTO parsePurchasingOrderDTO = null;
			JSONObject jObject  = new JSONObject(jsonString);
			
		       	 try {
		       		parsePurchasingOrderDTO = mapper.readValue(jObject.toString(), ParsePurchasingOrderDTO.class);
			          } catch (JsonParseException e) {
			                 // TODO Auto-generated catch block
			        	  log.error(e.getMessage());
			          } catch (JsonMappingException e) {
			                 // TODO Auto-generated catch block
			        	  log.error(e.getMessage());
			          } catch (IOException e) {
			                 // TODO Auto-generated catch block
			        	  log.error(e.getMessage());
			         }
		       	 
		return dataFeedService.postPurchasingOrder(parsePurchasingOrderDTO.getRecordset());

	}
	
	@RequestMapping(value = "/postCustomerDemand", method = RequestMethod.POST)
	public List<CustomerDemandEntity> postCustomerDemand(@RequestBody  String jsonString,HttpServletRequest request, HttpServletResponse response){
		oAuth.oAuthValidator1(request, response);
			ObjectMapper mapper = new ObjectMapper();
			ParseSAPDemandDTO parseSAPDemandDTO = null;
			JSONObject jObject  = new JSONObject(jsonString);
			
		       	 try {
		       		parseSAPDemandDTO = mapper.readValue(jObject.toString(), ParseSAPDemandDTO.class);
			          } catch (JsonParseException e) {
			                 // TODO Auto-generated catch block
			        	  log.error(e.getMessage());
			          } catch (JsonMappingException e) {
			                 // TODO Auto-generated catch block
			        	  log.error(e.getMessage());
			          } catch (IOException e) {
			                 // TODO Auto-generated catch block
			        	  log.error(e.getMessage());
			         }
		return dataFeedService.postCustomerDemand(parseSAPDemandDTO.getRecordset());
		
	}
	
	

/*		
	@RequestMapping(value = "/postGELEDShipment ", method = RequestMethod.POST)
	public List<GEShipmentEntity> postGELEDShipment(
			@RequestBody List<GEShipmentJsonDTO> geShipmentDTOs,HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		//return dataFeedService.postGELEDShipment(geShipmentDTOs);
		return null;
	}*/

	/*@RequestMapping(value = "/postGECoreShipment ", method = RequestMethod.POST)
	public List<CoreShipmentEntity> postGECoreShipment(
			@RequestBody List<CoreShipmentJsonDTO> coreShipmentDTOs,HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		return dataFeedService.postGECoreShipment(coreShipmentDTOs);

	}*/

	/*@RequestMapping(value = "/postCustomerConsumption", method = RequestMethod.POST)
	public List<CustomerConsumptionEntity> postCustomerConsumption(
			@RequestBody List<CustomerConsumptionJsonDTO> consumptionJsonDTOs,HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		return dataFeedService.postCustomerConsumption(consumptionJsonDTOs);

	}*/

	/*@RequestMapping(value = "/postPurchasingOrder", method = RequestMethod.POST)
	public List<PurchasingOrderEntity> postPurchasingOrder(
			@RequestBody List<PurchasingOrderDTO> purchasingOrderDTOs,HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		return dataFeedService.postPurchasingOrder(purchasingOrderDTOs);

	}*/
	/*@RequestMapping(value = "/postCustomerDemand", method = RequestMethod.POST)
	public List<CustomerDemandEntity> postCustomerDemand(@RequestBody List<SAPDemandDTO> sapDemandDTOs,HttpServletRequest request, HttpServletResponse response){
		oAuth.oAuthValidator1(request, response);
		return dataFeedService.postCustomerDemand(sapDemandDTOs);
		
	}*/
	
	@RequestMapping(value = "/getSAPUploadDetails")
	public List<SAPUploadDTO> getSAPUploadDetails(HttpServletRequest request, HttpServletResponse response){
		//oAuth.oAuthValidator1(request, response);
		return dataFeedService.getSAPUploadDetails();
		
	}
	@RequestMapping(value = "/getSAPLoggerData")
	public List<SAPLoggerEntity> getSAPLoggerData(@RequestParam("sapUploadId") Long sapUploadId,HttpServletRequest request, HttpServletResponse response){
		//oAuth.oAuthValidator1(request, response);
		return dataFeedService.getSAPLoggerData(sapUploadId);
		
	}
	
	
}
