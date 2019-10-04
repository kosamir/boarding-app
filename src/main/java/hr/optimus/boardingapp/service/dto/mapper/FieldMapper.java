package hr.optimus.boardingapp.service.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import hr.optimus.boardingapp.model.Field;
import hr.optimus.boardingapp.service.dto.FieldDTO;

@Mapper(componentModel = "spring")
public interface FieldMapper {
	
	FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);
	
	Field toEntity(FieldDTO dto);
	FieldDTO toDto(Field entity);

}
