package ru.hellforge.refcollector.mapper;

import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.BaseRelationDto;
import ru.hellforge.refcollector.dto.BaseRelationImportDto;
import ru.hellforge.refcollector.model.entity.BaseRelation;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface BaseReferenceMapper {
    BaseRelation toEntity(BaseRelationDto relationDto);
    BaseRelationDto toDto(BaseRelation relation);
    List<BaseRelation> toEntityList(List<BaseRelationDto> baseRelationDtoList);
    List<BaseRelationDto> toDtoList(List<BaseRelation> baseRelationList);

    List<BaseRelationImportDto> entityListToImportDtoList(List<BaseRelation> all);
}