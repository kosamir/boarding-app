package hr.optimus.boardingapp.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FieldDTO {

	private Long id;

	private String label;
	private String type;
	private String value;

}
