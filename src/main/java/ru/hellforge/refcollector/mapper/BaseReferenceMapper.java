package ru.hellforge.refcollector.mapper;

import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.BaseRelationDto;
import ru.hellforge.refcollector.model.entity.BaseRelation;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface BaseReferenceMapper {
    BaseRelation toEntity(BaseRelationDto relationDto);

    BaseRelationDto toDto(BaseRelation relation);
}