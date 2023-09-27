package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.EnvironmentDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;

import java.util.List;

public interface EnvironmentService {

    List<EnvironmentDto> getAllEnvironment();

    List<EnvironmentDto> getAllEnvironment(ReferenceFilterDto filter);

    List<Long> getAllEnvironmentId(ReferenceFilterDto filter);

    EnvironmentDto addEnvironment(EnvironmentDto environmentDto);

    EnvironmentDto getEnvironmentById(Long environmentId);

    void addReferenceToEnvironment(Long environmentId, Long referenceId);
}
