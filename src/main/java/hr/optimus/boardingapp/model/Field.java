package hr.optimus.boardingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hr.optimus.boardingapp.model.enums.FieldType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name="field")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString(exclude = {"form"})
@EqualsAndHashCode( of = {"id"})
public class Field {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "field_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "form_id")
	private Form form;
	
	private String label;
	@Enumerated(EnumType.STRING)
	private FieldType type;
	
	private String value;
	
	
	
	

}
