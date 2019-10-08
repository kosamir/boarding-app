package hr.optimus.boardingapp.service.impl;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.optimus.boardingapp.config.RestApiConfig;
import hr.optimus.boardingapp.controller.ApiCallObject;
import hr.optimus.boardingapp.service.MicroBlinkApiService;
import hr.optimus.boardingapp.service.dto.CandidateDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MicroBlinkApiServiceImpl implements MicroBlinkApiService {
	
	private final RestApiConfig config;
	private final RestTemplate template;

	@Override
	public CandidateDTO getDataFromEndPoint(MultipartFile file) throws Exception {
		ApiCallObject body = new ApiCallObject();
 		body.setImageBase64(Base64.getEncoder().encodeToString(file.getBytes()));
 		body.setUserId(config.getUserId());
 		body.setRecognizers(config.getApiRequestData());
 		
 		HttpHeaders headers = new HttpHeaders();
 	    headers.put("Authorization", Arrays.asList(config.getBearerToken()));
 	    headers.setContentType(MediaType.APPLICATION_JSON);
 	    ObjectMapper mapper = new ObjectMapper();
 	    String jsonString = mapper.writeValueAsString(body);
 	    
 	    HttpEntity <String> request = new HttpEntity<String>(jsonString,headers);
 	   
 	    ResponseEntity<String> response = template.exchange(config.getApiUrl(), HttpMethod.POST, request, String.class);
 	    String responseJson = response.getBody();

 	    if(mapper.readTree(responseJson).get("data").get(0).get("result").isNull() ) {
 	    	return new CandidateDTO();
 	    }
 	    JsonNode data = mapper.readTree(responseJson).get("data").get(0).get("result");
		return new CandidateDTO(null,
        		data.path("firstName").asText(),
        		data.path("lastName").asText(),
        		"drugom prilikom",
        		data.path("dateOfBirth").path("originalString").asText(),
        		data.path("sex").asText());
	}

}
