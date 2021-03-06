package hr.optimus.boardingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import hr.optimus.boardingapp.service.CandidateService;
import hr.optimus.boardingapp.service.MicroBlinkApiService;
import hr.optimus.boardingapp.service.dto.CandidateDTO;
import hr.optimus.boardingapp.service.dto.CandidateResponseDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("candidate")
@RequiredArgsConstructor
public class UserController {	
	
	
	private final CandidateService service;
	private final MicroBlinkApiService apiService;
	
	@RequestMapping( value ="/new", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public ResponseEntity<Object> addNewCandidate(@RequestBody List<Map<String, String>> formData){
		CandidateDTO candidateDto = service.addNewCandidate(formData);
		if(null == candidateDto) {
			return new ResponseEntity<Object>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		return new ResponseEntity<Object>(candidateDto, HttpStatus.OK);   
	}
	
	
	
	@RequestMapping( value ="", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public ResponseEntity<Object> addNewCandidateRest(@RequestBody CandidateDTO dto){
		
		CandidateDTO candidateDto = service.addNewCandidate(dto);
		if(null == candidateDto) {
			return new ResponseEntity<Object>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		return new ResponseEntity<Object>(candidateDto, HttpStatus.OK);   
	}
	
	@RequestMapping( value ="/response/{candidateId}/", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public ResponseEntity<Object> collectRestResponse(@RequestBody CandidateResponseDTO dto){
		CandidateResponseDTO ret  = service.addCandidateResponse(dto);
		if(null == ret) {
			return new ResponseEntity<Object>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(ret, HttpStatus.OK);
	}
	

	
	@RequestMapping( value ="/recognize", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
    public ResponseEntity<Object> singleFileUpload(@RequestParam("file") MultipartFile file) throws Exception{
		CandidateDTO dto = apiService.getDataFromEndPoint(file);
		if(null==dto.getFirstName()) {
			 return new ResponseEntity<Object>("Not found!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(dto,HttpStatus.OK);


    }

	@RequestMapping( value ="/collect/response", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST
			)
//	public ResponseEntity<Object> collectResponse(@RequestBody String payload){
	public ResponseEntity<Object> collectResponse(@RequestBody List<Map<String, String>> formData){
		List<CandidateResponseDTO> ret  = service.addCandidateResponses(formData);
		if(null == ret) {
			return new ResponseEntity<Object>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(ret, HttpStatus.OK);
	}
	
	@RequestMapping( value ="/responses/{candidateId}/{boardId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.GET)
	public ResponseEntity<Object> getStatistics(@PathVariable String candidateId,
			@PathVariable String boardId){
		List<CandidateResponseDTO> ret = service.getCandiatesResponses(new Long(candidateId));
		if(null == ret || ret.size() == 0) {
			return new ResponseEntity<Object>("Not Found!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(ret, HttpStatus.OK);
	}
	
	
	
	

}
