package hr.optimus.boardingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.optimus.boardingapp.error.ApiError;
import hr.optimus.boardingapp.service.BoardingTemplateService;
import hr.optimus.boardingapp.service.dto.FormDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("boarding_template")
@RequiredArgsConstructor
public class FormController {

	private final BoardingTemplateService boardingTemplateService;  
	
	@RequestMapping( value ="/{boardId}/form", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public ResponseEntity<Object> addNewForm(@PathVariable String boardId, @RequestBody FormDTO p_dto){
		FormDTO formDto = boardingTemplateService.addForm(new Long(boardId), p_dto);
		if(null== formDto) {
			return  new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Error",null), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<Object>(formDto, HttpStatus.OK);   
	}
	
	
	@RequestMapping( value ="/{boardId}/form/{formId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.GET)
	public ResponseEntity<Object> getFormById(@PathVariable String boardId, @PathVariable String formId){
		FormDTO dto = boardingTemplateService.getFormById(new Long(boardId), new Long(formId));
		if(null == dto) {
			return  new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND,"Not Found",null), HttpStatus.NOT_FOUND);  
		}
		return new ResponseEntity<Object>(dto, HttpStatus.OK);   
	}
	
	@RequestMapping( value ="/{boardId}/form/{formId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.PUT)
	public ResponseEntity<Object> updateFormById(@PathVariable String boardId, @PathVariable String formId, @RequestBody FormDTO p_dto){
		FormDTO dto = boardingTemplateService.updateForm(new Long(boardId), new Long(formId), p_dto);
		if(dto == null) {
			return  new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND,"Not Found",null), HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Object>(dto, HttpStatus.OK);   
	}
	
	@RequestMapping( value ="/{boardId}/form/{formId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteFormById(@PathVariable String boardId, @PathVariable String formId){
		FormDTO dto = boardingTemplateService.removeForm(new Long(boardId), new Long(formId));
		if(dto == null) {
			return  new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND,"Not Found",null), HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Object>(dto, HttpStatus.OK);   
	}
}
