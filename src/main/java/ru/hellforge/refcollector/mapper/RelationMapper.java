package ru.hellforge.refcollector.mapper;


import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.RelationDto;
import ru.hellforge.refcollector.dto.RelationImportDto;
import ru.hellforge.refcollector.model.entity.Relation;


import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RelationMapper {
    Relation toEntity(RelationDto relationDto);

    RelationDto toDto(Relation relation);

    List<Relation> toEntityList(List<RelationDto> relationDtoList);

    List<RelationDto> toDtoList(List<Relation> relationList);

    List<RelationImportDto> entityListToImportDtoList(List<Relation> relations);
}