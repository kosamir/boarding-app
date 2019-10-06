package hr.optimus.boardingapp.service.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardingTemplateDTO {
	
	private Long id;
	private String name;
	private Integer numOfForms;
	
	private List<FormDTO> forms;

}
