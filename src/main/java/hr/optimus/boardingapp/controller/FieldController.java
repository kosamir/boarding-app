package hr.optimus.boardingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.optimus.boardingapp.service.BoardingTemplateService;
import hr.optimus.boardingapp.service.dto.FieldDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("boarding_template")
@RequiredArgsConstructor
public class FieldController {

	private final BoardingTemplateService boardingTemplateService;  
	
	@RequestMapping( value ="{boardId}/form/{formId}/field", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public ResponseEntity<Object> addNewField(@PathVariable String boardId,@PathVariable String formId, @RequestBody FieldDTO p_dto){
		FieldDTO fieldDTO = boardingTemplateService.addField(new Long(boardId), new Long(formId), p_dto);
		if(fieldDTO == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(fieldDTO, HttpStatus.CREATED);   
	}
	
	
	@RequestMapping( value ="{boardId}/form/{formId}/field/{fieldId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.GET)
	public ResponseEntity<Object> getFieldById(@PathVariable String boardId,@PathVariable String formId,@PathVariable String fieldId){
		FieldDTO dto = boardingTemplateService.getFieldById(new Long(boardId), new Long(formId), new Long(fieldId));
		if(null == dto) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Object>(dto, HttpStatus.OK);   
	}
	
	@RequestMapping( value ="{boardId}/form/{formId}/field/{fieldId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.PUT)
	public ResponseEntity<Object> updateFieldById(@PathVariable String boardId,@PathVariable String formId,@PathVariable String fieldId, @RequestBody FieldDTO p_dto){
		FieldDTO dto =boardingTemplateService.updateField(new Long(boardId), new Long(formId), new Long(fieldId), p_dto);
		if(dto == null) {
			return new  ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(dto, HttpStatus.OK);   
	}
	
	
	@RequestMapping( value ="{boardId}/form/{formId}/field/{fieldId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteFieldById(@PathVariable String boardId,@PathVariable String formId,@PathVariable String fieldId){
		FieldDTO dto = boardingTemplateService.removeField(new Long(boardId), new Long(formId), new Long(fieldId));
		if(dto == null) {
			return new  ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(dto, HttpStatus.OK);   
	}
}
