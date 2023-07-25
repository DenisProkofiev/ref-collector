package ru.hellforge.refcollector.mapper;

import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.EnvironmentReferenceRelationDto;
import ru.hellforge.refcollector.model.entity.relation.EnvironmentReferenceRelation;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EnvironmentReferenceMapper {

    EnvironmentReferenceRelationDto toDto(EnvironmentReferenceRelation environment);

    EnvironmentReferenceRelation toEntity(EnvironmentReferenceRelationDto environmentDto);

}
