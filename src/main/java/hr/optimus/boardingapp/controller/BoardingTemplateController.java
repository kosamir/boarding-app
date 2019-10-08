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
import hr.optimus.boardingapp.service.dto.BoardingTemplateDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("boarding_template")
@RequiredArgsConstructor
public class BoardingTemplateController {
	
	private final BoardingTemplateService boardingTemplateService;
	
	@RequestMapping( value ="", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public ResponseEntity<Object> addNewTempalate(@RequestBody BoardingTemplateDTO dto){
		BoardingTemplateDTO retDto = boardingTemplateService.addTemplate(dto);
		if(null==retDto) {
			return  new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Error",null), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<Object>(retDto, HttpStatus.OK);   
	}
	
	
	@RequestMapping(value = "/{templateId}", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET )
	public ResponseEntity<Object> getTemplateById(@PathVariable String templateId){
		BoardingTemplateDTO dto = boardingTemplateService.getTemplateById(new Long(templateId));
		if(null==dto) {
			return  new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND,"Not Found",null), HttpStatus.NOT_FOUND); 
		}
		return  new ResponseEntity<Object>(dto, HttpStatus.OK); 
	}
	
	
	@RequestMapping(value = "/{templateId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.PUT )
	public ResponseEntity<Object> updateTemplate(@PathVariable String templateId, @RequestBody BoardingTemplateDTO dto){
		BoardingTemplateDTO ret = boardingTemplateService.updateTemplate(new Long(templateId), dto);
		if(null==ret) {
			return  new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND,"Not Found",null), HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Object>(ret, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{templateId}", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.DELETE )
	public ResponseEntity<Object> deleteTemplate(@PathVariable String templateId){
		BoardingTemplateDTO dto = boardingTemplateService.removeTemplate(new Long(templateId));
		if(null == dto) {
			return  new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND,"Not Found",null), HttpStatus.NOT_FOUND); 
		}
		return  new ResponseEntity<Object>(dto, HttpStatus.OK); 
	}
	
	

}
