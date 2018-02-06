/**
 * 
 */
package com.ge.current.digitalinventoryservice;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.current.digitalinventoryservice.dto.HeatMapSecondLevelDTO;
import com.ge.current.digitalinventoryservice.heatmap.entity.AddressDetailsEntity;
import com.ge.current.digitalinventoryservice.heatmap.entity.FSGAddressDetailsEntity;
import com.ge.current.digitalinventoryservice.heatmap.entity.FsgToCusomerDetailsEntity;
import com.ge.current.digitalinventoryservice.heatmap.entity.FsgToFsgDetailsEntity;
import com.ge.current.digitalinventoryservice.heatmap.entity.HeatMapFirstlevelDetailsEntity;
import com.ge.current.digitalinventoryservice.repository.IHeatMapRepository;
import com.ge.current.digitalinventoryservice.util.DigitalInventoryConstants;
import com.ge.current.digitalinventoryservice.util.UtilManager;

/**
 * @author ncheredd
 *
 */
@RestController
@RequestMapping("/div/heatmap")
public class HeatMapService 
{
	@Autowired
	private IHeatMapRepository iHeatMapRepository;
	
	@RequestMapping("/landingpage")
	public @ResponseBody List<HeatMapFirstlevelDetailsEntity> getHeatMapLandingPageDetails(HttpServletRequest request, HttpServletResponse response) 
	{
//		oAuth.oAuthValidator1(request, response);
		return iHeatMapRepository.getHeatMapFirstLevelDetails();
	}
	
	@RequestMapping("/secondpage")
	public @ResponseBody List<List<HeatMapSecondLevelDTO>> getHeatMapSecondPageDetails(HttpServletRequest request, HttpServletResponse response) 
	{
//		oAuth.oAuthValidator1(request, response);
		Map<String, List<HeatMapSecondLevelDTO>> responseMap = null;
		List<FSGAddressDetailsEntity> fsgAddressList = iHeatMapRepository.getFsgAddressDetails();
		List<FsgToCusomerDetailsEntity> fsgToCustomerList = iHeatMapRepository.getFsgToCustomerDetails();
		List<FsgToFsgDetailsEntity> fsgToFsgList = iHeatMapRepository.getFsgToFsgDetails();
		Map<String, FSGAddressDetailsEntity> fsgAddressDetailsMap = fsgAddressList.stream().collect(Collectors.toMap(FSGAddressDetailsEntity::getFsgName, FSGAddressDetailsEntity -> FSGAddressDetailsEntity));
		responseMap = populateFsgToCustomerResponseDetails(fsgAddressDetailsMap, fsgToCustomerList);
		responseMap = populateFsgToFsgResponseDetails(responseMap, fsgAddressDetailsMap, fsgToFsgList);
		return new ArrayList<List<HeatMapSecondLevelDTO>>(responseMap.values());
	}
	
	
	private Map<String, List<HeatMapSecondLevelDTO>> populateFsgToFsgResponseDetails(
			Map<String, List<HeatMapSecondLevelDTO>> responseMap,
			Map<String, FSGAddressDetailsEntity> fsgAddressDetailsMap,
			List<FsgToFsgDetailsEntity> fsgToFsgList) 
	{
		for(FsgToFsgDetailsEntity entity : fsgToFsgList)
		{
			String fromLocation = entity.getFromFsgName();
			String toLocation = entity.getToFsgName();
			
			List<HeatMapSecondLevelDTO> list = null;
			if(responseMap.containsKey(fromLocation))
			{
				list = responseMap.get(fromLocation);
				setFSGAddressDTO(fsgAddressDetailsMap, toLocation, list);
				responseMap.put(fromLocation, list);
			}
			else
			{
				FSGAddressDetailsEntity fromFSGAdd = fsgAddressDetailsMap.get(fromLocation);
				if(UtilManager.isValidString(fromFSGAdd.getLatitude()) && UtilManager.isValidString(fromFSGAdd.getLongitude()))
				{
					list = new ArrayList<HeatMapSecondLevelDTO>();
					setFSGAddressDTO(fsgAddressDetailsMap, fromLocation,list);
					setFSGAddressDTO(fsgAddressDetailsMap, toLocation, list);
					responseMap.put(fromLocation, list);
				}
			}
		}
		return responseMap;
	}

	private void setFSGAddressDTO(Map<String, FSGAddressDetailsEntity> fsgAddressDetailsMap,
			String fromLocation, List<HeatMapSecondLevelDTO> list) 
	{
		HeatMapSecondLevelDTO dto;
		dto = getFsgResponseDTO(fsgAddressDetailsMap, fromLocation);
		if(dto != null)
			list.add(dto);
	}

	private HeatMapSecondLevelDTO getFsgResponseDTO(Map<String, FSGAddressDetailsEntity> fsgAddressDetailsMap,
			String fsgLocation) 
	{
		FSGAddressDetailsEntity fsgAddressEntity = fsgAddressDetailsMap.get(fsgLocation);
		String lat = fsgAddressEntity.getLatitude();
		String lng = fsgAddressEntity.getLongitude();
		if(UtilManager.isValidString(lat) && UtilManager.isValidString(lng))
		{
			HeatMapSecondLevelDTO dto = new HeatMapSecondLevelDTO();
			dto.setLatitude(Double.parseDouble(lat));
			dto.setLongitude(Double.parseDouble(lng));
			dto.setDescription(getFSGAddrressAsString(fsgAddressEntity));
			dto.setType(DigitalInventoryConstants.LOCATION_TYPE_FSG);
			return dto;
		}
		return null;
	}

	private Map<String, List<HeatMapSecondLevelDTO>> populateFsgToCustomerResponseDetails(
			Map<String, FSGAddressDetailsEntity> fsgAddressDetailsMap,
			List<FsgToCusomerDetailsEntity> fsgToCustomerList) 
	{
		Map<String, List<HeatMapSecondLevelDTO>> fsgToCustomerResponseMap = new HashMap<String, List<HeatMapSecondLevelDTO>>();
		for(FsgToCusomerDetailsEntity entity : fsgToCustomerList)
		{
			if(UtilManager.isValidString(entity.getLatitude()) && UtilManager.isValidString(entity.getLongitude()))
			{
				List<HeatMapSecondLevelDTO> list = null;
				String fsgName = entity.getFsgName();
				if(fsgToCustomerResponseMap.containsKey(fsgName))
				{
					list = fsgToCustomerResponseMap.get(fsgName);
				}
				else
				{
					list = new ArrayList<HeatMapSecondLevelDTO>();
					setFSGAddressDTO(fsgAddressDetailsMap, fsgName, list);
					//list.add(getFsgResponseDTO(fsgAddressDetailsMap, fsgName));
				}
				list.add(getCustResponseDTO(entity));
				fsgToCustomerResponseMap.put(fsgName, list);
			}
		}
		return fsgToCustomerResponseMap;
	}

	private HeatMapSecondLevelDTO getCustResponseDTO(FsgToCusomerDetailsEntity entity) 
	{
		HeatMapSecondLevelDTO dto = new HeatMapSecondLevelDTO();
		dto.setLatitude(Double.parseDouble(entity.getLatitude()));
		dto.setLongitude(Double.parseDouble(entity.getLongitude()));
		dto.setDescription(getCustAddrressAsString(entity));
		dto.setType(DigitalInventoryConstants.LOCATION_TYPE_CUSTOMER);
		return dto;
	}

	private String getFSGAddrressAsString(FSGAddressDetailsEntity fsgAddressEntity) 
	{
		StringBuffer addressBuffer = new StringBuffer();
		addressBuffer.append(fsgAddressEntity.getFsgName()).append(DigitalInventoryConstants.HYPHEN_WITH_SPACES);
		addressBuffer.append(fsgAddressEntity.getStreet()).append(DigitalInventoryConstants.SPACE);
		addressBuffer.append(fsgAddressEntity.getCity()).append(DigitalInventoryConstants.SPACE);
		addressBuffer.append(fsgAddressEntity.getState()).append(DigitalInventoryConstants.SPACE);
		addressBuffer.append(fsgAddressEntity.getCountry()).append(DigitalInventoryConstants.SPACE);
		addressBuffer.append(fsgAddressEntity.getZipcode());
		return addressBuffer.toString();
	}

	private String getCustAddrressAsString(FsgToCusomerDetailsEntity entity) 
	{
		StringBuffer addressBuffer = new StringBuffer();
		addressBuffer.append(entity.getStreet()).append(DigitalInventoryConstants.SPACE);
		addressBuffer.append(entity.getCity()).append(DigitalInventoryConstants.SPACE);
		addressBuffer.append(entity.getState()).append(DigitalInventoryConstants.SPACE);
		addressBuffer.append(entity.getCountry()).append(DigitalInventoryConstants.SPACE);
		addressBuffer.append(entity.getZipcode());
		return addressBuffer.toString();
	}

	@RequestMapping("/insertlatlong")
	public @ResponseBody String getLatLongByAddress(HttpServletRequest request, HttpServletResponse response) 
	{
		Map<String, Double> coords;
		//String address = "5851 San Felipe Rd, Houston, TX, 77057, USA";
		String address = null;
		List<HeatMapFirstlevelDetailsEntity> entityList = null;
		int count = 0;
		StringBuffer resString = new StringBuffer();
		List<AddressDetailsEntity> successEntityList = new ArrayList<AddressDetailsEntity>();
		entityList = iHeatMapRepository.getLatLongNotAvailableAddressDetails();
		System.out.println("No of customers.."+entityList.size());
		for(HeatMapFirstlevelDetailsEntity entity: entityList)
		{
			try
			{
				AddressDetailsEntity addressEntity = iHeatMapRepository.findByAddressId(entity.getAddressId()).get(0);
				address = getAddressString(addressEntity);
				System.out.println("Checking heatmap"+address);
				
				coords = com.ge.current.digitalinventoryservice.util.OpenStreetMapUtils.getInstance().getCoordinates(address);
				
				if(coords == null || coords.isEmpty())
				{
					
					
						try {
							
							StringBuffer address3=new StringBuffer();
							address3.append(addressEntity.getStreet()).append(DigitalInventoryConstants.COMMA + DigitalInventoryConstants.SPACE).append(addressEntity.getZipcode());
							String address4=address3.toString();
							System.out.println(address3);
							
							coords = com.ge.current.digitalinventoryservice.util.OpenStreetMapUtils.getInstance().getCoordinates(address4);
							
							//System.out.println(coords.get("lat").toString());
							//System.out.println(coords.get("lon").toString());
							//resString.append(entity.getAddressId()).append(DigitalInventoryConstants.COMMA + DigitalInventoryConstants.SPACE);
							
							if(coords == null || coords.isEmpty())
							{
								String address1 = addressEntity.getZipcode();
								//System.out.println(address1);
								coords = com.ge.current.digitalinventoryservice.util.OpenStreetMapUtils.getInstance().getCoordinates(address1);
								//System.out.println(coords.get("lat").toString());
								//System.out.println(coords.get("lon").toString());
								//resString.append(entity.getAddressId()).append(DigitalInventoryConstants.COMMA + DigitalInventoryConstants.SPACE);
								//addressEntity.setAddressId(entity.getAddressId());
								
								
								addressEntity.setLatitude(coords.get("lat").toString());
								addressEntity.setLongitude(coords.get("lon").toString());
								System.out.println(addressEntity);
								successEntityList.add(addressEntity);
							
								System.out.println(successEntityList);

								System.out.println("Fetched.."+count);
							}
							else
							{
							addressEntity.setLatitude(coords.get("lat").toString());
							addressEntity.setLongitude(coords.get("lon").toString());
							//System.out.println(addressEntity);
							successEntityList.add(addressEntity);

							System.out.println(successEntityList);

							System.out.println("Fetched.."+count);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
					else
				{
					count = count+1;
					addressEntity.setLatitude(coords.get("lat").toString());
					addressEntity.setLongitude(coords.get("lon").toString());
					successEntityList.add(addressEntity);
					System.out.println("Fetched.."+count);
				}
				//iHeatMapRepository.save(entity);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		System.out.println("insert start");
		iHeatMapRepository.save(successEntityList);
		System.out.println("insert finished");

		if(resString.length() != 0)
			return "Failed to insert lat & long details for address_id's : " + resString;
		return "Succefully inserted lat & long details for all address's.";
	}

	// For FSG address Lat Long
	@RequestMapping("/insertFSGlatlong")
	public @ResponseBody String getFSGLatLongByAddress(HttpServletRequest request, HttpServletResponse response) 
	{
		Map<String, Double> coords;
		//String address = "5851 San Felipe Rd, Houston, TX, 77057, USA";
		String address = null;
		List<FSGAddressDetailsEntity> entityList = null;
		int count = 0;
		StringBuffer resString = new StringBuffer();
		List<AddressDetailsEntity> successEntityList = new ArrayList<AddressDetailsEntity>();
		entityList = iHeatMapRepository.getFSGLatLongNotAvailableAddressDetails();
		System.out.println("No of FSGs.."+entityList.size());
		for(FSGAddressDetailsEntity entity: entityList)
		{
			try
			{
				AddressDetailsEntity addressEntity = iHeatMapRepository.findByAddressId(entity.getAddressId()).get(0);
				address = getAddressString(addressEntity);
				coords = com.ge.current.digitalinventoryservice.util.OpenStreetMapUtils.getInstance().getCoordinates(address);
				if(coords == null || coords.isEmpty())
					resString.append(entity.getAddressId()).append(DigitalInventoryConstants.COMMA + DigitalInventoryConstants.SPACE);
				else
				{
					count = count+1;
					addressEntity.setLatitude(coords.get("lat").toString());
					addressEntity.setLongitude(coords.get("lon").toString());
					successEntityList.add(addressEntity);
					System.out.println("Fetched.."+count);
				}
				//iHeatMapRepository.save(entity);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		System.out.println("insert fsg start");
		iHeatMapRepository.save(successEntityList);
		System.out.println("insert fsg finished");

		if(resString.length() != 0)
			return "Failed to insert lat & long details for address_id's : " + resString;
		return "Succefully inserted lat & long details for all address's.";
	}



	private String getAddressString(AddressDetailsEntity entity) 
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(entity.getStreet()).append(DigitalInventoryConstants.COMMA + DigitalInventoryConstants.SPACE);
		buffer.append(entity.getCity()).append(DigitalInventoryConstants.COMMA + DigitalInventoryConstants.SPACE);
		buffer.append(entity.getState()).append(DigitalInventoryConstants.COMMA + DigitalInventoryConstants.SPACE);
		buffer.append(entity.getZipcode()).append(DigitalInventoryConstants.COMMA + DigitalInventoryConstants.SPACE);
		buffer.append(entity.getCountry());
		return buffer.toString();
	}
	
/*	@RequestMapping("/insertlatlongbyaddress")
	public @ResponseBody String getLatLongByAddress(@Param("addressId") String addressId, HttpServletRequest request, HttpServletResponse response) 
	{
		Map<String, Double> coords;
		List<AddressDetailsEntity> addressEntityList = iHeatMapRepository.findByAddressId(Integer.parseInt(addressId));
		if(addressEntityList != null && !addressEntityList.isEmpty())
		{
		//	AddressDetailsEntity entity = addressEntityList.get(0);
			String sampleAddress = "5851 San Felipe Rd, Houston, TX, 77057, USA";
			System.out.println("*********==== sampleAddress : "+sampleAddress);
		//	String address = getAddressString(entity);
//			System.out.println("*********==== address : "+address);
			coords = com.ge.current.digitalinventoryservice.util.OpenStreetMapUtils.getInstance().getCoordinates(sampleAddress);
			
			
			if(coords != null && !coords.isEmpty())
			{
				//entity.setLatitude(coords.get("lat").toString());
			//	entity.setLongitude(coords.get("lon").toString());
				//iHeatMapRepository.save(entity);
				return "Succefully inserted lat & long details for all address's." + "   " +addressId +coords.get("lat").toString() +"  " + coords.get("lon").toString() ;
			}else
				return "Unable to find lat & long details for address_id's : " + addressId;
		}
		return "Data not available in DB. addressId is : "+ addressId;
	}*/
}
