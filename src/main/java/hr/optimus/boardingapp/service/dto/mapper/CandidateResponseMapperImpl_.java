package hr.optimus.boardingapp.service.dto.mapper;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import hr.optimus.boardingapp.model.CandidateResponse;
import hr.optimus.boardingapp.service.dto.CandidateResponseDTO;
import lombok.RequiredArgsConstructor;

@Primary
@Service("CandidateResponseMapperImpl")
@RequiredArgsConstructor
public class CandidateResponseMapperImpl_ implements CandidateResponseMapper {

	private final CandidateResponseMapper mapper;

	@Override
	public CandidateResponse toEntity(CandidateResponseDTO dto) {
		return mapper.toEntity(dto);
	}

	@Override
	public CandidateResponseDTO toDto(CandidateResponse entity) {
		CandidateResponseDTO dto = mapper.toDto(entity);
		dto.setBoardName(entity.getTemplate().getName());
		dto.setFormName(entity.getForm().getName());
		dto.setFieldName(entity.getField().getLabel());
		
		return dto;
	}

}
