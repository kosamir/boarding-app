package hr.optimus.boardingapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "boarding_template")  
@Data
@ToString
public class BoardingTemplate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "board_id")
	private Long id;
	private String name;
	private Integer numOfForms;

	@OneToMany(fetch = FetchType.EAGER, 
			cascade = { CascadeType.ALL}
			, mappedBy = "template"
			, orphanRemoval = true
			)
//	@JoinColumn(name = "board_id")
	private List<Form> forms = new ArrayList<Form>();
	
	public void addForm(Form f) {
		this.forms.add(f);
		f.setTemplate(this);
	}
	
	public void removeForm(Form f) {
		boolean removed = this.forms.remove(f);
		f.setTemplate(null);
		
	}
}
