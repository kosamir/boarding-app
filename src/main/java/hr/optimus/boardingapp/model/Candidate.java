package hr.optimus.boardingapp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hr.optimus.boardingapp.model.enums.Gender;
import lombok.Data;

@Entity
@Table(name = "candidate")
@Data

public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_id")
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	   
	private Gender gender;


}
