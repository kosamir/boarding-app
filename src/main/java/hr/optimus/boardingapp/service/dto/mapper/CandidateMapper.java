package hr.optimus.boardingapp.service.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import hr.optimus.boardingapp.model.Candidate;
import hr.optimus.boardingapp.service.dto.CandidateDTO;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
	CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);
	
	
	@Mapping(target="date", source="dto.date",
	           dateFormat="yyyy-MM-dd")
	Candidate toEntity(CandidateDTO dto);
	
	
	CandidateDTO toDTO(Candidate entity);

}
