package hr.optimus.boardingapp.service.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import hr.optimus.boardingapp.model.CandidateResponse;
import hr.optimus.boardingapp.service.dto.CandidateResponseDTO;

@Mapper(componentModel = "spring", uses = {FormMapper.class, FieldMapper.class, CandidateMapper.class, BoardingTemplateMapper.class})
public interface CandidateResponseMapper {
	
	CandidateResponseMapper INSTANCE = Mappers.getMapper(CandidateResponseMapper.class);
	
	CandidateResponse toEntity(CandidateResponseDTO dto);
	@Mapping(source="entity.form", target = "form", ignore = true)
	@Mapping(source="entity.template", target = "template", ignore = true)
	@Mapping(source="entity.field", target = "field", ignore = true)
	CandidateResponseDTO toDto(CandidateResponse entity);

}
