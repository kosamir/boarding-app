package hr.optimus.boardingapp.service.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import hr.optimus.boardingapp.model.BoardingTemplate;
import hr.optimus.boardingapp.service.dto.BoardingTemplateDTO;


@Mapper(componentModel = "spring")
public interface BoardingTemplateMapper {
	
	BoardingTemplateMapper INSTANCE = Mappers.getMapper(BoardingTemplateMapper.class);
	
	BoardingTemplate toEntity(BoardingTemplateDTO dto);
	
	@Mapping(ignore = true, source="forms" ,target = "forms")
	BoardingTemplateDTO toDto(BoardingTemplate entity);
	

}
