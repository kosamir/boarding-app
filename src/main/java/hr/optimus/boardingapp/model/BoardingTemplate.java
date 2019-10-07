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
	@Column(unique = true)
	private String name;
	private Integer numOfForms = new Integer(0);

	@OneToMany(fetch = FetchType.EAGER, 
			cascade = { CascadeType.ALL}
			, orphanRemoval = true
			, mappedBy = "template"
			)
	private List<Form> forms = new ArrayList<Form>();
	
	public void addForm(Form f) {
		f.setTemplate(this);
		this.forms.add(f);
		numOfForms++;
	}
	
	public void removeForm(Form form) {
		form.getFields().clear();
		form.setTemplate(null);
		this.forms.remove(form);
		numOfForms--;
		
	}
}
