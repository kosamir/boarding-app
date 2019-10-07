package hr.optimus.boardingapp.service.dto;

import lombok.Data;

@Data
public class CandidateResponseDTO {
	private Long id;
	private String boardName;
	private String formName;
	private String fieldName;
	private String answer;
	
	private BoardingTemplateDTO template;
	private FormDTO form;
	private FieldDTO field;
	private CandidateDTO candidate;

}
