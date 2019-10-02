package hr.optimus.boardingapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "boarding_form")
@Data
public class Form {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "form_id")
	private Long id;
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "form_id")
	private List <Field> field;
	

}
