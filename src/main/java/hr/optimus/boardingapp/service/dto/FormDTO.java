package hr.optimus.boardingapp.service.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormDTO {
	
	private Long id;
	private String name;
	private Integer numOfFields;
	private List<FieldDTO> fields;

}
