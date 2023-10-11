package ru.hellforge.refcollector.mapper;

import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceImportDto;
import ru.hellforge.refcollector.model.entity.Reference;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * ReferenceMapper.
 *
 * @author dprokofev
 */
@Mapper(componentModel = SPRING)
public interface ReferenceMapper {
    ReferenceDto entityToFullDto(Reference reference);
    Reference fullDtoToEntity(ReferenceDto referenceDto);
    List<ReferenceDto> referenceListToFullDtoList(List<Reference> referenceList);
    List<Reference> fullDtoListToEntityList(List<ReferenceDto> referenceDtoList);
    List<ReferenceImportDto> entityListToImportDtoList(List<Reference> referenceList);
    List<Reference> importDtoListToEntityList(List<ReferenceImportDto> referenceDtoList);
}