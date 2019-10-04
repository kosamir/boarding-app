package hr.optimus.boardingapp.integration.jpa;

import static org.junit.Assert.assertEquals;

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

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepostitoryTest {
	
	@Autowired
	private BoardingTemplateRepository repository;
	@Autowired
	private FormRepository formRepository;
	@Autowired
	private FieldRepository fieldRepository;
	
	private BoardingTemplate tempate;
	
	@Before
	public void contextLoads() {
		tempate = new BoardingTemplate();
		tempate.setName("TEST");
		
		Form f = new Form();
		f.setName("Unos podataka");
		tempate.addForm(f);
		
		Field field = new Field();
		field.setLabel("Ime");
		field.setType(FieldType.SHORT_TEXT_FIELD);
		f.addField(field);

		
		Field field2 = new Field();
		field2.setLabel("Prezime");
		field2.setType(FieldType.SHORT_TEXT_FIELD);
		f.addField(field2);
		
		Field field3 = new Field();
		field3.setLabel("Adresa");
		field3.setType(FieldType.SHORT_TEXT_FIELD);
		f.addField(field3);
		
		
		Field field4 = new Field();
		field4.setLabel("Datum");
		field4.setType(FieldType.SHORT_TEXT_FIELD);
		f.addField(field4);
		
		Field field5 = new Field();
		field5.setLabel("Spol");
		field5.setType(FieldType.SHORT_TEXT_FIELD);
		f.addField(field5);
		
		
		repository.saveAndFlush(tempate);
		
		BoardingTemplate template = repository.findById(tempate.getId()).orElse(null);
		
		
		Form f2 = new Form();
		f2.setName("Programski jezici");  
		template.addForm(f2);
		
		Field field6 = new Field();
		field6.setLabel("Najdrazi programski jezik");
		field6.setType(FieldType.SHORT_TEXT_FIELD);
		f2.addField(field6);
	
		repository.saveAndFlush(template);
		printAll();

	}
	
	private void printAll() {
		BoardingTemplate template = repository.findById(tempate.getId()).orElse(null);
		System.out.println("Print all memebers of template_id:"+template.getId());
		for (Form form1 : template.getForms()) {
			System.out.println(form1.getName());
			System.out.println(form1.getId());
			for (Field ff : form1.getFields()) {
				System.out.println(ff.toString());
			}
		}
	}

	@Test
	public void removeForm() {
		
		BoardingTemplate template = repository.findById(tempate.getId()).orElse(null);
		Long id = tempate.getForms().get(0).getId();
		System.out.println("Removing form with id="+ id);  
		Form form = formRepository.findById(id).orElse(null);
		if(null != form) {
			template.removeForm(form);
		}
		repository.saveAndFlush(template);
		
		Form form2 = formRepository.findById(id).orElse(null);
		
		printAll();
		assertEquals(null, form2);
	}
	
	
	@Test
	public void removeField() {
		
		
		BoardingTemplate template = repository.findById(tempate.getId()).orElse(null);
		Long formId = tempate.getForms().get(0).getId();
		Long fieldId = tempate.getForms().get(0).getFields().get(0).getId();
		System.out.println("Removing field with id=3");
		Form form = formRepository.findById(formId).orElse(null);
		Field filed = fieldRepository.findById(fieldId).orElse(null);
		if(null != filed) {
			form.removeField(filed);
		}
		repository.saveAndFlush(template);
		
		Field filed2 = fieldRepository.findById(fieldId).orElse(null);
		printAll();
		assertEquals(null, filed2);
	}

}
