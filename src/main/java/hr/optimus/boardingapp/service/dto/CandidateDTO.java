package hr.optimus.boardingapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String date;
	private String gender;

}
