package hr.optimus.boardingapp.service.dto.mapper;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import hr.optimus.boardingapp.model.BoardingTemplate;
import hr.optimus.boardingapp.service.dto.BoardingTemplateDTO;
import hr.optimus.boardingapp.service.dto.FormDTO;
import lombok.RequiredArgsConstructor;

@Primary
@Service("BoardTemplateMapperImpl")
@RequiredArgsConstructor
public class BoardTemplateMapperImpl implements BoardingTemplateMapper{
	
	private final BoardingTemplateMapper mapper;
	
	public BoardingTemplate toEntity(BoardingTemplateDTO dto) {
		// TODO Auto-generated method stub
		return mapper.toEntity(dto);
	}

	@Override
	// Hibernate mapping tweak. 
	// Kartezijev produkt, podupla svaku formu unutar templeta jel board nema na sebi formId pa koliko polja na formi toliko zapisa
	// 
	public BoardingTemplateDTO toDto(BoardingTemplate entity) {
		BoardingTemplateDTO dto = mapper.toDto(entity);
		List<FormDTO> forms = dto.getForms();
		Long formId = new Long(0);
		for (Iterator<FormDTO> iterator = forms.iterator(); iterator.hasNext();) {
			FormDTO formDTO = (FormDTO) iterator.next();
			if(formId.longValue() == formDTO.getId().longValue()) {
				iterator.remove();
			}
			formId = formDTO.getId();
		}
		return dto;
	}

	
}
