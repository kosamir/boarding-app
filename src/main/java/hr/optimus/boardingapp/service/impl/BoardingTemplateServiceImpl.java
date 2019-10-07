package hr.optimus.boardingapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import hr.optimus.boardingapp.model.BoardingTemplate;
import hr.optimus.boardingapp.model.Field;
import hr.optimus.boardingapp.model.Form;
import hr.optimus.boardingapp.repository.BoardingTemplateRepository;
import hr.optimus.boardingapp.repository.FormRepository;
import hr.optimus.boardingapp.service.BoardingTemplateService;
import hr.optimus.boardingapp.service.dto.BoardingTemplateDTO;
import hr.optimus.boardingapp.service.dto.FieldDTO;
import hr.optimus.boardingapp.service.dto.FormDTO;
import hr.optimus.boardingapp.service.dto.mapper.BoardingTemplateMapper;
import hr.optimus.boardingapp.service.dto.mapper.FieldMapper;
import hr.optimus.boardingapp.service.dto.mapper.FormMapper;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor

public class BoardingTemplateServiceImpl implements BoardingTemplateService {
	
	private final BoardingTemplateRepository boardingTemplateRepository;
	private final FormRepository formRepository;
	private final BoardingTemplateMapper mapper;
	private final FormMapper formMapper;
	private final FieldMapper fieldMapper;
	

	@Override
	public BoardingTemplateDTO addTemplate(BoardingTemplateDTO template) {
		BoardingTemplate entity = mapper.toEntity(template);
		boardingTemplateRepository.save(entity);
		BoardingTemplateDTO retDto = mapper.toDto(entity);
		return retDto;
	}

	@Override
	public BoardingTemplateDTO getTemplateById(Long id) {
		BoardingTemplate fromDb = boardingTemplateRepository.getOne(id);
		return fromDb!=null?mapper.toDto(fromDb):null;
	}

	@Override
	public BoardingTemplateDTO removeTemplate(Long Id) {
		BoardingTemplate fromDb = boardingTemplateRepository.getOne(Id);
		if(fromDb != null) {
			boardingTemplateRepository.deleteById(fromDb.getId());
			return mapper.toDto(fromDb);
		}
		return null;
	}
	
	@Override
	public BoardingTemplateDTO updateTemplate(Long Id, BoardingTemplateDTO dto) {
		BoardingTemplate fromDb = boardingTemplateRepository.getOne(Id);
		if(null == fromDb) {
			return null;
		}
		fromDb.setName(dto.getName());
		boardingTemplateRepository.save(fromDb);
		return mapper.toDto(fromDb);
	}

	@Override
	public FormDTO addForm(Long Id, FormDTO dto) {
		Form entity = formMapper.toEntity(dto);
		BoardingTemplate template = boardingTemplateRepository.getOne(Id);
		if( null == template) {
			return null;
		}
		template.addForm(entity);
		boardingTemplateRepository.saveAndFlush(template);
		//hack ovo je loshe treba istrazit zasto ne vraca id nakon flush-a
		Long formId= template.getForms().get(template.getForms().size()-1).getId();
		
		
		return formMapper.toDto(formRepository.getOne(formId));
	}

	@Override
	//TODO ne brise zapis iz baze samo ga setira na null
	
	public FormDTO removeForm(Long boardId, Long formId) {
		BoardingTemplate template = boardingTemplateRepository.getOne(boardId);
		if(template == null) {
			return null;
		}
		Optional<Form> f = template.getForms().stream().
				filter(object->object.getId().longValue()==formId.longValue()).
				findFirst();
		if(f.isPresent()) {
			template.removeForm(f.get());
			boardingTemplateRepository.saveAndFlush(template);
			return formMapper.toDto(f.get());
		}

		return null;
	}

	@Override
	public FormDTO getFormById(Long boardId, Long formId) {
		BoardingTemplate template = boardingTemplateRepository.getOne(boardId);
		if(template == null) {
			return null;
		}
		Optional<Form> f = template.getForms().stream().
				filter(object->object.getId().longValue()==formId.longValue()).
				findFirst();
		return f.isPresent()?formMapper.toDto(f.get()):null;
	}
	
	@Override
	public FormDTO updateForm(Long boardId, Long formId, FormDTO dto) {
		BoardingTemplate template = boardingTemplateRepository.getOne(boardId);
		if(template == null) {
			return null;
		}
		for (Form form : template.getForms()) {
			if(form.getId().longValue() == formId.longValue()) {
				form.setName(dto.getName());
				boardingTemplateRepository.save(template);
				return formMapper.toDto(form);
			}
			
		}
		return null;
	}

	@Override
	public FieldDTO addField(Long id, Long formId,  FieldDTO dto) {
		Field entity = fieldMapper.toEntity(dto);
		System.out.println(entity.toString());
		BoardingTemplate template = boardingTemplateRepository.getOne(id);
		if( null == template) {
			return null;
		}
		for (Form form : template.getForms()) {
			if(form.getId().longValue() == formId.longValue()) {
				form.addField(entity);
				boardingTemplateRepository.saveAndFlush(template);
				//fixme istraziti zasto ne flush-a
				entity.setId(form.getFields().get(form.getFields().size()-1).getId());
				return fieldMapper.toDto(entity);
			}
		}
		return null;
	}

	@Override
	public FieldDTO removeField(Long id, Long formId, Long fieldId) {
		
		BoardingTemplate template = boardingTemplateRepository.getOne(id);
		if( null == template) {
			return null;
		}
		
		for (Form form : template.getForms()) {
			if(form.getId().longValue() == formId.longValue()) {
				for ( Field field: form.getFields()) {
					if(field.getId().longValue() == fieldId.longValue()) {
						form.removeField(field);
						boardingTemplateRepository.saveAndFlush(template);
						return fieldMapper.toDto(field);
					}
				}
			}
		}
		return null;
	}

	@Override
	public FieldDTO getFieldById(Long id, Long formId, Long fieldId) {
		BoardingTemplate template = boardingTemplateRepository.getOne(id);
		if( null == template) {
			return null;
		}
		for (Form form : template.getForms()) {
			if(form.getId().longValue() == formId.longValue()) {
				for ( Field field: form.getFields()) {
					if(field.getId().longValue() == fieldId.longValue()) {
						return fieldMapper.toDto(field);
					}
				}
			}
		}
		return null;
	}

	@Override
	public FieldDTO updateField(Long id, Long formId, Long fieldId, FieldDTO dto) {
		Field entity = fieldMapper.toEntity(dto);
		BoardingTemplate template = boardingTemplateRepository.getOne(id);
		if( null == template) {
			return null;
		}
		for (Form form : template.getForms()) {
			if(form.getId().longValue() == formId.longValue()) {
				for ( Field field: form.getFields()) {
					if(field.getId().longValue() == fieldId.longValue()) {
						field.setLabel(entity.getLabel());
						field.setType(entity.getType());
						field.setValue(entity.getValue());
						boardingTemplateRepository.saveAndFlush(template);
						return fieldMapper.toDto(field);
						
					}
				}
			}
		}
		return null;
	}

	@Override
	public Long getTemplateByName( String name) {
		BoardingTemplate tmp = boardingTemplateRepository.getBoardingTemplateByName(name);
		return tmp!=null?tmp.getId():null;
	}

	@Override
	public List<FormDTO> getAllFormsFromBoard(Long Id) {
		List<Form> forms = formRepository.findAll();
		List<FormDTO> ret = new ArrayList<FormDTO>();
		for (Form form : forms) {
			Long boardId = form.getTemplate().getId();
			if(boardId!=null && boardId.longValue() == Id.longValue()) {
				ret.add(formMapper.toDto(form));
			}
		}
		return ret;
	}
	

}
