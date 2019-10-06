package hr.optimus.boardingapp.service.dto.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import hr.optimus.boardingapp.model.BoardingTemplate;
import hr.optimus.boardingapp.service.dto.BoardingTemplateDTO;


@Mapper(componentModel = "spring", uses = FormMapper.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface BoardingTemplateMapper {
	
	BoardingTemplateMapper INSTANCE = Mappers.getMapper(BoardingTemplateMapper.class);
	
	BoardingTemplate toEntity(BoardingTemplateDTO dto);
	
	BoardingTemplateDTO toDto(BoardingTemplate entity);
	

}
