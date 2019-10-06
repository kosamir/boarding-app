package hr.optimus.boardingapp.integration.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hr.optimus.boardingapp.model.BoardingTemplate;
import hr.optimus.boardingapp.model.Field;
import hr.optimus.boardingapp.model.Form;
import hr.optimus.boardingapp.model.enums.FieldType;
import hr.optimus.boardingapp.repository.BoardingTemplateRepository;
import hr.optimus.boardingapp.repository.FieldRepository;
import hr.optimus.boardingapp.repository.FormRepository;
import lombok.RequiredArgsConstructor;

@RunWith(SpringRunner.class)
@DataJpaTest
@RequiredArgsConstructor
public class RepostitoryTest {
	
	private final BoardingTemplateRepository repository;
	private final FormRepository formRepository;
	private final FieldRepository fieldRepository;
	
	private BoardingTemplate template = new BoardingTemplate();
	
	@Before
	public void contextLoads() {
		
	    template = new BoardingTemplate();
		template.setName("TEST" );

		Form form = new Form();
		form.setName("Unos podataka" );
		template.addForm(form);

		Field field = new Field();
		field.setLabel("Ime" );
		field.setType(FieldType.SHORT_TEXT_FIELD);
		form.addField(field);

		Field field2 = new Field();
		field2.setLabel("Prezime" );
		field2.setType(FieldType.SHORT_TEXT_FIELD);
		form.addField(field2);

		Field field3 = new Field();
		field3.setLabel("Adresa" );
		field3.setType(FieldType.LONG_TEXT_FIELD);
		form.addField(field3);

		Field field4 = new Field();
		field4.setLabel("Datum" );
		field4.setType(FieldType.DATE);
		form.addField(field4);

		Field field5 = new Field();
		field5.setLabel("Spol" );
		field5.setType(FieldType.CHOICE);
		form.addField(field5);

		Field field6 = new Field();
		field6.setLabel("Upisi broj" );
		field6.setType(FieldType.NUMBER);
		form.addField(field6);

		Field field7 = new Field();
		field7.setLabel("Multiple" );
		field7.setType(FieldType.MULTIPLE_CHOICE);
		form.addField(field7);

		Field field8 = new Field();
		field8.setLabel("check bok" );
		field8.setType(FieldType.CHECKBOX);
		
		form.addField(field8);
		form.setNumOfFields(form.getFields().size());

		template.setNumOfForms(template.getForms().size());

		repository.save(template);

		BoardingTemplate templateDB = repository.findById(template.getId()).orElse(null);

		Form form2 = new Form();
		form2.setName("Programski jezici" );
		templateDB.addForm(form2);

		Field field9 = new Field();
		field9.setLabel("Najdrazi programski jezik" );
		field9.setType(FieldType.SHORT_TEXT_FIELD);
		form2.addField(field9);

		form2.setNumOfFields(form2.getFields().size());
		templateDB.setNumOfForms(templateDB.getForms().size());

		template = repository.save(templateDB);
	}

	@Test
	public void removeForm() {
		
		BoardingTemplate tmpl = repository.findById(template.getId()).orElse(null);
		Long id = tmpl.getForms().get(0).getId();
		Form form = formRepository.findById(id).orElse(null);
		if(null != form) {
			tmpl.removeForm(form);
		}
		repository.saveAndFlush(tmpl);
		Form form2 = formRepository.findById(id).orElse(null);
		assertThrows(NoSuchElementException.class, 
				() -> tmpl.getForms().stream().filter(f->f.equals(form2)).findAny().get());
		assertEquals(null, form2);
		
	}
	
	@Test
	public void removeField() {
		BoardingTemplate tmpl = repository.findById(template.getId()).orElse(null);
		Long formId = tmpl.getForms().get(0).getId();
		Long fieldId = tmpl.getForms().get(0).getFields().get(0).getId();
		Form form = formRepository.findById(formId).orElse(null);
		Field filed = fieldRepository.findById(fieldId).orElse(null);
		if(null != filed) {
			form.removeField(filed);
		}
		repository.saveAndFlush(tmpl);
		
		Field filed2 = fieldRepository.findById(fieldId).orElse(null);
		assertThrows(NoSuchElementException.class, 
				() -> tmpl.getForms().stream().
				filter(f->f.equals(form)).findFirst()
				.get().getFields().
					stream().filter(field -> field.equals(filed2)));
		assertEquals(null, filed2);
	}

}
