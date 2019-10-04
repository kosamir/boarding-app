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
import hr.optimus.boardingapp.service.dto.BoardingTemplateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("boarding_template")
@RequiredArgsConstructor
@Slf4j
public class BoardingTemplateController {
	
	private final BoardingTemplateService boardingTemplateService;
	
	@RequestMapping( value ="/new", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public ResponseEntity<BoardingTemplateDTO> addNewTempalate(@RequestBody BoardingTemplateDTO dto){
		BoardingTemplateDTO retDto = boardingTemplateService.addTemplate(dto);
		return new ResponseEntity<BoardingTemplateDTO>(retDto, HttpStatus.OK);   
	}
	
	
	@RequestMapping(value = "/get/{templateId}", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET )
	public ResponseEntity<BoardingTemplateDTO> getTemplateById(@PathVariable String templateId){
		// todo validacija jsona
		BoardingTemplateDTO dto = boardingTemplateService.getTemplateById(new Long(templateId));

		return  new ResponseEntity<BoardingTemplateDTO>(dto, HttpStatus.OK); 
	}
	
	
	@RequestMapping(value = "/edit/{templateId}", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			
			method = RequestMethod.PUT )
	public ResponseEntity<BoardingTemplateDTO> updateTemplate(@PathVariable String templateId, @RequestBody BoardingTemplateDTO dto){
		BoardingTemplateDTO ret = boardingTemplateService.updateTemplate(new Long(templateId), dto);

		return new ResponseEntity<BoardingTemplateDTO>(ret, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/delete/{templateId}", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.DELETE )
	public ResponseEntity<BoardingTemplateDTO> deleteTemplate(@PathVariable String templateId){
		
		return  new ResponseEntity<BoardingTemplateDTO>(boardingTemplateService.removeTemplate(new Long(templateId)), HttpStatus.OK); 
	}
	
	

}
