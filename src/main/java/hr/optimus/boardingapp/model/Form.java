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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "boarding_form")
@Getter
@Setter
@ToString(exclude = {"template"})
@EqualsAndHashCode( of = {"form_id"})
public class Form {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "form_id")
	private Long id;
	private String name;
	private Integer numOfFields;
	
	@ManyToOne
	@JoinColumn(name = "board_id")
	private BoardingTemplate template;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }
			,mappedBy = "form", orphanRemoval = true
			)
//	@JoinColumn(name = "form_id")
	private List <Field> fields = new ArrayList<Field>();
	

	public void addField(Field f) {
		fields.add(f);
		f.setForm(this);
	}
	public void removeField(Field f) {
		boolean remove = this.fields.remove(f);
		f.setForm(null);
	}
}
