package hr.optimus.boardingapp.service;

import java.util.List;

import hr.optimus.boardingapp.service.dto.BoardingTemplateDTO;
import hr.optimus.boardingapp.service.dto.FieldDTO;
import hr.optimus.boardingapp.service.dto.FormDTO;

public interface BoardingTemplateService {
	
	 Long getTemplateByName(String name);
	 BoardingTemplateDTO addTemplate(BoardingTemplateDTO dto);
	 BoardingTemplateDTO getTemplateById(Long id);
	 BoardingTemplateDTO removeTemplate(Long Id);
	 BoardingTemplateDTO updateTemplate(Long Id, BoardingTemplateDTO dto);
	 
	 List<FormDTO> getAllFormsFromBoard(Long Id);
	 FormDTO addForm(Long id, FormDTO form);
	 FormDTO removeForm(Long id, Long formId);
	 FormDTO getFormById(Long id, Long formId);
	 FormDTO updateForm(Long boardId, Long formId, FormDTO dto);
	 
	 
	 FieldDTO addField(Long id, Long formId, FieldDTO dto);
	 FieldDTO removeField(Long id, Long formId, Long fieldId);
	 FieldDTO getFieldById(Long id, Long formId, Long fieldId);
	 FieldDTO updateField(Long id, Long formId, Long fieldId, FieldDTO dto);
	 

}
