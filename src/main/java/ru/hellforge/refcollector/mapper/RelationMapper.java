package ru.hellforge.refcollector.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hellforge.refcollector.dto.RelationDto;
import ru.hellforge.refcollector.dto.RelationImportDto;
import ru.hellforge.refcollector.model.entity.Relation;
import ru.hellforge.refcollector.util.BaseOperationService;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class RelationMapper {
   // @Mapping(target = "objectCode", expression = "java(operationService.convertStringToUUID(relationDto.getObjectCode()))")
    public abstract Relation toEntity(RelationDto relationDto);

    //@Mapping(target = "objectCode", expression = "java(operationService.convertUUIDToString(relation.getObjectCode()))")
    public abstract RelationDto toDto(Relation relation);

    public abstract List<Relation> toEntityList(List<RelationDto> relationDtoList);

    public abstract List<RelationDto> toDtoList(List<Relation> relationList);

    //@Mapping(target = "objectCode", expression = "java(operationService.convertUUIDToString(relation.getObjectCode()))")
    public abstract RelationImportDto entityToImportDto(Relation relation);

    public abstract List<RelationImportDto> entityListToImportDtoList(List<Relation> relations);
}