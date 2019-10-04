package hr.optimus.boardingapp.service.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import hr.optimus.boardingapp.model.Form;
import hr.optimus.boardingapp.service.dto.FormDTO;

@Mapper(componentModel = "spring", uses = FieldMapper.class)
public interface FormMapper {
	
	FormMapper INSTANCE = Mappers.getMapper(FormMapper.class);
	
	Form toEntity(FormDTO dto);
	FormDTO toDto(Form entity);

}
