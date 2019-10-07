package hr.optimus.boardingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="candidate_response")
@Data
public class CandidateResponse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "response_id")
	private Long id;
	@OneToOne
	@JoinColumn(name = "board_id")
	private BoardingTemplate template;
	@OneToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	@OneToOne
	@JoinColumn(name = "form_id")
	private Form form;
	@OneToOne
	@JoinColumn(name = "field_id")
	private Field field;
	
//	private Long candidateId;
//	private Long boardId;
//	private Long FormId;
//	private Long FieldId;
	private String answer;

}
