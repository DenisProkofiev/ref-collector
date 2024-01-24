package ru.hellforge.refcollector.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hellforge.refcollector.dto.EnvironmentDto;
import ru.hellforge.refcollector.dto.EnvironmentImportDto;
import ru.hellforge.refcollector.model.entity.Environment;
import ru.hellforge.refcollector.util.BaseOperationService;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class EnvironmentMapper {
    @Autowired
    protected BaseOperationService operationService;

    @Mapping(target = "objectCode", expression = "java(operationService.convertStringToUUID(environmentDto.getObjectCode()))")
    public abstract Environment toEntity(EnvironmentDto environmentDto);

    @Mapping(target = "objectCode", expression = "java(operationService.convertUUIDToString(environment.getObjectCode()))")
    public abstract EnvironmentDto toDto(Environment environment);

    public abstract List<EnvironmentDto> toDtoList(List<Environment> environmentList);

    public abstract List<EnvironmentImportDto> entityListToImportDtoList(List<Environment> all);

    @Mapping(target = "objectCode", expression = "java(operationService.convertStringToUUID(environmentImport.getObjectCode()))")
    public abstract Environment importDtoToEntity(EnvironmentImportDto environmentImport);

    public abstract List<Environment> importDtoListToEntityList(List<EnvironmentImportDto> newEnvironmentImports);
}