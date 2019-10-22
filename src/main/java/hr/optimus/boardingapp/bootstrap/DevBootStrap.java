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
		createTemplate();
	}

	
	public void createTemplate() {
		BoardingTemplate tempate = new BoardingTemplate();
		tempate.setName("TEMPLATE_TEST");

		Form form = new Form();
		form.setName("Unos podataka");
		tempate.addForm(form);

		Field field = new Field();
		field.setLabel("Ime");
		field.setType(FieldType.SHORT_TEXT_FIELD);
		form.addField(field);

		Field field2 = new Field();
		field2.setLabel("Prezime");
		field2.setType(FieldType.SHORT_TEXT_FIELD);
		form.addField(field2);

		Field field3 = new Field();
		field3.setLabel("Adresa");
		field3.setType(FieldType.SHORT_TEXT_FIELD);
		form.addField(field3);

		Field field4 = new Field();
		field4.setLabel("Datum");
		field4.setType(FieldType.DATE);
		form.addField(field4);

		Field field5 = new Field();
		field5.setLabel("Spol");
		field5.setType(FieldType.CHOICE);
		field5.setValue("Male,Female,Other");
		form.addField(field5);



		repository.save(tempate);

		BoardingTemplate template = repository.findById(tempate.getId()).orElse(null);
		System.out.println(template.toString());

		Form f2 = new Form();
		f2.setName("Programski jezici");
		template.addForm(f2);

		Field field9 = new Field();
		field9.setLabel("Najdrazi programski jezik");
		field9.setType(FieldType.CHOICE);
		field9.setValue("Java, Haskel, Clojure, R, Lisp, Kotlin");
		f2.addField(field9);


		repository.save(template);

	}

	
}
