package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.EnvironmentDto;
import ru.hellforge.refcollector.dto.EnvironmentImportDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.mapper.EnvironmentMapper;
import ru.hellforge.refcollector.model.entity.Environment;
import ru.hellforge.refcollector.model.entity.relation.EnvironmentReferenceRelation;
import ru.hellforge.refcollector.repository.EnvironmentRepository;
import ru.hellforge.refcollector.service.EnvironmentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {

    private final EnvironmentRepository environmentRepository;
    private final EnvironmentMapper environmentMapper;

    @Override
    public List<EnvironmentDto> getAllEnvironment() {
        List<Environment> allEnvironmentList = environmentRepository.findAll();

        return environmentMapper.toDtoList(allEnvironmentList);
    }

    @Override
    public List<Long> getAllEnvironmentId() {
        return getAllEnvironment().stream()
                .map(EnvironmentDto::getId)
                .collect(toList());
    }

    @Override
    public EnvironmentDto addEnvironment(EnvironmentDto environmentDto) {
        Environment environment = environmentMapper.toEntity(environmentDto);

        Environment savedEnvironment = environmentRepository.save(environment);

        return environmentMapper.toDto(savedEnvironment);
    }

    @Override
    public EnvironmentDto getEnvironmentById(Long environmentId) {
        Environment environment = environmentRepository.findById(environmentId).orElseThrow(EntityNotFoundException::new);

        return environmentMapper.toDto(environment);
    }

    @Override
    public Boolean isEnvironmentExist(Long environmentId) {
        return environmentRepository.existsById(environmentId);
    }

    @Override
    public List<EnvironmentImportDto> getAllImportEnvironment() {
        return environmentMapper.entityListToImportDtoList(environmentRepository.findAll());
    }

    @Override
    public List<EnvironmentImportDto> importEnvironment(List<EnvironmentImportDto> environmentsImportList) {
        List<EnvironmentImportDto> newEnvironmentImports = compareImportReference(environmentsImportList);

        List<Environment> environments = environmentRepository.saveAll(environmentMapper.importDtoListToEntityList(newEnvironmentImports));

        return environmentMapper.entityListToImportDtoList(environments);
    }

    private List<EnvironmentImportDto> compareImportReference(List<EnvironmentImportDto> referenceImportList) {
        List<String> referencesName = environmentRepository.findAll().stream()
                .map(Environment::getName)
                .collect(toList());

        return referenceImportList.stream()
                .filter(referencesName::contains)
                .collect(toList());
    }

}