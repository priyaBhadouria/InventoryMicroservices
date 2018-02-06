package com.ge.current.digitalinventoryservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.current.digitalinventoryservice.repository.ITotalInventoryTransactionRepository;
import com.ge.current.digitalinventoryservice.totalTransaction.dto.TotalInventoryTransactionDTO;
import com.ge.current.digitalinventoryservice.totalTransaction.entity.TotalInventoryTransactionEntity;
import com.ge.current.digitalinventoryservice.util.HtmlEscapeStringEditor;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author 842449
 *
 */

@Configuration
@RestController
public class DivTransactionServices {

	@Autowired
	OauthVerification oAuth;

	@Autowired
	private ITotalInventoryTransactionRepository totInvTransactionSerivce;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private HtmlEscapeStringEditor htmlEscapeStringEditor;

	@RequestMapping(value = "/getMessage", method = RequestMethod.GET)
	public String getMessage() {

		return "Welcome To Digital Inventory Visibility.... :)";
	}

	@RequestMapping(value = "/getTotalInventoryTransactionData", method = RequestMethod.GET)
	public @ResponseBody List<TotalInventoryTransactionDTO> getTotalInventoryTransactionData(
			HttpServletRequest request, HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		List<TotalInventoryTransactionEntity> totalInvTransactions = (List<TotalInventoryTransactionEntity>) this.totInvTransactionSerivce
				.selectAllData();
		List<TotalInventoryTransactionDTO> totalInvTransactionsDtos = new ArrayList<TotalInventoryTransactionDTO>();
		for (TotalInventoryTransactionEntity InvTransaction : totalInvTransactions) {
			TotalInventoryTransactionDTO inventoryTransactionDTO = new TotalInventoryTransactionDTO();
			inventoryTransactionDTO.setCustomerPO(InvTransaction
					.getCustomerPO());
			inventoryTransactionDTO.setDestination(InvTransaction
					.getDestination());
			inventoryTransactionDTO.setDestinationType(InvTransaction
					.getDestinationType());
			inventoryTransactionDTO.setMaterial(InvTransaction.getMaterial());
			inventoryTransactionDTO.setMaterialDescription(InvTransaction
					.getMaterialDescription());
			inventoryTransactionDTO.setQuantity(InvTransaction.getQuantity());
			inventoryTransactionDTO.setShipmentOrder(UtilManager
					.isJsonValidLong(InvTransaction.getShipmentOrder()));
			inventoryTransactionDTO.setSource(InvTransaction.getSource());
			inventoryTransactionDTO.setSourceType(InvTransaction
					.getSourceType());
			inventoryTransactionDTO.setTrackingNumber(InvTransaction
					.getTrackingNumber());
			inventoryTransactionDTO
					.setTransactionDate(convertDatetoString(InvTransaction
							.getTransactionDate()));
			inventoryTransactionDTO.setType(InvTransaction.getType());

			totalInvTransactionsDtos.add(inventoryTransactionDTO);

		}
		return totalInvTransactionsDtos;
	}

	public String convertDatetoString(Date dateInput) {
		String stringDate = null;
		if (dateInput != null) {
			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			// to convert Date to String, use format method of SimpleDateFormat
			// class.
			stringDate = dateFormat.format(dateInput);
		}
		return stringDate;
	}

	@RequestMapping(value = "/getAllMaterials", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllMaterial(
			HttpServletRequest request, HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		List<String> mat = new ArrayList<String>();
		mat = (List<String>) this.totInvTransactionSerivce.getAllMaterial();
		return mat;
	}

	@RequestMapping(value = "/getAllSTOs", method = RequestMethod.GET)
	public @ResponseBody List<Long> getAllSTOs(HttpServletRequest request,
			HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		List<Long> stos = new ArrayList<Long>();
		stos = this.totInvTransactionSerivce.getAllSto();
		return stos;

	}

	@RequestMapping(value = "/getAllSources", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllSources(HttpServletRequest request,
			HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		List<String> fsgs = new ArrayList<String>();
		fsgs = (List<String>) this.totInvTransactionSerivce.getAllSources();
		return fsgs;
	}

	@RequestMapping(value = "/getAllDestinations", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllDestinations(
			HttpServletRequest request, HttpServletResponse response) {
		 oAuth.oAuthValidator1(request, response);
		List<String> fsgs = new ArrayList<String>();
		fsgs = (List<String>) this.totInvTransactionSerivce
				.getAllDestinations();
		return fsgs;

	}

	@RequestMapping(value = "/getAllFilteredData", method = RequestMethod.POST)
	public @ResponseBody List<TotalInventoryTransactionDTO> getAllFilteredData(
			@RequestBody TotalInventoryTransactionDTO totInvTransactionDTO,
			HttpServletRequest request, HttpServletResponse response) {
		// oAuth.oAuthValidator1(request, response);
		List<TotalInventoryTransactionDTO> totalInventoryTransactions = new ArrayList<TotalInventoryTransactionDTO>();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff
				.append("select * from analytics.total_inventory_transaction_view where");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		htmlEscapeStringEditor.setAsText(totInvTransactionDTO.getDateFrom());
		String dateFromStr = htmlEscapeStringEditor.getAsText();
		htmlEscapeStringEditor.setAsText(totInvTransactionDTO.getDateTo());
		String dateToStr = htmlEscapeStringEditor.getAsText();
		//Long stoFrom = totInvTransactionDTO.getStoFrom();
		//Long stoTo = totInvTransactionDTO.getStoTo();
		htmlEscapeStringEditor.setAsText(totInvTransactionDTO.getSource());
		String sourceVar = htmlEscapeStringEditor.getAsText();
		htmlEscapeStringEditor.setAsText(totInvTransactionDTO.getMaterial());
		String materialName = htmlEscapeStringEditor.getAsText();
		htmlEscapeStringEditor.setAsText(totInvTransactionDTO.getDestination());
		String destinationVar = htmlEscapeStringEditor.getAsText();
		Date dateFrom = null;
		Date dateTo = null;
		//Long MaxSto = this.totInvTransactionSerivce.getMaxSto();
		//System.out.println("MAX STO" + MaxSto);
		
		

		if ((dateFromStr != null && !dateFromStr.isEmpty())
				&& (dateToStr == null || dateToStr.isEmpty())) {
			java.util.Date today = new java.util.Date();
			dateToStr = df.format(today);
		}
		if ((dateFromStr == null || dateFromStr.isEmpty())
				&& (dateToStr != null && !dateToStr.isEmpty())) {
			java.util.Date today = new java.util.Date();
			dateFromStr = df.format(today);
		}
		try {
			if ((dateFromStr != null && !dateFromStr.isEmpty())
					&& (dateToStr != null && !dateToStr.isEmpty())) {
				dateFrom = df.parse(dateFromStr);
				dateTo = df.parse(dateToStr);
				if (dateTo.getTime() < dateFrom.getTime()) {

					Date temp;
					temp = dateFrom;
					dateFrom = dateTo;
					dateTo = temp;
				}
				queryBuff.append(" transaction_date between " + "\'" + dateFrom
						+ "\'" + "and" + "\'" + dateTo + "\'");
			} else {
				queryBuff.append("");
			}
		} catch (ParseException e) {

			e.printStackTrace();
		}

		if (materialName != null && !materialName.isEmpty()) {

			if ((dateFromStr == null || dateFromStr.isEmpty())
					&& (dateToStr == null || dateToStr.isEmpty()))

			{
				queryBuff.append(" material=" + "\'" + materialName + "\'");

			} else {
				queryBuff.append(" and ");
				queryBuff.append(" material=" + "\'" + materialName + "\'");
			}

		}

		/*if ((stoFrom != null && stoFrom.toString() != "")
				&& (stoTo == null || stoTo.toString() == "")) {
			stoTo = MaxSto;
		}
		if ((stoFrom == null || stoFrom.toString() == "")
				&& (stoTo != null && stoTo.toString() != "")) {
			stoFrom = MaxSto;
		}
		if (stoFrom != null && stoFrom.toString() != "" && stoTo != null
				&& stoTo.toString() != "") {

			if (stoFrom > stoTo) {
				stoFrom = stoFrom + stoTo;
				stoTo = stoFrom - stoTo;
				stoFrom = stoFrom - stoTo;
			}
			if ((dateFromStr == null || dateFromStr == "")
					&& (dateToStr == null || dateToStr == "")
					&& (materialName == null || materialName == ""))

			{
				queryBuff.append(" shipment_order between " + stoFrom + " and "
						+ stoTo);

			} else {
				queryBuff.append(" and shipment_order between " + stoFrom
						+ " and " + stoTo);
			}
		}*/
		if (sourceVar != null && !sourceVar.isEmpty()) {

			if ((dateFromStr == null || dateFromStr.isEmpty())
					&& (dateToStr == null || dateToStr.isEmpty())
					&& (materialName == null || materialName.isEmpty()))

			{
				queryBuff.append(" source=" + "\'" + sourceVar + "\'");

			} else {
				queryBuff.append(" and source=" + "\'" + sourceVar + "\'");
			}
		}
		if (destinationVar != null && !destinationVar.isEmpty()) {
			if ((dateFromStr == null || dateFromStr.isEmpty())
					&& (dateToStr == null || dateToStr.isEmpty())
					&& (materialName == null || materialName.isEmpty())
					&& (sourceVar == null || sourceVar.isEmpty()))

			{
				queryBuff
						.append(" destination=" + "\'" + destinationVar + "\'");

			} else {
				queryBuff.append(" and destination=" + "\'" + destinationVar
						+ "\'");
			}
		}

		queryBuff.append(";");
		System.out.println("Query" + queryBuff);
		ResultSet resultSet = null;

		Connection conn = null;

		try {
			try {
				conn = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement ps = conn.prepareStatement(
						queryBuff.toString(), ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				resultSet = ps.executeQuery();
				System.out.println("RESULT" + resultSet.toString());

				while (resultSet.next()) {
					TotalInventoryTransactionDTO totInvTransactionAdd = new TotalInventoryTransactionDTO();
					totInvTransactionAdd.setSource(resultSet
							.getString("SOURCE"));
					totInvTransactionAdd.setSourceType(resultSet
							.getString("SOURCE_TYPE"));
					totInvTransactionAdd.setDestination(resultSet
							.getString("DESTINATION"));
					totInvTransactionAdd.setDestinationType(resultSet
							.getString("DESTINATION_TYPE"));
					totInvTransactionAdd.setMaterial(resultSet
							.getString("MATERIAL"));
					totInvTransactionAdd.setMaterialDescription(resultSet
							.getString("MATERIAL_DESCRIPTION"));
					totInvTransactionAdd.setQuantity(resultSet
							.getDouble("QUANTITY"));
					totInvTransactionAdd.setType(resultSet
							.getString("TRANSACTION_TYPE"));
					totInvTransactionAdd.setShipmentOrder(resultSet
							.getString("SHIPMENT_ORDER"));
					totInvTransactionAdd.setCustomerPO(resultSet
							.getString("CUSTOMER_PO"));
					totInvTransactionAdd.setTrackingNumber(resultSet
							.getString("TRACKING_NUMBER"));
					totInvTransactionAdd.setTransactionDate(resultSet
							.getString("TRANSACTION_DATE"));
					System.out.println("divTransactionDTOAdd"
							+ totInvTransactionAdd.toString());
					totalInventoryTransactions.add(totInvTransactionAdd);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				conn.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("totalInventoryTransactions"
				+ totalInventoryTransactions.toString());
		if (totalInventoryTransactions.isEmpty()) {
			System.out.println("EMPTY RESULT");
			totalInventoryTransactions = new ArrayList<TotalInventoryTransactionDTO>();
		}
		return totalInventoryTransactions;

	}
}
