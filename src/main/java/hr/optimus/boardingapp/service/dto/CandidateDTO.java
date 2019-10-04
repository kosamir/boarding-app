package hr.optimus.boardingapp.service.dto;

import lombok.Data;
@Data
public class CandidateDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String date;
	private String gender;

}
