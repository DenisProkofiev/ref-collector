package ru.hellforge.refcollector.mapper;

import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.EnvironmentDto;
import ru.hellforge.refcollector.model.entity.Environment;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EnvironmentMapper {

    Environment toEntity(EnvironmentDto environmentDto);

    EnvironmentDto toDto(Environment environment);

    List<EnvironmentDto> toDtoList(List<Environment> environmentList);

}
