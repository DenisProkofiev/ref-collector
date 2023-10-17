package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.EnvironmentDto;
import ru.hellforge.refcollector.dto.EnvironmentImportDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;

import java.util.List;

public interface EnvironmentService {

    List<EnvironmentDto> getAllEnvironment();

    List<Long> getAllEnvironmentId();

    EnvironmentDto addEnvironment(EnvironmentDto environmentDto);

    EnvironmentDto getEnvironmentById(Long environmentId);

    Boolean isEnvironmentExist(Long environmentId);

    List<EnvironmentImportDto> getAllImportEnvironment();

    List<EnvironmentImportDto> importEnvironment(List<EnvironmentImportDto> environments);
}
