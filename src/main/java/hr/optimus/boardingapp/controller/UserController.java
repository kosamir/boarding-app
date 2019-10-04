package hr.optimus.boardingapp.controller;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hr.optimus.boardingapp.config.RestApiConfig;
import hr.optimus.boardingapp.service.dto.CandidateDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("candidate")
@RequiredArgsConstructor
public class UserController {	
	
	private final RestApiConfig config;
	private final RestTemplate template;
	
	@RequestMapping( value ="/new", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public ResponseEntity<Object> addNewCandidate(@PathVariable String boardId, @RequestBody CandidateDTO CandidateDTO){
		CandidateDTO formDto = new CandidateDTO();//boardingTemplateService.addForm(new Long(boardId), p_dto);
		return new ResponseEntity<Object>(formDto, HttpStatus.OK);   
	}
	
	@RequestMapping( value ="/{candidateId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCandidate(@PathVariable String candidateId, @RequestBody CandidateDTO CandidateDTO){
		CandidateDTO formDto = new CandidateDTO();//boardingTemplateService.addForm(new Long(boardId), p_dto);
		return new ResponseEntity<Object>(formDto, HttpStatus.OK);   
	}
	
	@RequestMapping( value ="/recognize", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
    public ResponseEntity<Object> singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception{
 		byte[] bytes = file.getBytes();
 		String encodedImage = Base64.getEncoder().encodeToString(bytes);
 		System.out.println(encodedImage);
 		HttpHeaders headers = new HttpHeaders();
 	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
 	    HttpEntity <String> entity = new HttpEntity<String>(headers);
        
 		
 		

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

	@RequestMapping( value ="/collect/response", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public ResponseEntity<Object> collectResponse(@RequestBody String payload){
		
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping( value ="/{candidateId/responses/{boardId}}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.GET)
	public ResponseEntity<Object> getStatistics(@PathVariable String candidateId,
			@PathVariable String boardId){
		
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
	
	

}
