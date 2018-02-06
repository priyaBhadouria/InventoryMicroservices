package com.ge.current.digitalinventoryservice;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto.CustConsDrilldownSummaryDTO;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto.CustomerConsumptionDTO;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto.CustomerReturnDTO;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto.FSGIssuedSummaryDTO;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto.FSGReceiptSummaryDTO;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.dto.FSGTrendDrilldownSummaryDTO;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustConsDrilldownSiteSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustConsDrilldownSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustTrendFSGDrilldownEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustomerConsumptionSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.CustomerReturnSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGIssuedAggregationSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGReceivedAggregationSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGTrendDrilldownLocSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGTrendDrilldownSummaryEntity;
import com.ge.current.digitalinventoryservice.divtrendanalysisservice.entity.FSGTrendFsgSummaryEntity;
import com.ge.current.digitalinventoryservice.repository.ICustomerTrendOverviewRepository;
import com.ge.current.digitalinventoryservice.repository.IFSGOverviewTrendRepository;
import com.ge.current.digitalinventoryservice.util.DIVTrendConstants;
import com.ge.current.digitalinventoryservice.util.UtilManager;

@RestController
public class DIVTrendAnalysisService {

	@Autowired
	IFSGOverviewTrendRepository fsgTrend;
	
	@Autowired
	OauthVerification oAuth;

	@Autowired
	ICustomerTrendOverviewRepository customerTrend;

	/**
	 * This method is to get the aggregation of FSGIssued data for FSG Trend
	 * Analysis graphs
	 * 
	 * @param fsg
	 * @return List<FSGIssuedSummaryDTO>
	 */
	@RequestMapping("/getFSGIssuedTrendSummary")
	public @ResponseBody List<FSGIssuedSummaryDTO> getFSGIssuedAggregationSummary(
			@RequestParam("fsg") String fsg, HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
		List<FSGIssuedSummaryDTO> fsgSummaryDTOList = new ArrayList<FSGIssuedSummaryDTO>();
		if (fsg.equals(DIVTrendConstants.ALL)) {
			List<FSGIssuedAggregationSummaryEntity> fsgTrendSummaryEntityList = fsgTrend
					.getFSGIssuedAggregationSummary();
			List<FSGIssuedSummaryDTO> fsgList = new ArrayList<FSGIssuedSummaryDTO>();
			for (FSGIssuedAggregationSummaryEntity entity : fsgTrendSummaryEntityList) {
				FSGIssuedSummaryDTO fsgSummaryDTO = new FSGIssuedSummaryDTO();
				double quantity = entity.getAllQuantity();
				LocalDateTime issuedDate = entity.getIssuedDate();
				fsgSummaryDTO.setQuantity(quantity);
				fsgSummaryDTO.setIssuedDate(issuedDate.toInstant(
						ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
				fsgList.add(fsgSummaryDTO);
				fsgSummaryDTOList = fsgList.stream().distinct()
						.collect(Collectors.toList());
			}
		} else {
			List<FSGIssuedAggregationSummaryEntity> fsgTrendSummaryEntityList = fsgTrend
					.getFSGIssuedLocationAggregationSummary(fsg);
			for (FSGIssuedAggregationSummaryEntity entity : fsgTrendSummaryEntityList) {
				FSGIssuedSummaryDTO fsgSummaryDTO = new FSGIssuedSummaryDTO();
				double quantity = entity.getFsgQuantity();
				LocalDateTime issuedDate = entity.getIssuedDate();
				fsgSummaryDTO.setQuantity(quantity);
				fsgSummaryDTO.setIssuedDate(issuedDate.toInstant(
						ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
				fsgSummaryDTOList.add(fsgSummaryDTO);

			}
		}
		return fsgSummaryDTOList;
	}

	/**
	 * This method is to get the aggregation of FSGReceived data for FSG Trend
	 * Analysis graphs
	 * 
	 * @param fsg
	 * @return List<FSGReceiptSummaryDTO>
	 */
	@RequestMapping("/getFSGReciptTrendSummary")
	public @ResponseBody List<FSGReceiptSummaryDTO> getFSGReceiptAggregationSummary(
			@RequestParam("fsg") String fsg, HttpServletRequest request, HttpServletResponse response) 
	{
		oAuth.oAuthValidator1(request, response);
		List<FSGReceiptSummaryDTO> fsgSummaryDTOList = new ArrayList<FSGReceiptSummaryDTO>();

		if (fsg.equalsIgnoreCase(DIVTrendConstants.ALL)) {
			List<FSGReceiptSummaryDTO> fsgList = new ArrayList<FSGReceiptSummaryDTO>();
			List<FSGReceivedAggregationSummaryEntity> fsgTrendSummaryEntityList = fsgTrend
					.getFSGReceivedAggregationSummary();

			for (FSGReceivedAggregationSummaryEntity entity : fsgTrendSummaryEntityList) {
				FSGReceiptSummaryDTO fsgSummaryDTO = new FSGReceiptSummaryDTO();
				double quantity = entity.getAllReceivedQuantity();
				LocalDateTime receivedDate = entity.getReceivedDate();
				fsgSummaryDTO.setActualQuantity(quantity);
				fsgSummaryDTO.setReceiptDate(receivedDate.toInstant(
						ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
				fsgList.add(fsgSummaryDTO);
				fsgSummaryDTOList = fsgList.stream().distinct()
						.collect(Collectors.toList());
			}
		} else {
			List<FSGReceiptSummaryDTO> fsgList = new ArrayList<FSGReceiptSummaryDTO>();
			List<FSGReceivedAggregationSummaryEntity> fsgTrendSummaryEntityList = fsgTrend
					.getFSGReceivedLocationAggregationSummary(fsg);

			for (FSGReceivedAggregationSummaryEntity entity : fsgTrendSummaryEntityList) {
				FSGReceiptSummaryDTO fsgSummaryDTO = new FSGReceiptSummaryDTO();
				double quantity = entity.getFsgReceivedQuantity();
				LocalDateTime receivedDate = entity.getReceivedDate();
				fsgSummaryDTO.setActualQuantity(quantity);
				fsgSummaryDTO.setReceiptDate(receivedDate.toInstant(
						ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
				fsgList.add(fsgSummaryDTO);
				fsgSummaryDTOList = fsgList.stream().distinct()
						.collect(Collectors.toList());
			}
		}

		return fsgSummaryDTOList;
	}

	/**
	 * This method is to get the aggregation of CustomerConsumption data for
	 * Customer Consumption Trend Analysis graphs
	 * 
	 * @param customerSiteId
	 * @return List<CustomerConsumptionDTO>
	 */
	@RequestMapping("/getCustomerConsumptionTrendSummary")
	public @ResponseBody List<CustomerConsumptionDTO> getCustomerConsumptionAggregationSummary(
			@Param("customerSiteId") String customerSiteId, HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<CustomerConsumptionDTO> customerConsumptionDTOList = new ArrayList<CustomerConsumptionDTO>();

		if (customerSiteId.equalsIgnoreCase(DIVTrendConstants.ALL)) {
			List<CustomerConsumptionSummaryEntity> customerConsumptionSummaryEntity = customerTrend
					.getCustomerConsumptionSummary();
		//	List<CustomerConsumptionDTO> consList = new ArrayList<CustomerConsumptionDTO>();

			for (CustomerConsumptionSummaryEntity entity : customerConsumptionSummaryEntity) {
				CustomerConsumptionDTO customerConsumptionDTO = new CustomerConsumptionDTO();
				double quantity = entity.getAllConsumedQty();
				LocalDateTime issuedDate = entity.getInstallationDate();
				customerConsumptionDTO.setQuantity(quantity);
				customerConsumptionDTO.setIssuedDate(issuedDate.toInstant(
						ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
				customerConsumptionDTOList.add(customerConsumptionDTO);
				//customerConsumptionDTOList = consList.stream().distinct()
					//	.collect(Collectors.toList());
			}
		} else {
			List<CustomerConsumptionSummaryEntity> customerConsumptionSummaryEntity = customerTrend
					.getCustomerConsumptionSiteIdSummary(customerSiteId);

			for (CustomerConsumptionSummaryEntity entity : customerConsumptionSummaryEntity) {
				CustomerConsumptionDTO customerConsumptionDTO = new CustomerConsumptionDTO();
				double quantity = entity.getCustomerConsumedQty();
				LocalDateTime issuedDate = entity.getInstallationDate();
				customerConsumptionDTO.setQuantity(quantity);
				customerConsumptionDTO.setIssuedDate(issuedDate.toInstant(
						ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
				customerConsumptionDTOList.add(customerConsumptionDTO);
			}
		}
		return customerConsumptionDTOList;
	}

	/**
	 * This method is to get the aggregation of CustomerReturns data for
	 * Customer Consumption Trend Analysis graphs
	 *
	 * @param customerSiteId
	 * @return List<CustomerReturnDTO>
	 */
	@RequestMapping("/getCustomerReturnTrendSummary")
	public @ResponseBody List<CustomerReturnDTO> getCustomerReturnAggregationSummary(
			@RequestParam("customerSiteId") String customerSiteId, HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<CustomerReturnDTO> customerReturnDTOList = new ArrayList<CustomerReturnDTO>();
		if (customerSiteId.equalsIgnoreCase(DIVTrendConstants.ALL)) {
			List<CustomerReturnSummaryEntity> customerReturnSummaryEntity = customerTrend
					.getCustomerReturnSummary();
			List<CustomerReturnDTO> returnsList = new ArrayList<CustomerReturnDTO>();

			for (CustomerReturnSummaryEntity entity : customerReturnSummaryEntity) {
				CustomerReturnDTO customerReturnDTO = new CustomerReturnDTO();
				double quantity = entity.getAllReturnedQty();
				LocalDateTime returnDate = entity.getReturnDate();
				customerReturnDTO.setQuantity(quantity);
				customerReturnDTO.setReturnDate(returnDate.toInstant(
						ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
				returnsList.add(customerReturnDTO);
				customerReturnDTOList = returnsList.stream().distinct()
						.collect(Collectors.toList());

			}
		} else {
			List<CustomerReturnSummaryEntity> customerReturnSummaryEntity = customerTrend
					.getCustomerReturnSiteIdSummary(customerSiteId);

			for (CustomerReturnSummaryEntity entity : customerReturnSummaryEntity) {
				CustomerReturnDTO customerReturnDTO = new CustomerReturnDTO();
				double quantity = entity.getSiteReturnedQty();
				LocalDateTime returnDate = entity.getReturnDate();
				customerReturnDTO.setQuantity(quantity);
				customerReturnDTO.setReturnDate(returnDate.toInstant(
						ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
				customerReturnDTOList.add(customerReturnDTO);
			}

		}
		return customerReturnDTOList;
	}

	/**
	 * This method is to fetch FSG Issued and FSG Received for 1st level
	 * drilldown data in FSG Trend Analysis
	 * 
	 * @param date
	 * @param fsg
	 * @return List<FSGTrendDrilldownSummaryDTO>
	 * @throws ParseException
	 */
	@RequestMapping("/getFSGTrendDrilldownSummary")
	public @ResponseBody List<FSGTrendDrilldownSummaryDTO> getFSGTrendDrilldownSummary(
			@RequestParam String date, @RequestParam("fsg") String fsg, HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		oAuth.oAuthValidator1(request, response);
		LocalDate dateInput = convertStringtoDate(date);
		List<FSGTrendDrilldownSummaryDTO> fsgSummaryDTOList = new ArrayList<FSGTrendDrilldownSummaryDTO>();

		if (fsg.equalsIgnoreCase(DIVTrendConstants.ALL)) {
			List<FSGTrendDrilldownSummaryEntity> fsgTrendSummaryEntityList = fsgTrend.getFSGTrendDrilldownSummary(dateInput);
			for (FSGTrendDrilldownSummaryEntity entity : fsgTrendSummaryEntityList) {
				FSGTrendDrilldownSummaryDTO fsgSummaryDTO = new FSGTrendDrilldownSummaryDTO();
				fsgSummaryDTO.setMaterial(entity.getMaterial());
				fsgSummaryDTO.setMaterialDescription(entity
						.getMaterialDescription());
				if (null != entity.getFsgIssuedquantity())
					fsgSummaryDTO.setFsgIssuedquantity(entity
							.getFsgIssuedquantity());
				if (null != entity.getFsgReceivedquantity())
					fsgSummaryDTO.setFsgReceivedquantity(entity
							.getFsgReceivedquantity());
				fsgSummaryDTO.setDate(convertDatetoString(entity.getDate()));

				fsgSummaryDTOList.add(fsgSummaryDTO);
			}
		} else {
			List<FSGTrendDrilldownLocSummaryEntity> fsgTrendSummaryEntityList = fsgTrend.getFSGTrendDrilldownLocSummary(fsg, dateInput);
			for (FSGTrendDrilldownLocSummaryEntity entity : fsgTrendSummaryEntityList) {
				FSGTrendDrilldownSummaryDTO fsgSummaryDTO = new FSGTrendDrilldownSummaryDTO();
				fsgSummaryDTO.setMaterial(entity.getMaterial());
				fsgSummaryDTO.setMaterialDescription(entity
						.getMaterialDescription());
				if (null != entity.getFsgIssuedquantity())
					fsgSummaryDTO.setFsgIssuedquantity(entity
							.getFsgIssuedquantity());
				if (null != entity.getFsgReceivedquantity())
					fsgSummaryDTO.setFsgReceivedquantity(entity
							.getFsgReceivedquantity());
				fsgSummaryDTO.setDate(convertDatetoString(entity.getDate()));

				fsgSummaryDTOList.add(fsgSummaryDTO);
			}
		}
		return fsgSummaryDTOList;
	}

	/**
	 * This method is to fetch FSG Issued or FSG Received for 2nd level
	 * drilldown data in FSG Trend Analysis
	 *
	 * 
	 * @param material
	 * @param date
	 * @param reqInput
	 * @return List<FSGTrendDrilldownSummaryDTO>
	 * @throws ParseException
	 */
	@RequestMapping("/getFSGLocSummary")
	public @ResponseBody List<FSGTrendDrilldownSummaryDTO> getFSGLocSummary(
			@RequestParam String material, @RequestParam String date,
			@RequestParam String reqInput, @RequestParam String fsgName, HttpServletRequest request, HttpServletResponse response) throws ParseException 
	{
		oAuth.oAuthValidator1(request, response);
		List<FSGTrendDrilldownSummaryDTO> fsgSummaryDTOList = null;
		LocalDate issued_date = convertStringtoDate(date);
		List<FSGTrendFsgSummaryEntity> fsgTrendSummaryEntityList = getFSGTrendDetails(material, reqInput, fsgName, issued_date);
		if (reqInput.equals(DIVTrendConstants.FSG_ISSUED)) 
			fsgSummaryDTOList = getFSGIssuedTrendDetails(fsgTrendSummaryEntityList);
		else
			fsgSummaryDTOList = getFSGReceivedTrendDetails(fsgTrendSummaryEntityList);
		return fsgSummaryDTOList;
	}

	private List<FSGTrendDrilldownSummaryDTO> getFSGReceivedTrendDetails(List<FSGTrendFsgSummaryEntity> fsgTrendSummaryEntityList) 
	{
		List<FSGTrendDrilldownSummaryDTO> fsgSummaryDTOList = new ArrayList<FSGTrendDrilldownSummaryDTO>();
		for (FSGTrendFsgSummaryEntity entity : fsgTrendSummaryEntityList) 
		{
			Double receivedQty = entity.getFsgReceived();
			if (UtilManager.isValidInteget(receivedQty))
			{
				FSGTrendDrilldownSummaryDTO fsgSummaryDTO = new FSGTrendDrilldownSummaryDTO();
				fsgSummaryDTO.setMaterial(entity.getMaterial());
				fsgSummaryDTO.setFsgReceivedquantity(receivedQty);
				fsgSummaryDTO.setLocation(entity.getReceivedlocation());
				fsgSummaryDTO.setDate(entity.getDate().toString());
				fsgSummaryDTOList.add(fsgSummaryDTO);
			}
		}
		return fsgSummaryDTOList;
	}

	private List<FSGTrendDrilldownSummaryDTO> getFSGIssuedTrendDetails(List<FSGTrendFsgSummaryEntity> fsgTrendSummaryEntityList) 
	{
		List<FSGTrendDrilldownSummaryDTO> fsgSummaryDTOList = new ArrayList<FSGTrendDrilldownSummaryDTO>();
		List<String> fsgIssuedLocations = new ArrayList<String>();
		for (FSGTrendFsgSummaryEntity entity : fsgTrendSummaryEntityList) 
		{
			String fsgLocation = entity.getIssuedlocation();
			if(!fsgIssuedLocations.contains(fsgLocation))
			{
				Double issuedQty = entity.getFsgIssued();
				if (UtilManager.isValidInteget(issuedQty))
				{
					FSGTrendDrilldownSummaryDTO fsgSummaryDTO = new FSGTrendDrilldownSummaryDTO();
					fsgSummaryDTO.setMaterial(entity.getMaterial());
					fsgSummaryDTO.setFsgIssuedquantity(issuedQty);
					fsgSummaryDTO.setIssuedLocation(fsgLocation);
					fsgSummaryDTO.setDate(entity.getDate().toString());
					fsgSummaryDTOList.add(fsgSummaryDTO);
					fsgIssuedLocations.add(fsgLocation);
				}
			}
		}
		return fsgSummaryDTOList;
	}

	private List<FSGTrendFsgSummaryEntity> getFSGTrendDetails(String material,
			String reqInput, String fsgName, LocalDate issued_date) 
	{
		List<FSGTrendFsgSummaryEntity> fsgTrendSummaryEntityList;
		if(fsgName.equalsIgnoreCase(DIVTrendConstants.ALL))
			fsgTrendSummaryEntityList = fsgTrend.getFSGTrendFsgSummary(material, issued_date);
		else
		{
			if(reqInput.equals(DIVTrendConstants.FSG_ISSUED))
				fsgTrendSummaryEntityList = fsgTrend.getFSGTrendFsgIssuedSummary(material, issued_date, fsgName);
			else
				fsgTrendSummaryEntityList = fsgTrend.getFSGTrendFsgReceivedSummary(material, issued_date, fsgName);
		}
		return fsgTrendSummaryEntityList;
	}

	/**
	 * This method is to fetch all the FSG details in DB for Service Provider
	 * List drop down
	 * 
	 * @return List<String>
	 */
	@RequestMapping("/getFSGSummary")
	public @ResponseBody List<String> getFSGSummary(HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<String> responseList = fsgTrend.getFSGSummary();

		List<String> fsgList = responseList.stream().distinct()
				.collect(Collectors.toList());

		return fsgList;
	}

	/**
	 * This method is to fetch Customer Consumptions and Customer returns for
	 * 1st level drilldown data in Customer Consumption Trend Analysis
	 * 
	 * @param date
	 * @param customerSiteId
	 * @return List<CustConsDrilldownSummaryDTO>
	 * @throws ParseException
	 */
	@RequestMapping("/getCustConsTrendDrilldownSummary")
	public @ResponseBody List<CustConsDrilldownSummaryDTO> getCustConsTrendDrilldownSummary(
			@RequestParam String date,
			@RequestParam("customerSiteId") String customerSiteId, HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		oAuth.oAuthValidator1(request, response);
		LocalDate dateInput = convertStringtoDate(date);
		List<CustConsDrilldownSummaryDTO> custSummaryDTOList = new ArrayList<CustConsDrilldownSummaryDTO>();
		if (customerSiteId.equalsIgnoreCase(DIVTrendConstants.ALL)) {
			List<CustConsDrilldownSummaryEntity> custConsDrilldownSummaryList = customerTrend
					.getCustConsDrilldownSummary(dateInput);
			for (CustConsDrilldownSummaryEntity entity : custConsDrilldownSummaryList) {
				CustConsDrilldownSummaryDTO custSummaryDTO = new CustConsDrilldownSummaryDTO();
				custSummaryDTO.setMaterial(entity.getMaterial());
				custSummaryDTO.setMaterialDesc(entity.getMaterialDescription());
				if (null != entity.getCustConsQuantity())
					custSummaryDTO.setCustConsumptions(entity
							.getCustConsQuantity());
				if (null != entity.getCustReturnQuantity())
					custSummaryDTO.setCustReturns(entity
							.getCustReturnQuantity());
				custSummaryDTO.setDate(convertDatetoString(entity.getDate()));
				custSummaryDTOList.add(custSummaryDTO);
			}
		} else {

			List<CustConsDrilldownSiteSummaryEntity> custConsDrilldownSummaryList = customerTrend
					.getCustConsSiteDrilldownSummary(dateInput, customerSiteId);
			for (CustConsDrilldownSiteSummaryEntity entity : custConsDrilldownSummaryList) {
				CustConsDrilldownSummaryDTO custSummaryDTO = new CustConsDrilldownSummaryDTO();
				custSummaryDTO.setMaterial(entity.getMaterial());
				custSummaryDTO.setMaterialDesc(entity.getMaterialDescription());
				if (null != entity.getCustConsQuantity())
					custSummaryDTO.setCustConsumptions(entity
							.getCustConsQuantity());
				if (null != entity.getCustReturnQuantity())
					custSummaryDTO.setCustReturns(entity
							.getCustReturnQuantity());
				custSummaryDTO.setDate(convertDatetoString(entity.getDate()));
				custSummaryDTOList.add(custSummaryDTO);
			}
		}

		return custSummaryDTOList;
	}

	/**
	 * This method is to fetch Customer Consumptions or Customer returns for 2nd
	 * level drilldown data in Customer Consumption Trend Analysis
	 * 
	 * @param material
	 * @param date
	 * @param reqInput
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getCustTrendFSGLocSummary")
	public @ResponseBody List<CustConsDrilldownSummaryDTO> getCustTrendFSGLocSummary(
			@RequestParam String material, @RequestParam String date,
			@RequestParam String reqInput, @RequestParam String customerId, HttpServletRequest request, HttpServletResponse response) throws ParseException 
	{
		oAuth.oAuthValidator1(request, response);
		List<CustConsDrilldownSummaryDTO> custSummaryDTOList = null;
		LocalDate issued_date = convertStringtoDate(date);
		List<CustTrendFSGDrilldownEntity> custTrendSummaryEntityList = getCustomerTrendDetails(material,customerId, issued_date);
		if (reqInput.equals(DIVTrendConstants.CUSTOMER_CONSUMPTIONS)) 
			custSummaryDTOList = getCustomerConsumptionDetails(custTrendSummaryEntityList);
		else
			custSummaryDTOList = getCustomerReceivedDetails(custTrendSummaryEntityList);
		return custSummaryDTOList;
	}

	private List<CustConsDrilldownSummaryDTO> getCustomerReceivedDetails(List<CustTrendFSGDrilldownEntity> custTrendSummaryEntityList) 
	{
		List<CustConsDrilldownSummaryDTO> custSummaryDTOList = new ArrayList<CustConsDrilldownSummaryDTO>();
		for (CustTrendFSGDrilldownEntity entity : custTrendSummaryEntityList) 
		{
			Double return_Qty = entity.getCustRQuantity();
			if(UtilManager.isValidInteget(return_Qty))
			{
				CustConsDrilldownSummaryDTO custTrendSummaryDTO = new CustConsDrilldownSummaryDTO();
				custTrendSummaryDTO.setCustId(entity.getCustomerId());
				custTrendSummaryDTO.setCustDesc(entity.getCustomerDesc());
				custTrendSummaryDTO.setCustReturns(return_Qty);
				custTrendSummaryDTO.setReceivedLoc(entity.getReceivedLoc());
				custTrendSummaryDTO.setDate(entity.getDate().toString());
				custSummaryDTOList.add(custTrendSummaryDTO);
			}
		}
		return custSummaryDTOList;
	}

	private List<CustConsDrilldownSummaryDTO> getCustomerConsumptionDetails(List<CustTrendFSGDrilldownEntity> custTrendSummaryEntityList) 
	{
		List<CustConsDrilldownSummaryDTO> custSummaryDTOList = new ArrayList<CustConsDrilldownSummaryDTO>();
		for (CustTrendFSGDrilldownEntity entity : custTrendSummaryEntityList) 
		{
			Double consumption_Qty = entity.getCustConsQuantity();
			if(UtilManager.isValidInteget(consumption_Qty))
			{
				CustConsDrilldownSummaryDTO custTrendSummaryDTO = new CustConsDrilldownSummaryDTO();
				custTrendSummaryDTO.setCustId(entity.getCustomerId());
				custTrendSummaryDTO.setCustDesc(entity.getCustomerDesc());
				custTrendSummaryDTO.setCustConsumptions(consumption_Qty);
				custTrendSummaryDTO.setInstallationLoc(entity.getInstallationLoc());
				custTrendSummaryDTO.setDate(entity.getDate().toString());
				custSummaryDTOList.add(custTrendSummaryDTO);
			}
		}
		return custSummaryDTOList;
	}

	private List<CustTrendFSGDrilldownEntity> getCustomerTrendDetails(String material, String customerId, LocalDate issued_date) 
	{
		List<CustTrendFSGDrilldownEntity> custTrendSummaryEntityList;
		if(customerId.equalsIgnoreCase(DIVTrendConstants.ALL))
			custTrendSummaryEntityList = customerTrend.getCustomerTrendFsgSummary(material, issued_date);
		else
			custTrendSummaryEntityList = customerTrend.getCustomerTrendFsgSummary(material, issued_date, customerId);
		return custTrendSummaryEntityList;
	}

	/**
	 * This method is to fetch all the Customer details from DB for Customer
	 * Site ID drop down list in Customer Consumption Trend
	 * 
	 * @return List<String>
	 */
	@RequestMapping("/getCustomerSummary")
	public @ResponseBody List<String> getCustomerSummary(HttpServletRequest request, HttpServletResponse response) {
		oAuth.oAuthValidator1(request, response);
		List<String> responseList = customerTrend.getCustomerSummary();

		List<String> customerList = responseList.stream().distinct()
				.collect(Collectors.toList());

		return customerList;
	}

	/**
	 * This method is to convert Date to desired string format
	 * 
	 * @param dateInput
	 * @return
	 */
	public String convertDatetoString(LocalDate dateInput) {
		String stringDate = null;
		if (dateInput != null) {
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("dd-MM-YYYY");
			stringDate = formatter.format(dateInput);
		}
		return stringDate;
	}

	/**
	 * This method is to convert String date to LocalDate
	 * 
	 * @param dateInString
	 * @return
	 * @throws ParseException
	 */
	public LocalDate convertStringtoDate(String dateInString)
			throws ParseException {

		String[] parts = dateInString.split(DIVTrendConstants.HYPHEN);
		String part1 = parts[0];
		if (DIVTrendConstants.ONE == part1.length())
			part1 = DIVTrendConstants.ZERO + part1;

		String part2 = parts[1];
		if (DIVTrendConstants.ONE == part2.length())
			part2 = DIVTrendConstants.ZERO + part2;

		String part3 = parts[2];
		String dateString = part3 + DIVTrendConstants.HYPHEN + part2
				+ DIVTrendConstants.HYPHEN + part1;

		LocalDate localDate = LocalDate.parse(dateString);
		return localDate;
	}

}
