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
    ReferenceDto toDto(Reference reference);
    Reference toEntity(ReferenceDto referenceDto);
    List<ReferenceDto> toDtoList(List<Reference> referenceList);
    List<Reference> toEntityList(List<ReferenceDto> referenceDtoList);
    ReferenceDto toFullDto(ReferenceImportDto referenceImportDto);
    List<ReferenceDto> toFullDtoList(List<ReferenceImportDto> referenceImportDtoList);
}