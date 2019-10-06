package hr.optimus.boardingapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import hr.optimus.boardingapp.model.BoardingTemplate;
import hr.optimus.boardingapp.model.Field;
import hr.optimus.boardingapp.model.Form;
import hr.optimus.boardingapp.model.enums.FieldType;
import hr.optimus.boardingapp.repository.BoardingTemplateRepository;
import lombok.RequiredArgsConstructor;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

	private final BoardingTemplateRepository repository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createTemplate(1);
		createTemplate(2);
	}

	
	public void createTemplate(int i) {
		BoardingTemplate tempate = new BoardingTemplate();
		tempate.setName("TEST" + i);

		Form form = new Form();
		form.setName("Unos podataka" + i);
		tempate.addForm(form);

		Field field = new Field();
		field.setLabel("Ime" + i);
		field.setType(FieldType.SHORT_TEXT_FIELD);
		form.addField(field);

		Field field2 = new Field();
		field2.setLabel("Prezime" + i);
		field2.setType(FieldType.SHORT_TEXT_FIELD);
		form.addField(field2);

		Field field3 = new Field();
		field3.setLabel("Adresa" + i);
		field3.setType(FieldType.LONG_TEXT_FIELD);
		form.addField(field3);

		Field field4 = new Field();
		field4.setLabel("Datum" + i);
		field4.setType(FieldType.DATE);
		form.addField(field4);

		Field field5 = new Field();
		field5.setLabel("Spol" + i);
		field5.setType(FieldType.CHOICE);
		form.addField(field5);

		Field field6 = new Field();
		field6.setLabel("Upisi broj" + i);
		field6.setType(FieldType.NUMBER);
		form.addField(field6);

		Field field7 = new Field();
		field7.setLabel("Multiple" + i);
		field7.setType(FieldType.MULTIPLE_CHOICE);
		form.addField(field7);

		Field field8 = new Field();
		field8.setLabel("check bok" + i);
		field8.setType(FieldType.CHECKBOX);
		
		form.addField(field8);
		form.setNumOfFields(form.getFields().size());

		tempate.setNumOfForms(tempate.getForms().size());

		repository.save(tempate);

		BoardingTemplate template = repository.findById(tempate.getId()).orElse(null);
		System.out.println(template.toString());

		Form f2 = new Form();
		f2.setName("Programski jezici" + i);
		template.addForm(f2);

		Field field9 = new Field();
		field9.setLabel("Najdrazi programski jezik" + i);
		field9.setType(FieldType.SHORT_TEXT_FIELD);
		f2.addField(field9);

		f2.setNumOfFields(f2.getFields().size());
		template.setNumOfForms(template.getForms().size());

		repository.save(template);

	}

	
}
